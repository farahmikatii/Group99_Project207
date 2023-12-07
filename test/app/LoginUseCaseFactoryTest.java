package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.recipePopup.RecipePopupViewModel;
import interface_adapter.signup.SignupViewModel;
import org.junit.Test;
import use_case.login.LoginUserDataAccessInterface;
import view.LoginView;


import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;

public class LoginUseCaseFactoryTest {
    @Test
    public void createLoginView_Success() {
        ViewManagerModel viewManagerModel = mock(ViewManagerModel.class);
        LoginViewModel loginViewModel = mock(LoginViewModel.class);
        LoggedInViewModel loggedInViewModel = mock(LoggedInViewModel.class);
        LoginUserDataAccessInterface userDataAccessObject = mock(LoginUserDataAccessInterface.class);
        SignupViewModel signupViewModel = mock(SignupViewModel.class);
        RecipePopupViewModel recipePopupViewModel = mock(RecipePopupViewModel.class);

        LoginView loginView = LoginUseCaseFactory.create(
                viewManagerModel, loginViewModel, loggedInViewModel,
                userDataAccessObject, signupViewModel, recipePopupViewModel);

        assertNotNull(loginView);

    }
    @Test
    public void createLoginView_Failure() {
        ViewManagerModel viewManagerModel = mock(ViewManagerModel.class);
        LoginViewModel loginViewModel = mock(LoginViewModel.class);
        LoggedInViewModel loggedInViewModel = mock(LoggedInViewModel.class);
        LoginUserDataAccessInterface userDataAccessObject = mock(LoginUserDataAccessInterface.class);
        SignupViewModel signupViewModel = mock(SignupViewModel.class);
        RecipePopupViewModel recipePopupViewModel = mock(RecipePopupViewModel.class);


        LoginView loginView = LoginUseCaseFactory.create(
                viewManagerModel, loginViewModel, loggedInViewModel,
                userDataAccessObject, signupViewModel, recipePopupViewModel);

        assertNotNull(loginView);

    }

}

