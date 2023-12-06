package interface_adapter.login;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;

public class LoginControllerTest {

    private LoginInputBoundary mockLoginInteractor;
    private LoginController loginController;

    @Test
    public void testExecute() {
        mockLoginInteractor = mock(LoginInputBoundary.class);
        loginController = new LoginController(mockLoginInteractor);
        String username = "testUser";
        String password = "testPassword";

        loginController.execute(username, password);

        // Verify that execute method in LoginInputBoundary was called with the correct arguments
        verify(mockLoginInteractor, times(1)).execute(argThat(
                input -> input.getUsername().equals(username) && input.getPassword().equals(password)
        ));
    }

    @Test
    public void testSetUsername() {
        mockLoginInteractor = mock(LoginInputBoundary.class);
        loginController = new LoginController(mockLoginInteractor);
        String username = "testUser";
        String password = "testPassword";

        loginController.setUsername(username, password);

        // Verify that setUsername method in LoginInputBoundary was called with the correct arguments
        verify(mockLoginInteractor, times(1)).setUsername(argThat(
                input -> input.getUsername().equals(username) && input.getPassword().equals(password)
        ));
    }
}