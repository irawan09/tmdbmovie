package irawan.electroshock.tmdbmovie.di.module;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import irawan.electroshock.tmdbmovie.data.api.ServiceApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;

@Module
public class NetworkModule {

    String baseUrl;

    public NetworkModule(String baseUrl){
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    Gson provideGson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }
}
