//package app;
//
//import entity.CommonUserFactory;
//import entity.UserFactory;
//import interface_adapter.ViewManagerModel;
//import interface_adapter.logged_in.LoggedInController;
//import interface_adapter.logged_in.LoggedInPresenter;
//import interface_adapter.logged_in.LoggedInViewModel;
//import interface_adapter.login.LoginController;
//import interface_adapter.login.LoginPresenter;
//import interface_adapter.login.LoginViewModel;
//import interface_adapter.profile.ProfileViewModel;
//import interface_adapter.recipePopup.RecipePopupController;
//import interface_adapter.recipePopup.RecipePopupState;
//import interface_adapter.recipePopup.RecipePopupViewModel;
//import use_case.loggedIn.LoggedInDataAccessInterface;
//import use_case.loggedIn.LoggedInInputBoundary;
//import use_case.loggedIn.LoggedInInteractor;
//import use_case.loggedIn.LoggedInOutputBoundary;
//import use_case.login.LoginInputBoundary;
//import use_case.login.LoginInteractor;
//import use_case.login.LoginOutputBoundary;
//import use_case.login.LoginUserDataAccessInterface;
//import use_case.recipePopup.RecipePopupDataAccessInterface;
//import view.LoggedInView;
//import view.LoginView;
//import view.RecipePopupView;
//
//import javax.swing.*;
//import java.io.IOException;
//
//public class LoggedInUseCaseFactory {
//    private LoggedInUseCaseFactory(){}
//    ViewManagerModel viewManagerModel;
//
//    LoggedInViewModel loggedInViewModel;
//    public static LoggedInView create(
//            ViewManagerModel viewManagerModel, RecipePopupViewModel recipePopupViewModel, LoggedInDataAccessInterface loggedInDataAccessInterface, LoggedInViewModel loggedInViewModel, ProfileViewModel profileViewModel){
//
//        try{
//            LoggedInController loggedInController = createLoggedinUseCase(loggedInViewModel, viewManagerModel, recipePopupViewModel, loggedInDataAccessInterface);
//            return new LoggedInView(loggedInViewModel, viewManagerModel, profileViewModel, recipePopupViewModel);
//        }
//        catch (IOException e){
//            JOptionPane.showMessageDialog(null, "Could not open user data file.");
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        return null;
//    }
//
//
//    private static LoggedInController createLoggedinUseCase(LoggedInViewModel loggedInViewModel, ViewManagerModel viewManagerModel,
//                                                         RecipePopupViewModel recipePopupViewModel,
//                                                         LoggedInDataAccessInterface loggedInDataAccessInterface) throws IOException {
//
//        // Notice how we pass this method's parameters to the Presenter.
//        LoggedInOutputBoundary loggedInOutputBoundary = new LoggedInPresenter(loggedInViewModel, viewManagerModel, recipePopupViewModel);
//        UserFactory userFactory = new CommonUserFactory();
//
//
//        LoggedInInputBoundary loggedInInteractor = new LoggedInInteractor(loggedInDataAccessInterface, loggedInOutputBoundary);
//
//        return new LoggedInController(loggedInInteractor);
//    }
//}
