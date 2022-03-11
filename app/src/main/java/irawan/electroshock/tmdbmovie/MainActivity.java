package irawan.electroshock.tmdbmovie;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import javax.inject.Inject;

import irawan.electroshock.tmdbmovie.data.api.ServiceApi;
import irawan.electroshock.tmdbmovie.data.database.Executor;
import irawan.electroshock.tmdbmovie.data.model.MovieEntity;
import irawan.electroshock.tmdbmovie.data.model.Movies;
import irawan.electroshock.tmdbmovie.di.module.MoviesRepositoryModule;
import irawan.electroshock.tmdbmovie.presentation.fragment.MoviesViewModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String apiKey = "9edf3fee29984e86d8be8170d810dd71";
    private static final String TAG = "MainActivity";
    Button buttonAsJSON, buttonAsResult;

    @Inject
    Retrofit retrofit;

    MoviesViewModel moviesViewModel;

    @Inject
    MoviesRepositoryModule moviesRepositoryModule;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAsJSON = findViewById(R.id.btn_as_json);
        buttonAsResult = findViewById(R.id.btn_as_model);

        ((BaseApplication) getApplication()).getNetComponent().inject(this);

        ServiceApi getServiceApi = retrofit.create(ServiceApi.class);

        moviesViewModel = new ViewModelProvider(this).get(MoviesViewModel.class);

        buttonAsResult.setOnClickListener(v ->
//                moviesRepositoryModule.provideMoviesGetData().observe(this,
//                        movies ->
//                                Log.d("DATA",String.valueOf(movies.get(7).getTitle()))
//                )

                Executor.IOThread(() -> {
                    List<MovieEntity> databaseData = moviesRepositoryModule.provideGetDatabaseData();
                    for (int i = 0; i < databaseData.size();i++){
                     Log.d("DATA", String.valueOf(databaseData.get(i).getTitle()));
                    }
                })
        );

        buttonAsJSON.setOnClickListener(v -> getServiceApi.getResultsAsJSON(apiKey).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.body() != null) {
                    try {
                        Log.d(TAG, "onResponseJSON: "+response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure"+t.getMessage());
            }
        }));
    }
}