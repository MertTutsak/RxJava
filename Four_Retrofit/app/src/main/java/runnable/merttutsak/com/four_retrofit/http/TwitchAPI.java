package runnable.merttutsak.com.four_retrofit.http;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import runnable.merttutsak.com.four_retrofit.http.apimodel.Twitch;

public interface TwitchAPI {

    @GET("games/top")
    Call<Twitch> getTopGames(@Header("Client-Id") String clientId);

}
