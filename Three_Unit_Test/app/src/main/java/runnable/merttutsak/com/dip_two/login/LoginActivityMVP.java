package runnable.merttutsak.com.dip_two.login;

public interface LoginActivityMVP {

    interface View {

        String getFirstName();

        String getLastName();

        void showUserNotAvaible();

        void showInputError();

        void showUserSavedMessage();

        void setFirstName(String firstName);

        void setLastName(String lastName);

    }

    interface Presenter {

        void setView(LoginActivityMVP.View view);

        void loginButtonClicked();

        void getCurrentUser();

        void saveUser();

    }

    interface Model {

        void createUser(String firstName, String lastName);

        User getUser();

    }
}
