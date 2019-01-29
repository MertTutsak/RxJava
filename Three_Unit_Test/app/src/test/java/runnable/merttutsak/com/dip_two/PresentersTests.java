package runnable.merttutsak.com.dip_two;

import org.junit.Before;
import org.junit.Test;

import runnable.merttutsak.com.dip_two.login.LoginActivityMVP;
import runnable.merttutsak.com.dip_two.login.LoginActivityPresenter;
import runnable.merttutsak.com.dip_two.login.User;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class PresentersTests {

    LoginActivityMVP.Model mockLoginModel;
    LoginActivityMVP.View mockView;
    LoginActivityPresenter presenter;
    User user;

    @Before
    public void setup() {
        mockLoginModel = mock(LoginActivityMVP.Model.class);

        user = new User("Mert", "Tutsak");

        mockView = mock(LoginActivityMVP.View.class);

        presenter = new LoginActivityPresenter(mockLoginModel);

        presenter.setView(mockView);

    }

    @Test
    public void loadTheUserFromTheRepositoryWhenValidUserIsPresent() {
        when(mockLoginModel.getUser()).thenReturn(user);

        presenter.getCurrentUser();

        //verify model interactions
        verify(mockLoginModel, times(1)).getUser();

        //verify view interactions
        verify(mockView, times(1)).setFirstName("Mert");
        verify(mockView, times(1)).setLastName("Tutsak");
        verify(mockView, never()).showUserSavedMessage();
    }

    @Test
    public void justAnInteractionWithView() {
        presenter.getCurrentUser();

        verify(mockView, times(1)).showUserNotAvaible();
        verifyNoMoreInteractions(mockView);// It verify it that not be the other interaction.
    }

    @Test
    public void shouldShowErrorMessageWhenUserIsNull() {
        when(mockLoginModel.getUser()).thenReturn(null);

        presenter.getCurrentUser();

        //verify model interactions
        verify(mockLoginModel, times(1)).getUser();

        //verify view interactions
        verify(mockView, never()).setFirstName("Mert");
        verify(mockView, never()).setLastName("Tutsak");
        verify(mockView, times(1)).showUserNotAvaible();
    }

    @Test
    public void shouldCreateErrorMessageIfFieldAreEmpty() {
        when(mockView.getFirstName()).thenReturn("");

        presenter.saveUser();

        verify(mockView, times(1)).getFirstName();
        verify(mockView, never()).getLastName();
        verify(mockView, times(1)).showInputError();

        //Noew tell mockView to return a valur for first name and an empty last name
        when(mockView.getFirstName()).thenReturn("Dana");
        when(mockView.getLastName()).thenReturn("");

        presenter.saveUser();

        verify(mockView, times(2)).getFirstName(); // Called two times now, once before, and once now
        verify(mockView, times(1)).getLastName(); // Only called once
        verify(mockView, times(2)).showInputError(); // Called two times now, once before and once now
    }

    @Test
    public void shouldBeAbleToSaveAValidUser() {

        when(mockView.getFirstName()).thenReturn("Mert");
        when(mockView.getLastName()).thenReturn("Tutsak");

        presenter.saveUser();

        //Called two more times in the saveUser call.
        verify(mockView, times(2)).getFirstName();
        verify(mockView, times(2)).getFirstName();

        //Make sure the repository saved the user
        verify(mockLoginModel, times(1)).createUser("Mert", "Tutsak");

        //Make sure that the view showed the user saved message
        verify(mockView, times(1)).showUserSavedMessage();
    }

}
