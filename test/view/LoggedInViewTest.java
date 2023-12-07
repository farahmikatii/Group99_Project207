package view;

import app.LoginUseCaseFactory;
import data_access.CommonRecipeDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.recipePopup.RecipePopupViewModel;
import interface_adapter.resultSearch.ResultViewModel;
import interface_adapter.search.SearchViewModel;
import interface_adapter.signup.SignupViewModel;
import org.junit.jupiter.api.Test;
import use_case.loggedIn.LoggedInDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class LoggedInViewTest {

    private LoginViewModel mockLoginViewModel;
    private LoggedInView mockLoggedInViewModel;
    private ProfileViewModel mockProfileViewModel;
    private SearchViewModel mockSearchViewModel;
    private ViewManagerModel mockViewManagerModel;




    @Test
    public void actionPerformed(){


        LoggedInView mockLoggedInView = mock(LoggedInView.class);
        ActionEvent mockActionEvent = mock(ActionEvent.class);
        when(mockActionEvent.getActionCommand()).thenReturn("someCommand");
        mockLoggedInView.actionPerformed(mockActionEvent);
        verify(mockLoggedInView).actionPerformed(any(ActionEvent.class));

    }

    @Test
    public void propertyChange() throws Exception {

        ViewManagerModel viewManagerModel = new ViewManagerModel() ;
        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        UserFactory userFactory = new CommonUserFactory();
        LoginUserDataAccessInterface loginUserDataAccessInterface = new FileUserDataAccessObject("test/user1.csv", userFactory);
        SignupViewModel signupViewModel = new SignupViewModel();
        RecipePopupViewModel recipePopupViewModel = new RecipePopupViewModel();
        ProfileViewModel profileViewModel = new ProfileViewModel();
        SearchViewModel searchViewModel = new SearchViewModel();

        LoginState loginState = loginViewModel.getState();

        LoggedInView loggedInView = new LoggedInView(loggedInViewModel, viewManagerModel, profileViewModel, recipePopupViewModel, searchViewModel);

        PropertyChangeEvent mockPropertyChangeEvent = mock(PropertyChangeEvent.class);
        when(mockPropertyChangeEvent.getNewValue()).thenReturn(loginState);

        loggedInView.propertyChange(mockPropertyChangeEvent);
    }

}
