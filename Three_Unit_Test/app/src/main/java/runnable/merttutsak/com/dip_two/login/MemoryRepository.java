package runnable.merttutsak.com.dip_two.login;

public class MemoryRepository implements LoginRepository {

    private User user;

    @Override
    public User getUser() {

        if (user == null) {
            User user = new User("Mert", "Tutsak");
            user.setId(0);
            return user;
        }

        return user;
    }

    @Override
    public void saveUser(User user) {

        if (user == null) {
            user = getUser();
        }

        this.user = user;
    }
}
