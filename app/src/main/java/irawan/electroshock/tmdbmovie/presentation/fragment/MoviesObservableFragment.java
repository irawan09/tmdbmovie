package irawan.electroshock.tmdbmovie.presentation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import irawan.electroshock.tmdbmovie.data.model.Movies;
import irawan.electroshock.tmdbmovie.databinding.FragmentObservableMoviesBinding;
import irawan.electroshock.tmdbmovie.presentation.adapter.MoviesAdapter;

public class MoviesObservableFragment extends Fragment {

    private static final String TAG = "MoviesFragment data";
    private FragmentObservableMoviesBinding moviesFragmentBinding;
    private ArrayList<Movies> moviesArrayList = new ArrayList<>();

    public static MoviesObservableFragment newInstance() {
        return new MoviesObservableFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        moviesFragmentBinding = FragmentObservableMoviesBinding.inflate(inflater, container, false);
        return moviesFragmentBinding.getRoot();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle getData = this.getArguments();
        if(getData != null){
            moviesFragmentBinding.moviesObservableRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            MoviesAdapter adapter = new MoviesAdapter(moviesArrayList, getContext());
            moviesFragmentBinding.moviesObservableRecyclerView.setAdapter(adapter);

            moviesArrayList = (ArrayList<Movies>) getData.getSerializable("MoviesData");
            adapter.updateList(moviesArrayList);
        }
    }
}