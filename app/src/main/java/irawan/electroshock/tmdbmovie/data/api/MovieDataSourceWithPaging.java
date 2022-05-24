package irawan.electroshock.tmdbmovie.data.api;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagingState;
import androidx.paging.rxjava3.RxPagingSource;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import irawan.electroshock.tmdbmovie.data.model.Movies;
import irawan.electroshock.tmdbmovie.di.module.MoviesRepositoryModule;
import irawan.electroshock.tmdbmovie.di.module.MoviesUseCaseModule;


public class MovieDataSourceWithPaging extends RxPagingSource<Integer, Movies> {

    private final Context context;
    private final String TAG = "Movie Paging";

    public MovieDataSourceWithPaging(Context context){
        this.context = context;
    }

    @Nullable
    @Override
    public Integer getRefreshKey(@NonNull PagingState<Integer, Movies> pagingState) {
        return null;
    }

    @NonNull
    @Override
    public Single<LoadResult<Integer, Movies>> loadSingle(@NonNull LoadParams<Integer> loadParams) {
        int page = loadParams.getKey() != null ? loadParams.getKey() : 1;

        MoviesRepositoryModule repository = new MoviesRepositoryModule();
        MoviesUseCaseModule useCase = new MoviesUseCaseModule(repository);

        return useCase.provideFlowablePaging(page, context);
    }

    private LoadResult<Integer, Movies> toLoadResult(List<Movies> movies, Integer page) {
        return new LoadResult.Page<>(movies, page == 1 ? null : page - 1, page + 1);
    }
}
