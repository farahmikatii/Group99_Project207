package interface_adapter.login;

import java.beans.PropertyChangeListener;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LoginViewModelTest {

    private LoginViewModel loginViewModel;
    private PropertyChangeListener listener;

    @Test
    public void testInitialState() {
        loginViewModel = new LoginViewModel();
        listener = mock(PropertyChangeListener.class);
        loginViewModel.addPropertyChangeListener(listener);
        LoginState state = loginViewModel.getState();
        assertEquals("", state.getUsername());
        assertNull(state.getUsernameError());
        assertEquals("", state.getPassword());
        assertNull(state.getPasswordError());
    }

    @Test
    public void testSetState() {
        loginViewModel = new LoginViewModel();
        listener = mock(PropertyChangeListener.class);
        loginViewModel.addPropertyChangeListener(listener);
        LoginState newState = new LoginState();
        newState.setUsername("testUser");
        newState.setUsernameError("Username error");
        newState.setPassword("testPassword");
        newState.setPasswordError("Password error");

        loginViewModel.setState(newState);

        LoginState updatedState = loginViewModel.getState();
        assertEquals("testUser", updatedState.getUsername());
        assertEquals("Username error", updatedState.getUsernameError());
        assertEquals("testPassword", updatedState.getPassword());
        assertEquals("Password error", updatedState.getPasswordError());
    }

    @Test
    public void testFirePropertyChanged() {
        loginViewModel = new LoginViewModel();
        listener = mock(PropertyChangeListener.class);
        loginViewModel.addPropertyChangeListener(listener);
        loginViewModel.firePropertyChanged();

        verify(listener, times(1)).propertyChange(any());
    }
}