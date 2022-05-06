package irawan.electroshock.tmdbmovie;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import irawan.electroshock.tmdbmovie.data.api.ServiceApi;
import irawan.electroshock.tmdbmovie.data.database.Executor;
import irawan.electroshock.tmdbmovie.data.model.Movies;
import irawan.electroshock.tmdbmovie.data.model.MoviesObject;
import irawan.electroshock.tmdbmovie.data.model.ResultsObject;
import irawan.electroshock.tmdbmovie.databinding.ActivityMainBinding;
import irawan.electroshock.tmdbmovie.di.module.MoviesUseCase;
import irawan.electroshock.tmdbmovie.presentation.fragment.MoviesViewModel;
import irawan.electroshock.tmdbmovie.presentation.fragment.MoviesViewModelFactory;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String apiKey = "9edf3fee29984e86d8be8170d810dd71";
    private static final String TAG = "MainActivity";

    @Inject
    Retrofit retrofit;

    MoviesViewModel moviesViewModel;

    @Inject
    MoviesViewModelFactory mViewModelFactory;

    @Inject
    MoviesUseCase moviesUseCase;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ((BaseApplication) getApplication()).getNetComponent().inject(this);
        ServiceApi getServiceApi = retrofit.create(ServiceApi.class);
        moviesViewModel = new ViewModelProvider(this, mViewModelFactory).get(MoviesViewModel.class);

        binding.btnAsModel.setOnClickListener(v ->
                moviesViewModel.moviesGetDataObject().observe(this,
                        movies -> {
                            for(int i =0;i< movies.size(); i++){
                                Log.d("Remote Data" ,String.valueOf(movies.get(i).getTitle()));
                            }
                        })
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
                moviesViewModel.moviesObservableGetData().observe(this, movies -> {

                    for(int i=0;i<movies.size(); i++){
                        Log.d("Observable data", String.valueOf(movies.get(i).getTitle()));
                    }
                })
        );

        binding.btnAsDatabase.setOnClickListener(v->{
            Executor.IOThread(() -> {
                    List<Movies> databaseData = moviesViewModel.getDatabaseData();
                    for (int i = 0; i < databaseData.size();i++){
                        Log.d("Database", String.valueOf(databaseData.get(i).getTitle()));
                    }
                });
        });
    }

}