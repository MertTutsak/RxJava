package runnable.merttutsak.com.six_demoapp.root;

import javax.inject.Singleton;

import dagger.Component;
import runnable.merttutsak.com.six_demoapp.http.ApiModuleForInfo;
import runnable.merttutsak.com.six_demoapp.http.ApiModuleForName;
import runnable.merttutsak.com.six_demoapp.topmovies.TopMoviesActivity;
import runnable.merttutsak.com.six_demoapp.topmovies.TopMoviesModule;

@Singleton
@Component(modules = {ApplicationModule.class, ApiModuleForInfo.class, ApiModuleForName.class, TopMoviesModule.class})
public interface ApplicationComponent {

    void inject(TopMoviesActivity target);
}
