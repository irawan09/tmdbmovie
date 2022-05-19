package irawan.electroshock.tmdbmovie.data.api;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import irawan.electroshock.tmdbmovie.data.model.ObservableMovies;
import irawan.electroshock.tmdbmovie.data.model.Results;
import irawan.electroshock.tmdbmovie.data.model.ResultsObservable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceApi {
    @GET("movie/popular")
    Call<Results> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<ResponseBody> getResultsAsJSON(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Observable<ResultsObservable> getObservableMovies(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Single<ObservableMovies> getObservableMoviesWithPaging(
            @Query("api_key") String apiKey,
            @Query("page") int page
            );
}
