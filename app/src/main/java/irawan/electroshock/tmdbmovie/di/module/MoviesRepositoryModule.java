package irawan.electroshock.tmdbmovie.di.module;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagingSource;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import irawan.electroshock.tmdbmovie.data.api.ServiceApi;
import irawan.electroshock.tmdbmovie.data.database.AppDatabase;
import irawan.electroshock.tmdbmovie.data.database.Executor;
import irawan.electroshock.tmdbmovie.data.database.dao.MoviesDao;
import irawan.electroshock.tmdbmovie.data.model.Movies;
import irawan.electroshock.tmdbmovie.data.model.Results;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

@Module
public class MoviesRepositoryModule {

    @Inject
    Retrofit retrofit;

    @Inject
    AppDatabase database;

    ServiceApi api;
    MoviesDao dao;
    Movies moviesModel;
    private static final String apiKey = "9edf3fee29984e86d8be8170d810dd71";
    private static final String TAG = "Repository";
    private final ArrayList<Movies> moviesArrayList = new ArrayList<>();
    private final MutableLiveData<ArrayList<Movies>> moviesMutableLiveData = new MutableLiveData<>();

    @Inject
    public MoviesRepositoryModule(){
    }

    @Provides
    @Singleton
    public MutableLiveData<ArrayList<Movies>> provideMoviesGetData(){
        final MoviesDao moviesDao = database.moviesDao();
        api = retrofit.create(ServiceApi.class);
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

                            moviesArrayList.add(movies);
                            moviesMutableLiveData.postValue(moviesArrayList);
                            Executor.IOThread(() -> moviesDao.insertAll(movies));
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Results> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });

        return moviesMutableLiveData;
    }

    @Provides
    @Singleton
    public List<Movies> provideGetDatabaseData(){
        final MoviesDao moviesDao = database.moviesDao();
        return moviesDao.getAll();
    }


    @Provides
    @Singleton
    public Observable<Results> provideGetMoviesObservable(){
        api = retrofit.create(ServiceApi.class);
        return api.getObservableMovies(apiKey);
    }

    @Provides
    @Singleton
    public MutableLiveData<ArrayList<Movies>> provideMoviesObservableGetData(){
        dao = database.moviesDao();
        provideGetMoviesObservable()
                .subscribeOn(Schedulers.io())
                .map(observableMovies -> {
                    ArrayList<Movies> list = (ArrayList<Movies>) observableMovies.getResults();
                    for(int i=0; i< list.size(); i++){
                        String id = observableMovies.getResults().get(i).getId();
                        String title = observableMovies.getResults().get(i).getTitle();
                        String overview = observableMovies.getResults().get(i).getOverview();
                        String posterPath = observableMovies.getResults().get(i).getPosterPath();
                        String releaseDate = observableMovies.getResults().get(i).getReleaseDate();

                        moviesModel = new Movies();
                        moviesModel.setId(id);
                        moviesModel.setTitle(title);
                        moviesModel.setOverview(overview);
                        moviesModel.setPosterPath(posterPath);
                        moviesModel.setReleaseDate(releaseDate);
                        moviesArrayList.add(moviesModel);
                        moviesMutableLiveData.postValue(moviesArrayList);
                        Executor.IOThread(() -> dao.insertAll(moviesModel));
                    }
                    return list;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moviesMutableLiveData::setValue,
                        error-> Log.e(TAG, "getMovies: " + error.getMessage() ));

        return moviesMutableLiveData;
    }

    @Singleton
    @Provides
    public Single<PagingSource.LoadResult<Integer, Movies>> provideFlowablePaging(int page, Context context){
        NetworkModule networkModule = new NetworkModule("https://api.themoviedb.org/3/");
        DatabaseModule databaseModule = new DatabaseModule(context);

        Gson gson = networkModule.provideGson();
        Retrofit retrofit = networkModule.provideRetrofit(gson);
        ServiceApi api = retrofit.create(ServiceApi.class);

        AppDatabase database = databaseModule.provideDatabase();
        dao = database.moviesDao();

        return api.getObservableMoviesWithPaging(apiKey, page)
                .subscribeOn(Schedulers.io())
                .map(Results::getResults)
                .map(movies -> toLoadResult(movies, page))
                .onErrorReturn(PagingSource.LoadResult.Error::new);
    }

    private PagingSource.LoadResult<Integer, Movies> toLoadResult(List<Movies> movies, Integer page) {
        return new PagingSource.LoadResult.Page<>(movies, page == 1 ? null : page - 1, page + 1);
    }

}
