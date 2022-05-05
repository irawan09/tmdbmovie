package irawan.electroshock.tmdbmovie.presentation.fragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

public class MoviesViewModelFactory implements ViewModelProvider.Factory {

    private final MoviesViewModel moviesViewModel;

    @Inject
    public MoviesViewModelFactory(MoviesViewModel moviesViewModel) {
        this.moviesViewModel = moviesViewModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> aClass) {
        ViewModel viewModel;
        if(aClass == MoviesViewModel.class){
            viewModel = moviesViewModel;
        } else{
            throw new RuntimeException("Unsupported View Model class: "+aClass);
        }
        return (T) viewModel;
    }
}
