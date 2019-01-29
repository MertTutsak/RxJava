package runnable.merttutsak.com.dip_two.login;

public interface LoginRepository {

    User getUser();

    void saveUser(User user);
}
