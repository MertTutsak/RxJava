package runnable.merttutsak.com.six_demoapp.root;

import android.app.Application;

import runnable.merttutsak.com.six_demoapp.http.ApiModuleForInfo;
import runnable.merttutsak.com.six_demoapp.http.ApiModuleForName;
import runnable.merttutsak.com.six_demoapp.topmovies.TopMoviesModule;

public class App extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(App.this))
                .apiModuleForName(new ApiModuleForName())
                .apiModuleForInfo(new ApiModuleForInfo())
                .topMoviesModule(new TopMoviesModule())
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
