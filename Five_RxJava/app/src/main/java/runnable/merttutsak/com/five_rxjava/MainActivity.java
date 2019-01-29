package runnable.merttutsak.com.five_rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import runnable.merttutsak.com.five_rxjava.http.TwitchAPI;
import runnable.merttutsak.com.five_rxjava.http.apimodel.Top;
import runnable.merttutsak.com.five_rxjava.http.apimodel.Twitch;
import runnable.merttutsak.com.five_rxjava.root.App;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Inject
    TwitchAPI twitchAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((App) getApplication()).getComponent().inject(this);

        twitchAPI.getTopGamesObservable("oinbj2uksskh4sydd9qwojrb5mitpd")
                .flatMap(new Func1<Twitch, Observable<Top>>() {
                    @Override
                    public Observable<Top> call(Twitch twitch) {
                        return Observable.from(twitch.getTop());
                    }
                })
                .flatMap(new Func1<Top, Observable<String>>() {
                    @Override
                    public Observable<String> call(Top top) {
                        return Observable.just(top.getGame().getName());
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(MainActivity.this.getClass().getSimpleName(), "Rx : Error:" + e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        Log.i(MainActivity.this.getClass().getSimpleName(), "Rx : Game Name -> " + s);
                    }
                });

        twitchAPI.getTopGamesObservable("oinbj2uksskh4sydd9qwojrb5mitpd")
                .flatMap(new Func1<Twitch, Observable<Top>>() {
                    @Override
                    public Observable<Top> call(Twitch twitch) {
                        return Observable.from(twitch.getTop());
                    }
                })
                .flatMap(new Func1<Top, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(Top top) {
                        return Observable.just(top.getGame().getPopularity());
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(MainActivity.this.getClass().getSimpleName(), "Rx : Error:" + e.getMessage());
                    }

                    @Override
                    public void onNext(Integer i) {
                        Log.i(MainActivity.this.getClass().getSimpleName(), "Rx : Game's Popularity -> " + i);
                    }
                });

        twitchAPI.getTopGamesObservable("oinbj2uksskh4sydd9qwojrb5mitpd")
                .flatMap(new Func1<Twitch, Observable<Top>>() {
                    @Override
                    public Observable<Top> call(Twitch twitch) {
                        return Observable.from(twitch.getTop());
                    }
                })
                .flatMap(new Func1<Top, Observable<String>>() {
                    @Override
                    public Observable<String> call(Top top) {
                        return Observable.just(top.getGame().getName());
                    }
                })
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return s.startsWith("C");
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(MainActivity.this.getClass().getSimpleName(), "Rx : Error:" + e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        Log.i(MainActivity.this.getClass().getSimpleName(), "Rx : Game Name-Filter(\"C\") -> " + s);
                    }
                });
    }
}
