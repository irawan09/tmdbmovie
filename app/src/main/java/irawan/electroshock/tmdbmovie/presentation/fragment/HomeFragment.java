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
import irawan.electroshock.tmdbmovie.di.module.MoviesUseCase;
import irawan.electroshock.tmdbmovie.presentation.adapter.MoviesAdapter;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment {

    private static final String TAG = "Home";
    private static final String apiKey = "9edf3fee29984e86d8be8170d810dd71";
    private HomeFragmentBinding binding;
    private MoviesViewModel mViewModel;
    private MoviesAdapter adapter;
    private ArrayList<Movies> movieList =  new ArrayList<>();

    @Inject
    MoviesViewModelFactory mViewModelFactory;

    @Inject
    MoviesUseCase moviesUseCase;

    @Inject
    Retrofit retrofit;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
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
//                                Log.d("Remote data: ", String.valueOf(movieList));
                            }
//                            adapter.updateList(movieList);
                            initMovieFragmentView();
                        }
                )
        );

        binding.btnAsJson.setOnClickListener(v-> getServiceApi.getResultsAsJSON(apiKey).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                        if (response.body() != null) {
                            try {
                                Log.d(TAG, "onResponseJSON: "+response.body().string());
                                String data = response.body().string();

//                                Intent i = new Intent(FirstScreen.this, SecondScreen.class);
//                                String strName = null;
//                                i.putExtra("STRING_I_NEED", strName);
//
//                                String newString;
//                                if (savedInstanceState == null) {
//                                    Bundle extras = getIntent().getExtras();
//                                    if(extras == null) {
//                                        newString= null;
//                                    } else {
//                                        newString= extras.getString("STRING_I_NEED");
//                                    }
//                                } else {
//                                    newString= (String) savedInstanceState.getSerializable("STRING_I_NEED");
//                                }

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
                mViewModel.moviesObservableGetData().observe(getViewLifecycleOwner(), movies -> {

                    for(int i=0;i<movies.size(); i++){
                        Log.d("Observable data", String.valueOf(movies.get(i).getTitle()));
                    }
                })
        );

        binding.btnAsDatabase.setOnClickListener(v->{
            Executor.IOThread(() -> {
                    List<Movies> databaseData = mViewModel.getDatabaseData();
                    for (int i = 0; i < databaseData.size();i++){
                        Log.d("Database", String.valueOf(databaseData.get(i).getTitle()));
                    }
                });
        });
    }

    private void initMovieFragmentView() {
        MoviesFragment moviesFragment = new MoviesFragment();
        FragmentManager fragmentManager = this.requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.frameLayout, moviesFragment);
        transaction.commit();

//        binding.moviesFrameLayout.setLayoutManager(new LinearLayoutManager(getContext()));
//        adapter = new MoviesAdapter(movieList, getContext());
//        binding.moviesFrameLayout.setAdapter(adapter);
    }
}