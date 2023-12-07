package app;

import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.recipePopup.RecipePopupViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;
import interface_adapter.login.LoginViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import view.LoginView;

public class LoginUseCaseFactory {

    LoginUseCaseFactory(){}

    public static LoginView create(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, LoggedInViewModel loggedInViewModel, LoginUserDataAccessInterface userDataAccessObject, SignupViewModel signupViewModel, RecipePopupViewModel recipePopupViewModel){

        LoginController loginController = createLoginUseCase(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject, recipePopupViewModel);
        return new LoginView(loginController, loginViewModel, signupViewModel, viewManagerModel);

    }

    private static LoginController createLoginUseCase(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, LoggedInViewModel loggedInViewModel, LoginUserDataAccessInterface userDataAccessObject, RecipePopupViewModel recipePopupViewModel){
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loggedInViewModel, loginViewModel, recipePopupViewModel);

        UserFactory userFactory = new CommonUserFactory();

        LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        return new LoginController(loginInteractor);
    }
}
