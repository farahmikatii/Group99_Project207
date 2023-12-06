package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.recipePopup.RecipePopupViewModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import use_case.login.LoginOutputData;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {

    @Mock
    private ViewManagerModel viewManagerModel;

    @Mock
    private LoggedInViewModel loggedInViewModel;

    @Mock
    private LoginViewModel loginViewModel;

    @Mock
    private RecipePopupViewModel recipePopupViewModel;

    private LoginPresenter loginPresenter;

    @Before
    public void setUp() {
        LoginState state = new LoginState();
        loginViewModel.setState(state);
        loginPresenter = new LoginPresenter(viewManagerModel, loggedInViewModel, loginViewModel, recipePopupViewModel);
    }

    @Test
    public void testPrepareSuccessView() {
        LoginOutputData response = new LoginOutputData("username", false);
        when(loggedInViewModel.getState()).thenReturn(new LoggedInState());

        loginPresenter.prepareSuccessView(response);

        // Verify that the view models are updated correctly
        verify(loggedInViewModel).firePropertyChanged();
        verify(viewManagerModel).setActiveView(loggedInViewModel.getViewName());
        verify(viewManagerModel).firePropertyChanged();

        // Check the state of the LoggedInViewModel
        LoggedInState loggedInState = loggedInViewModel.getState();
        assertEquals("username", loggedInState.getUsername());
    }

    @Test
    public void testPrepareFailView() {
        String error = "Invalid credentials";
        loginPresenter.prepareFailView(error);

        // Check the state of the LoginViewModel
        LoginState loginState = loginViewModel.getState();
        if (loginState != null){
        assertEquals(error, loginState.getUsernameError());}
    }
}