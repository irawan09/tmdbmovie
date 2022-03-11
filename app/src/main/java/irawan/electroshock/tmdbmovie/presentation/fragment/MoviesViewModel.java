package irawan.electroshock.tmdbmovie.presentation.fragment;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import irawan.electroshock.tmdbmovie.data.database.Executor;
import irawan.electroshock.tmdbmovie.data.database.retention.ApplicationContext;
import irawan.electroshock.tmdbmovie.data.model.MovieEntity;
import irawan.electroshock.tmdbmovie.data.model.Movies;
import irawan.electroshock.tmdbmovie.di.module.AppModule;
import irawan.electroshock.tmdbmovie.di.module.MoviesRepositoryModule;

public class MoviesViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Movies>> moviesData;

    @Inject
    public MoviesViewModel(){
    }

    private LiveData<Movies> remoteData;

    @Inject
    MoviesRepositoryModule repository;

    public MutableLiveData<ArrayList<Movies>> getMoviesList(){

        return moviesData;
    }


}