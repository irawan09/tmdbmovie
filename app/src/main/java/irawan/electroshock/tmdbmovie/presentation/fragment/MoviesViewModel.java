package irawan.electroshock.tmdbmovie.presentation.fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import irawan.electroshock.tmdbmovie.data.database.Executor;
import irawan.electroshock.tmdbmovie.data.model.MovieEntity;
import irawan.electroshock.tmdbmovie.data.model.Movies;
import irawan.electroshock.tmdbmovie.di.module.MoviesRepositoryModule;

public class MoviesViewModel extends ViewModel {

    @Inject
    public MoviesViewModel(){
    }

    private LiveData<Movies> remoteData;

    @Inject
    MoviesRepositoryModule repository;



    public List<MovieEntity> getMoviesList(){
        Executor.IOThread(()->repository.provideGetDatabaseData());
        return repository.provideGetDatabaseData();
    }


}