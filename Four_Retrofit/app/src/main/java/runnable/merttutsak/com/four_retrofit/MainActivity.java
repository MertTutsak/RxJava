package runnable.merttutsak.com.four_retrofit;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import runnable.merttutsak.com.four_retrofit.http.TwitchAPI;
import runnable.merttutsak.com.four_retrofit.http.apimodel.Top;
import runnable.merttutsak.com.four_retrofit.http.apimodel.Twitch;
import runnable.merttutsak.com.four_retrofit.root.App;

public class MainActivity extends AppCompatActivity {

    @Inject
    TwitchAPI twitchAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((App) getApplication()).getComponent().inject(this);

        Call<Twitch> call = twitchAPI.getTopGames("oinbj2uksskh4sydd9qwojrb5mitpd");

        call.enqueue(new Callback<Twitch>() {
            @Override
            public void onResponse(Call<Twitch> call, Response<Twitch> response) {
                List<Top> gamesList = response.body().getTop();

                for (int i = 0; i < gamesList.size(); i++) {

                    Log.i(MainActivity.this.getClass().getSimpleName(), ((i + 1) < 10 ? ("0" + (i + 1)) : (i+1)) + ") Games Name -> " + gamesList.get(i).getGame().getName());
                }
            }

            @Override
            public void onFailure(Call<Twitch> call, Throwable t) {
                Log.e(MainActivity.this.getClass().getSimpleName(), "Error:" + t.getMessage());

            }
        });
    }
}
