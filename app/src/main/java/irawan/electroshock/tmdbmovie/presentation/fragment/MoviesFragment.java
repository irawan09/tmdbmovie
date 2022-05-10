package irawan.electroshock.tmdbmovie.presentation.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import irawan.electroshock.tmdbmovie.R;
import irawan.electroshock.tmdbmovie.data.model.Movies;
import irawan.electroshock.tmdbmovie.databinding.MoviesFragmentBinding;
import irawan.electroshock.tmdbmovie.presentation.adapter.MoviesAdapter;

public class MoviesFragment extends Fragment {

    private static final String TAG = "MoviesFragment data";
    private MoviesFragmentBinding moviesFragmentBinding;
    private MoviesAdapter adapter;
    private ArrayList<Movies> moviesArrayList = new ArrayList<>();

    public static MoviesFragment newInstance() {
        return new MoviesFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        moviesFragmentBinding = MoviesFragmentBinding.inflate(inflater, container, false);
        return moviesFragmentBinding.getRoot();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle getData = this.getArguments();
        if(getData != null){
            moviesFragmentBinding.moviesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            adapter = new MoviesAdapter(moviesArrayList, getContext());
            moviesFragmentBinding.moviesRecyclerView.setAdapter(adapter);

            moviesArrayList = (ArrayList<Movies>) getData.getSerializable("MoviesData");
            Log.d(TAG, String.valueOf(moviesArrayList.size()));
            adapter.updateList(moviesArrayList);


//            moviesArrayList.clear();
        }

    }
}