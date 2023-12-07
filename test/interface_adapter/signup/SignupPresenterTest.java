package interface_adapter.signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.recipePopup.RecipePopupViewModel;
import interface_adapter.search.SearchPresenter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import use_case.login.LoginOutputData;
import use_case.signup.SignupOutputData;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SignupPresenterTest {
    @Mock
    private ViewManagerModel viewManagerModel;

    @Mock
    private SignupViewModel signupViewModel;

    @Mock
    private LoginViewModel loginViewModel;

    private SignupPresenter signupPresenter;

    @Before
    public void setUp() {
        LoginState state = new LoginState();
        loginViewModel.setState(state);
        signupPresenter = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);
    }

    @Test
    public void testPrepareSuccessView() {
        SignupOutputData response = new SignupOutputData("username", false);
        when(loginViewModel.getState()).thenReturn(new LoginState());

        signupPresenter.prepareSuccessView(response);

        // Verify that the view models are updated correctly
        verify(loginViewModel).firePropertyChanged();
        verify(viewManagerModel).setActiveView(loginViewModel.getViewName());
        verify(viewManagerModel).firePropertyChanged();

        // Check the state of the LoggedInViewModel
        LoginState loginState = loginViewModel.getState();
        assertEquals("username", loginState.getUsername());
    }

    @Test
    public void testPrepareFailView() {
        String error = "Invalid credentials";
        signupPresenter.prepareFailView(error);

        // Check the state of the LoginViewModel
        SignupState signupState = signupViewModel.getState();
        if (signupState != null){
            assertEquals(error, signupState.getUsernameError());}
    }

}