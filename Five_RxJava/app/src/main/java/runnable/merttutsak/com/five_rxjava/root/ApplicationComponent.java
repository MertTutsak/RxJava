package runnable.merttutsak.com.five_rxjava.root;

import javax.inject.Singleton;

import dagger.Component;
import runnable.merttutsak.com.five_rxjava.MainActivity;
import runnable.merttutsak.com.five_rxjava.http.ApiModule;

@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class})
public interface ApplicationComponent {

    void inject(MainActivity target);
}
