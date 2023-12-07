//package view;
//import app.SignupUseCaseFactory;
//import interface_adapter.ViewManagerModel;
//import interface_adapter.logged_in.LoggedInViewModel;
//import interface_adapter.login.LoginViewModel;
//import interface_adapter.search.SearchController;
//import interface_adapter.search.SearchState;
//import interface_adapter.search.SearchViewModel;
//import interface_adapter.signup.SignupController;
//import interface_adapter.signup.SignupViewModel;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//
//import javax.swing.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class SignupViewTest {
//    @BeforeEach
//    void setUp() {
//        ViewManagerModel viewManagerModel = new ViewManagerModel();
//        SignupViewModel signupViewModel = new SignupViewModel();
//        LoginViewModel loginViewModel = new LoginViewModel();
//
//        SignupController mockSignupController = mock(SignupController.class);
//        SignupViewModel mockSignupViewModel = mock(SignupViewModel.class);
//        LoginViewModel mockLoginViewModel = mock(LoginViewModel.class);
//        ViewManagerModel mockViewManagerModel = mock(ViewManagerModel.class);
//
//        SignupView signupView = new SignupView(mockSignupController, mockSignupViewModel, mockLoginViewModel, mockViewManagerModel);
//    }
//
//    @Test
//    void actionPerformed() {
//        ViewManagerModel viewManagerModel = new ViewManagerModel();
//        SignupViewModel signupViewModel = new SignupViewModel();
//        LoginViewModel loginViewModel = new LoginViewModel();
//
//
////        JButton signUpButton = signupView.getSignUpButton();
////
////        // Act
////        signUpButton.doClick();  // Simulate a button click
////
////        // Assert
////        verify(mockSignupController, times(1)).execute(any(), any(), any()); // Adjust as per your controller method parameters
//        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, )
//    }
//
//    @Test
//    void propertyChange() {
//    }
//}