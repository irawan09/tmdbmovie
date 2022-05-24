package irawan.electroshock.tmdbmovie.presentation.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import irawan.electroshock.tmdbmovie.BaseApplication;
import irawan.electroshock.tmdbmovie.databinding.PagingFragmentBinding;
import irawan.electroshock.tmdbmovie.di.module.MoviesUseCaseModule;
import irawan.electroshock.tmdbmovie.presentation.adapter.MoviesPagingAdapter;
import irawan.electroshock.tmdbmovie.presentation.adapter.MoviesPagingStateAdapter;
import irawan.electroshock.tmdbmovie.presentation.fragment.viewmodel.MoviesViewModel;
import irawan.electroshock.tmdbmovie.presentation.fragment.viewmodel.MoviesViewModelFactory;
import irawan.electroshock.tmdbmovie.utils.GridSpace;
import irawan.electroshock.tmdbmovie.utils.MovieComparator;

public class PagingFragment extends Fragment {

    private static final String TAG = PagingFragment.class.getSimpleName();
    private PagingFragmentBinding pagingFragment;
    private MoviesViewModel  mViewModel;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Inject
    MoviesViewModelFactory mViewModelFactory;
    @Inject
    MoviesUseCaseModule useCase;

    public static PagingFragment newInstance() {
        return  new PagingFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        pagingFragment = PagingFragmentBinding.inflate(inflater, container, false);
        return pagingFragment.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MoviesPagingAdapter moviesPagingAdapter = new MoviesPagingAdapter(
                new MovieComparator(), this.requireContext());

        ((BaseApplication) this.requireActivity().getApplication()).getNetComponent().inject(this);

        mViewModel = new ViewModelProvider(this, mViewModelFactory).get(MoviesViewModel.class);

        mViewModel.subscribeFlowable(this.requireContext());
        Disposable disposable = mViewModel.pagingDataFlow.subscribe(moviePagingData -> {
            Log.i(TAG, moviePagingData.toString());
            moviesPagingAdapter.submitData(getLifecycle(), moviePagingData);
        });

        compositeDisposable.add(disposable);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.requireContext(), 2);

        pagingFragment.recyclerViewMovies.setLayoutManager(gridLayoutManager);
        pagingFragment.recyclerViewMovies.addItemDecoration(new GridSpace(2, 12, true));
        pagingFragment.recyclerViewMovies.setAdapter(
                moviesPagingAdapter.withLoadStateFooter(
                        new MoviesPagingStateAdapter(mview -> moviesPagingAdapter.retry())
                )
        );

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return moviesPagingAdapter.getItemViewType(position) == MoviesPagingAdapter.LOADING_ITEM ? 1:2;
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        compositeDisposable.dispose();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }
}