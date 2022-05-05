package irawan.electroshock.tmdbmovie.presentation.fragment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import irawan.electroshock.tmdbmovie.data.model.Movies;
import irawan.electroshock.tmdbmovie.di.module.MoviesUseCase;

public class MoviesViewModel extends ViewModel {
    @Inject
    MoviesUseCase moviesUseCase;

    public MutableLiveData<ArrayList<Movies>> moviesGetDataObject(){
        return moviesUseCase.provideMoviesGetDataObject();
    }

    public List<Movies> getDatabaseData(){
        return moviesUseCase.provideGetDatabaseData();
    }

    public MutableLiveData<ArrayList<Movies>> moviesObservableGetData(){
        return moviesUseCase.provideMoviesObservableGetData();
    }

}