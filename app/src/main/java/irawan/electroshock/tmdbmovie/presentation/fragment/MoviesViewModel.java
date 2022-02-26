package irawan.electroshock.tmdbmovie.presentation.fragment;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import irawan.electroshock.tmdbmovie.di.module.MoviesRepositoryModule;

public class MoviesViewModel extends ViewModel {

    @Inject
    public MoviesViewModel(){
    }

    @Inject
    MoviesRepositoryModule repository;

}