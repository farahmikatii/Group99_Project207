package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import org.junit.Test;
import use_case.signup.SignupUserDataAccessInterface;
import view.SignupView;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;

public class SignupUseCaseFactoryTest {
    @Test
    public void createSignupView_Success() {
        ViewManagerModel viewManagerModel = mock(ViewManagerModel.class);
        LoginViewModel loginViewModel = mock(LoginViewModel.class);
        SignupViewModel signupViewModel = mock(SignupViewModel.class);
        SignupUserDataAccessInterface userDataAccessObject = mock(SignupUserDataAccessInterface.class);

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);

        assertNotNull(signupView);

    }

    @Test
    public void createSignupView_Failure() {
        ViewManagerModel viewManagerModel = mock(ViewManagerModel.class);
        LoginViewModel loginViewModel = mock(LoginViewModel.class);
        SignupViewModel signupViewModel = mock(SignupViewModel.class);
        SignupUserDataAccessInterface userDataAccessObject = mock(SignupUserDataAccessInterface.class);


        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);

        assertNotNull(signupView);


    }
}