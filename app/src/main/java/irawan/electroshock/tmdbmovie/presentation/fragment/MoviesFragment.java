package irawan.electroshock.tmdbmovie.presentation.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import irawan.electroshock.tmdbmovie.R;
import irawan.electroshock.tmdbmovie.presentation.adapter.MoviesAdapter;

public class MoviesFragment extends Fragment {

    private MoviesViewModel mViewModel;

    public static MoviesFragment newInstance() {
        return new MoviesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.movies_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycle_movies);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

//        recyclerView.setAdapter(new MoviesAdapter(null,getContext()));


        return view;
    }

}