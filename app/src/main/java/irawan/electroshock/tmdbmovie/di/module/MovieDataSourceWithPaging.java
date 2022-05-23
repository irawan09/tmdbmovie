package irawan.electroshock.tmdbmovie.di.module;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagingState;
import androidx.paging.rxjava3.RxPagingSource;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Single;
import irawan.electroshock.tmdbmovie.data.model.ObservableMovies;


public class MovieDataSourceWithPaging extends RxPagingSource<Integer, ObservableMovies> {

    private final Context context;
    private final String TAG = "Movie Paging";

    public MovieDataSourceWithPaging(Context context){
        this.context = context;
    }

    @Nullable
    @Override
    public Integer getRefreshKey(@NonNull PagingState<Integer, ObservableMovies> pagingState) {
        return null;
    }

    @NonNull
    @Override
    public Single<LoadResult<Integer, ObservableMovies>> loadSingle(@NonNull LoadParams<Integer> loadParams) {
        int page = loadParams.getKey() != null ? loadParams.getKey() : 1;

        MoviesRepositoryModule repository = new MoviesRepositoryModule();
        MoviesUseCaseModule useCase = new MoviesUseCaseModule(repository);

        return useCase.provideFlowablePaging(page, context);
    }

    private LoadResult<Integer, ObservableMovies> toLoadResult(List<ObservableMovies> movies, Integer page) {
        return new LoadResult.Page<>(movies, page == 1 ? null : page - 1, page + 1);
    }
}
