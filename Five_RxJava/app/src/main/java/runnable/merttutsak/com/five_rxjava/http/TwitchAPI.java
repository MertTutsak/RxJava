package runnable.merttutsak.com.five_rxjava.http;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import runnable.merttutsak.com.five_rxjava.http.apimodel.Twitch;
import rx.Observable;

public interface TwitchAPI {

    @GET("games/top")
    Call<Twitch> getTopGames(@Header("Client-Id") String clientId);

    @GET("games/top")
    Observable<Twitch> getTopGamesObservable(@Header("Client-Id") String clientId);
}
