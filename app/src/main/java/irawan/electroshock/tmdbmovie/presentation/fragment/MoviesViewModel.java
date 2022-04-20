package irawan.electroshock.tmdbmovie.presentation.fragment;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import irawan.electroshock.tmdbmovie.data.model.Movies;
import irawan.electroshock.tmdbmovie.di.module.MoviesRepositoryModule;

public class MoviesViewModel extends ViewModel {

    private static final String TAG = "PokemonViewModel";

    private final MutableLiveData<ArrayList<Movies>> moviesDataList = new MutableLiveData<>();
    @Inject
    MoviesRepositoryModule repository;

    public MoviesViewModel(){
    }

    public void getMoviesViewModels(){
        repository.provideGetMoviesObservable()
                .subscribeOn(Schedulers.io())
                .map(observableMovies -> {
                    ArrayList<Movies> list = observableMovies.getResultsObservable();
                    for(int i=0; i< list.size(); i++){
                        String id = observableMovies.getResultsObservable().get(i).getId();
                        String title = observableMovies.getResultsObservable().get(i).getTitle();
                        String overview = observableMovies.getResultsObservable().get(i).getOverview();
                        String posterPath = observableMovies.getResultsObservable().get(i).getPosterPath();
                        Log.e(TAG, "apply: "+ observableMovies.getResultsObservable().get(i).getTitle());
                    }
                    return list;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moviesDataList::setValue,
                        error-> Log.e(TAG, "getMovies: " + error.getMessage() ));
    }





}