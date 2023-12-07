package interface_adapter.signup;

import interface_adapter.login.LoginController;
import org.junit.Test;
import use_case.login.LoginInputBoundary;
import use_case.signup.SignupInputBoundary;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

public class SignupControllerTest {

    private SignupInputBoundary mockSignupInteractor;
    private SignupController signupController;

    @Test
    public void testExecute() {
        mockSignupInteractor = mock(SignupInputBoundary.class);
        signupController = new SignupController(mockSignupInteractor);
        String username = "testUser";
        String password = "testPassword";
        String password1 = "testPassword";

        signupController.execute(username, password, password1);

        // Verify that execute method in LoginInputBoundary was called with the correct arguments
        verify(mockSignupInteractor, times(1)).execute(argThat(
                input -> input.getUsername().equals(username) && input.getPassword().equals(password) && input.getRepeatPassword().equals(password1)
        ));
    }

}