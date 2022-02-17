package irawan.electroshock.tmdbmovie;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import javax.inject.Inject;

import irawan.electroshock.tmdbmovie.data.api.ServiceApi;
import irawan.electroshock.tmdbmovie.di.module.MoviesRepositoryModule;
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

    @Inject
    MoviesRepositoryModule moviesRepositoryModule;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAsJSON = (Button) findViewById(R.id.btn_as_json);
        buttonAsResult = (Button) findViewById(R.id.btn_as_model);

        ((BaseApplication) getApplication()).getNetComponent().inject(this);

        ServiceApi getServiceApi = retrofit.create(ServiceApi.class);

        buttonAsResult.setOnClickListener(v ->
                moviesRepositoryModule.provideGetData().observe(this, movies ->
                        Log.d(TAG, movies.get(9).getTitle())
                )
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