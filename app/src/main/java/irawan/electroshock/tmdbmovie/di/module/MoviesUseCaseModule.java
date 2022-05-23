package irawan.electroshock.tmdbmovie.di.module;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagingSource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.rxjava3.core.Single;
import irawan.electroshock.tmdbmovie.data.model.Movies;
import irawan.electroshock.tmdbmovie.data.model.ObservableMovies;

@Module
public class MoviesUseCaseModule {

    MoviesRepositoryModule repository;

    @Inject
    public MoviesUseCaseModule(MoviesRepositoryModule repository){
        this.repository= repository;
    }

    @Provides
    @Singleton
    public MutableLiveData<ArrayList<Movies>> provideMoviesGetDataObject(){
        return repository.provideMoviesGetData();
    }

    @Provides
    @Singleton
    public List<Movies> provideGetDatabaseData(){
        return repository.provideGetDatabaseData();
    }

    @Provides
    @Singleton
    public MutableLiveData<ArrayList<ObservableMovies>> provideMoviesObservableGetData(){
        return repository.provideMoviesObservableGetData();
    }

    @Provides
    @Singleton
    public Single<PagingSource.LoadResult<Integer, ObservableMovies>> provideFlowablePaging(
            int page,
            Context context){
        return repository.provideFlowablePaging(page, context);
    }

}
