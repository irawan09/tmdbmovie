package irawan.electroshock.tmdbmovie.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import irawan.electroshock.tmdbmovie.api.ServiceApi;
import irawan.electroshock.tmdbmovie.model.Movies;
import irawan.electroshock.tmdbmovie.model.Results;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RepositoryModule {

    @Inject
    RepositoryModule(){
    }

    @Inject
    Retrofit retrofit;

    ServiceApi api;
    private static final String apiKey = "9edf3fee29984e86d8be8170d810dd71";
    private static final String TAG = "Repository";


    public void provideGetData(){
        List<Movies> data = null;

        api = retrofit.create(ServiceApi.class);
        api.getPopularMovies(apiKey).enqueue(new Callback<Results>() {
            @Override
            public void onResponse(@NonNull Call<Results> call, @NonNull Response<Results> response) {
                if (response.body() != null) {
                    for(int i =0; i < response.body().getResults().size();i++ ){
                        Log.d(TAG, "onResponse: "+response.body().getResults().get(i).getTitle());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Results> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });

    }
}
