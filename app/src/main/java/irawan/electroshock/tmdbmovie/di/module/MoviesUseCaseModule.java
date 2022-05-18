package irawan.electroshock.tmdbmovie.di.module;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import irawan.electroshock.tmdbmovie.data.model.Movies;

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
    public MutableLiveData<ArrayList<Movies>> provideMoviesObservableGetData(){
        return repository.provideMoviesObservableGetData();
    }
}
