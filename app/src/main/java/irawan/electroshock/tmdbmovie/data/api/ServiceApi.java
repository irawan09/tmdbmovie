package irawan.electroshock.tmdbmovie.data.api;

import irawan.electroshock.tmdbmovie.data.model.Results;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceApi {
    @GET("movie/popular")
    Call<Results> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<ResponseBody> getResultsAsJSON(@Query("api_key") String apiKey);
}
