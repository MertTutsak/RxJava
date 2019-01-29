package runnable.merttutsak.com.four_retrofit.root;

import javax.inject.Singleton;

import dagger.Component;
import runnable.merttutsak.com.four_retrofit.MainActivity;
import runnable.merttutsak.com.four_retrofit.http.ApiModule;

@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class})
public interface ApplicationComponent {

    void inject(MainActivity target);
}
