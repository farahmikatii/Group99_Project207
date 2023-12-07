package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;

import static org.mockito.Mockito.*;

public class LoginViewTest {

    private LoginViewModel mockLoginViewModel;

    private ViewManagerModel mockViewManagerModel;

    private LoginController mockLoginController;

    private LoginView loginView;

    @Test
    public void actionPerformed(){
        loginView.usernameInputField.setText("testUser");
        loginView.passwordInputField.setText("testPassword");

        // Simulate a login button click
        loginView.actionPerformed(new ActionEvent(loginView.login, ActionEvent.ACTION_PERFORMED, "login"));

        // Verify that the loginController.execute method was called with the expected parameters
        verify(mockLoginController, times(1)).execute(eq("testUser"), "testPassword");
    }

    @Test
    public void propertyChange(){
        PropertyChangeEvent mockEvent = mock(PropertyChangeEvent.class);
        when(mockEvent.getNewValue()).thenReturn(mock(LoginState.class));

        loginView.propertyChange(mockEvent);

        verify(mockLoginViewModel, times(1)).getState();
    }

}
