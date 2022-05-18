package irawan.electroshock.tmdbmovie.presentation.fragment.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import irawan.electroshock.tmdbmovie.data.model.Movies;
import irawan.electroshock.tmdbmovie.di.module.MoviesUseCaseModule;

public class MoviesViewModel extends ViewModel {

    MoviesUseCaseModule moviesUseCaseModule;

    @Inject
    public MoviesViewModel(MoviesUseCaseModule moviesUseCaseModule){
        this.moviesUseCaseModule = moviesUseCaseModule;
    }

    public MutableLiveData<ArrayList<Movies>> moviesGetDataObject(){
        return moviesUseCaseModule.provideMoviesGetDataObject();
    }

    public List<Movies> getDatabaseData(){
        return moviesUseCaseModule.provideGetDatabaseData();
    }

    public MutableLiveData<ArrayList<Movies>> moviesObservableGetData(){
        return moviesUseCaseModule.provideMoviesObservableGetData();
    }

}