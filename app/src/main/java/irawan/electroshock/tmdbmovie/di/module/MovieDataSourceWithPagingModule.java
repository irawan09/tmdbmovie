package irawan.electroshock.tmdbmovie.di.module;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagingState;
import androidx.paging.rxjava3.RxPagingSource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import irawan.electroshock.tmdbmovie.data.api.ServiceApi;
import irawan.electroshock.tmdbmovie.data.database.AppDatabase;
import irawan.electroshock.tmdbmovie.data.database.Executor;
import irawan.electroshock.tmdbmovie.data.database.dao.MoviesDao;
import irawan.electroshock.tmdbmovie.data.model.ObservableMovies;
import retrofit2.Retrofit;

@Module
public class MovieDataSourceWithPagingModule extends RxPagingSource<Integer, ObservableMovies> {

    private static final String apiKey = "9edf3fee29984e86d8be8170d810dd71";
    ServiceApi api;
    private final ArrayList<ObservableMovies> moviesArrayList = new ArrayList<>();
    private final MutableLiveData<ArrayList<ObservableMovies>> moviesMutableLiveData = new MutableLiveData<>();

    @Inject
    Retrofit retrofit;

    @Inject
    AppDatabase database;

    @Inject
    MoviesRepositoryModule repository;

    @Inject
    public MovieDataSourceWithPagingModule(){

    }

    @Nullable
    @Override
    public Integer getRefreshKey(@NonNull PagingState<Integer, ObservableMovies> pagingState) {
        return null;
    }

    @NonNull
    @Override
    public Single<LoadResult<Integer, ObservableMovies>> loadSingle(@NonNull LoadParams<Integer> loadParams) {
        int page = loadParams.getKey() != null ? loadParams.getKey() : 1;


        final MoviesDao moviesDao = database.moviesDao();
        api = retrofit.create(ServiceApi.class);
        return api.getObservableMoviesWithPaging(apiKey, page)
                .subscribeOn(Schedulers.io())
                .map(observableMovies -> {
                    List<ObservableMovies> list = new ArrayList<>();

                    Log.i("Line ", "---------------------------------------");
                    String id = observableMovies.getId();
                    String title = observableMovies.getTitle();
                    String overview = observableMovies.getOverview();
                    String posterPath = observableMovies.getPosterPath();
                    String releaseDate = observableMovies.getReleaseDate();

                    ObservableMovies movies = new ObservableMovies();
                    movies.setId(id);
                    movies.setTitle(title);
                    movies.setOverview(overview);
                    movies.setPosterPath(posterPath);
                    movies.setReleaseDate(releaseDate);
                    moviesArrayList.add(movies);
                    moviesMutableLiveData.postValue(moviesArrayList);
                    Executor.IOThread(() -> moviesDao.insertAllObservable(movies));

                    return list;
                })
                .map(movies -> toLoadResult(movies, page))
                .onErrorReturn(LoadResult.Error::new);
    }


    private LoadResult<Integer, ObservableMovies> toLoadResult(List<ObservableMovies> movies, Integer page) {
        return new LoadResult.Page<>(movies, page == 1 ? null : page - 1, page + 1);
    }

    @Provides
    @Singleton
    public MutableLiveData<ArrayList<ObservableMovies>> provideLoadSingle(){
        return moviesMutableLiveData;
    }
}
