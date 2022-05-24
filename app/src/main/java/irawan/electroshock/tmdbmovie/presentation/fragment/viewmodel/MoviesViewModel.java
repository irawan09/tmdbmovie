package irawan.electroshock.tmdbmovie.presentation.fragment.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Flowable;
import irawan.electroshock.tmdbmovie.data.model.Movies;
import irawan.electroshock.tmdbmovie.data.MovieDataSourceWithPaging;
import irawan.electroshock.tmdbmovie.di.module.MoviesUseCaseModule;
import kotlinx.coroutines.CoroutineScope;

public class MoviesViewModel extends ViewModel {

    private String TAG = MoviesViewModel.class.getSimpleName();
    MoviesUseCaseModule moviesUseCaseModule;
    public Flowable<PagingData<Movies>> pagingDataFlow;

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

    public void subscribeFlowable(Context context){
        // Define Paging Source
        MovieDataSourceWithPaging moviePagingSource = new MovieDataSourceWithPaging(context);
        // Create new pager
        Pager<Integer, Movies> pager = new Pager<>(
                new PagingConfig(20,    // pageSize - Count of items in one page
                        20,       // prefetchDistance - Number of items to prefetch
                        false,  // enablePlaceholders - Enable placeholders for data which is not yet loaded
                        20,        // initialLoadSize - Count of items to be loaded initially
                        20*499),       // maxSize - Count of total items to be shown in recyclerview
                () -> moviePagingSource);      // set paging source

        // init Flowable
        pagingDataFlow = PagingRx.getFlowable(pager);
        CoroutineScope coroutineScope = ViewModelKt.getViewModelScope(this);
        PagingRx.cachedIn(pagingDataFlow, coroutineScope);
    }

}