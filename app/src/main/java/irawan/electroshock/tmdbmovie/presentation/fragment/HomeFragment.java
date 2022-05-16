package irawan.electroshock.tmdbmovie.presentation.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import irawan.electroshock.tmdbmovie.BaseApplication;
import irawan.electroshock.tmdbmovie.R;
import irawan.electroshock.tmdbmovie.data.api.ServiceApi;
import irawan.electroshock.tmdbmovie.data.database.Executor;
import irawan.electroshock.tmdbmovie.data.model.Movies;
import irawan.electroshock.tmdbmovie.databinding.HomeFragmentBinding;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment {

    private static final String TAG = "Home Data";
    private static final String apiKey = "9edf3fee29984e86d8be8170d810dd71";
    private HomeFragmentBinding binding;
    private MoviesViewModel mViewModel;
    private final ArrayList<Movies> movieList =  new ArrayList<>();

    @Inject
    MoviesViewModelFactory mViewModelFactory;

    @Inject
    Retrofit retrofit;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = HomeFragmentBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((BaseApplication) this.requireActivity().getApplication()).getNetComponent().inject(this);

        ServiceApi getServiceApi = retrofit.create(ServiceApi.class);

        mViewModel = new ViewModelProvider(this, mViewModelFactory).get(MoviesViewModel.class);

        binding.btnAsModel.setOnClickListener(v ->
                mViewModel.moviesGetDataObject().observe(getViewLifecycleOwner(),
                        movies -> {
                            for(int i =0;i< movies.size(); i++){
                                Log.d("Remote Data" ,String.valueOf(movies.get(i).getTitle()));
                                String title = movies.get(i).getTitle();
                                String posterPath = movies.get(i).getPosterPath();
                                String description = movies.get(i).getOverview();

                                Movies movie = new Movies();
                                movie.setTitle(title);
                                movie.setPosterPath(posterPath);
                                movie.setOverview(description);

                                movieList.add(movie);
                            }
                            initMovieFragmentView(movieList);
                        }
                )
        );

        binding.btnAsJson.setOnClickListener(v-> getServiceApi.getResultsAsJSON(apiKey).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                        if (response.body() != null) {
                            try {
                                String data = response.body().string();
                                Log.d(TAG, "onResponseJSON: "+data);

                                JSONFragment jsonFragment = new JSONFragment();
                                Bundle bundle = new Bundle();
                                bundle.putString("MoviesData", data);
                                jsonFragment.setArguments(bundle);

                                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                                fragmentManager.popBackStack();
                                FragmentTransaction transaction = fragmentManager.beginTransaction();

                                transaction.addToBackStack("Home Fragment");
                                transaction.replace(R.id.frameLayout, jsonFragment);
                                transaction.commit();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                        Log.d(TAG, "onFailure"+t.getMessage());
                    }
                })
        );

        binding.btnAsObservable.setOnClickListener(v->
                mViewModel.moviesObservableGetData().observe(
                        getViewLifecycleOwner(),
                        this::initMovieFragmentView)
        );

        binding.btnAsDatabase.setOnClickListener(v->
                Executor.IOThread(() -> {
                List<Movies> databaseData = mViewModel.getDatabaseData();
                for (int i = 0; i < databaseData.size();i++){
                    Log.d("Database", String.valueOf(databaseData.get(i).getTitle()));
                    String title = databaseData.get(i).getTitle();
                    String posterPath = databaseData.get(i).getPosterPath();
                    String description = databaseData.get(i).getOverview();

                    Movies movie = new Movies();
                    movie.setTitle(title);
                    movie.setPosterPath(posterPath);
                    movie.setOverview(description);

                    movieList.add(movie);
                }
                initMovieFragmentView(movieList);
            }));
    }

    private void initMovieFragmentView(ArrayList<Movies> moviesList) {
        FragmentManager fragmentManager = this.requireActivity().getSupportFragmentManager();
        fragmentManager.popBackStack();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        MoviesFragment moviesFragment = new MoviesFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("MoviesData", moviesList);
        moviesFragment.setArguments(bundle);

        transaction.addToBackStack("Home Fragment");
        transaction.replace(R.id.frameLayout, moviesFragment);
        transaction.commit();
    }
}