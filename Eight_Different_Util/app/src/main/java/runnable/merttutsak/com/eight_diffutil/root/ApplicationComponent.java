package runnable.merttutsak.com.eight_diffutil.root;

import javax.inject.Singleton;

import dagger.Component;
import runnable.merttutsak.com.eight_diffutil.http.ApiModuleForName;
import runnable.merttutsak.com.eight_diffutil.topmovies.TopMoviesActivity;
import runnable.merttutsak.com.eight_diffutil.topmovies.TopMoviesModule;

@Singleton
@Component(modules = {ApplicationModule.class, ApiModuleForName.class, TopMoviesModule.class})
public interface ApplicationComponent {

    void inject(TopMoviesActivity target);
}
