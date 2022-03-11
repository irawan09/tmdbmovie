package irawan.electroshock.tmdbmovie.presentation.fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

import irawan.electroshock.tmdbmovie.data.model.Movies;
import irawan.electroshock.tmdbmovie.di.module.MoviesRepositoryModule;

public class MoviesViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Movies>> moviesData;

    @Inject
    public MoviesViewModel(){
    }

    @Inject
    MoviesRepositoryModule repository;

    public MutableLiveData<ArrayList<Movies>> getMoviesList(){

        return moviesData;
    }


}