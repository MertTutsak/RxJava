package runnable.merttutsak.com.eight_diffutil.http;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import runnable.merttutsak.com.eight_diffutil.http.apimodel.TopRated;

public interface MovieApiService {

    @GET("top_rated")
    Observable<TopRated> getTopRatedMovies(@Query("page") Integer page);
}
