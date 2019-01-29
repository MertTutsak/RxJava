package runnable.merttutsak.com.dip_two.root;

import javax.inject.Singleton;

import dagger.Component;
import runnable.merttutsak.com.dip_two.login.LoginActivity;
import runnable.merttutsak.com.dip_two.login.LoginModule;

@Singleton
@Component(modules = {ApplicationModule.class,LoginModule.class})
public interface ApplicationComponent {
    void inject(LoginActivity target);
}
