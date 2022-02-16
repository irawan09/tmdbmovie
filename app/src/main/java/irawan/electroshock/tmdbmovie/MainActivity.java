package irawan.electroshock.tmdbmovie;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.sql.SQLOutput;

import javax.inject.Inject;

import irawan.electroshock.tmdbmovie.api.ServiceApi;
import irawan.electroshock.tmdbmovie.model.Results;
import irawan.electroshock.tmdbmovie.repository.RepositoryModule;
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
    RepositoryModule repositoryModule;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAsJSON = (Button) findViewById(R.id.btn_as_json);
        buttonAsResult = (Button) findViewById(R.id.btn_as_model);

        ((BaseApplication) getApplication()).getNetComponent().inject(this);

        ServiceApi getServiceApi = retrofit.create(ServiceApi.class);

        buttonAsResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repositoryModule.provideGetData();
            }
        });

//        buttonAsResult.setOnClickListener(v -> getServiceApi.getPopularMovies(apiKey).enqueue(new Callback<Results>() {
//            @Override
//            public void onResponse(@NonNull Call<Results> call, @NonNull Response<Results> response) {
//                if (response.body() != null) {
//                    for(int i =0; i < response.body().getResults().size();i++ ){
////                        Log.d(TAG, "onResponse: "+response.body().getResults().get(i).getTitle());
//                        RepositoryModule repo = new RepositoryModule();
//                        repo.provideGetData();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<Results> call, @NonNull Throwable t) {
//                Log.d(TAG, "onFailure: "+t.getMessage());
//            }
//        }));

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