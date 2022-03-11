package irawan.electroshock.tmdbmovie.di.module;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import irawan.electroshock.tmdbmovie.data.api.ServiceApi;
import irawan.electroshock.tmdbmovie.data.database.AppDatabase;
import irawan.electroshock.tmdbmovie.data.database.Executor;
import irawan.electroshock.tmdbmovie.data.database.dao.MoviesDao;
import irawan.electroshock.tmdbmovie.data.model.MovieEntity;
import irawan.electroshock.tmdbmovie.data.model.Movies;
import irawan.electroshock.tmdbmovie.data.model.Results;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

@Module
public class MoviesRepositoryModule {

    @Inject
    public MoviesRepositoryModule(){
    }

    @Inject
    Retrofit retrofit;

    @Inject
    AppDatabase database;

    ServiceApi api;
    private static final String apiKey = "9edf3fee29984e86d8be8170d810dd71";
    private static final String TAG = "Repository";
    private final ArrayList<Movies> moviesArrayList = new ArrayList<>();
    private final MutableLiveData<ArrayList<Movies>> moviesMutableLiveData = new MutableLiveData<>();

    @Provides
    @Singleton
    public MutableLiveData<ArrayList<Movies>> provideMoviesGetData(){
        api = retrofit.create(ServiceApi.class);
        final MoviesDao moviesDao = database.moviesDao();
        api.getPopularMovies(apiKey).enqueue(new Callback<Results>() {
            @Override
            public void onResponse(@NonNull Call<Results> call, @NonNull Response<Results> response) {
                if (response.isSuccessful()) {
                    for(int i = 0; i < Objects.requireNonNull(response.body()).getResults().size(); i++ ){
                        List<Movies> data = response.body().getResults();
                        if (response.body().getResults() != null){
                            String id = data.get(i).getId();
                            String title = data.get(i).getTitle();
                            String overview = data.get(i).getOverview();
                            String posterPath = data.get(i).getPosterPath();
                            String releaseDate = data.get(i).getReleaseDate();

                            Movies movies = new Movies();
                            movies.setId(id);
                            movies.setTitle(title);
                            movies.setOverview(overview);
                            movies.setPosterPath(posterPath);
                            movies.setReleaseDate(releaseDate);

                            MovieEntity movieEntity = new MovieEntity();
                            movieEntity.setId(Integer.parseInt(id));
                            movieEntity.setTitle(title);
                            movieEntity.setOverview(overview);
                            movieEntity.setPosterPath(posterPath);
                            movieEntity.setReleaseDate(releaseDate);

                            moviesArrayList.add(movies);
                            moviesMutableLiveData.postValue(moviesArrayList);
                            Executor.IOThread(() -> moviesDao.insertAll(movieEntity));
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Results> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });

        Log.d("LIVE DATA", String.valueOf(moviesMutableLiveData));

        return moviesMutableLiveData;
    }

    @Provides
    @Singleton
    public List<MovieEntity> provideGetDatabaseData(){
        final MoviesDao moviesDao = database.moviesDao();
        return moviesDao.getAll();
    }

}
