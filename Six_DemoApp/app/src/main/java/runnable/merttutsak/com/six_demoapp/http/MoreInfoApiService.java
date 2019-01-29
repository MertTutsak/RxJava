package runnable.merttutsak.com.six_demoapp.http;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import runnable.merttutsak.com.six_demoapp.http.apimodel.OmdbApi;

public interface MoreInfoApiService {

    @GET("/")
    Observable<OmdbApi> getCountry(@Query("t") String title);
}
