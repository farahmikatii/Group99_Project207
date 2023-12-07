package interface_adapter.signup;

import org.junit.Test;

import java.beans.PropertyChangeListener;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SignupViewModelTest {
    private PropertyChangeListener listener;

    private SignupViewModel signupViewModel;

    @Test
    public void testInitialState() {
        SignupViewModel signupViewModel1 = new SignupViewModel();
        SignupState initialState = signupViewModel1.getState();

        initialState.setUsername("A");
        signupViewModel1.setState(initialState);

        assertNotNull(initialState);
        assertEquals("A", initialState.getUsername());
    }

    @Test
    public void testFirePropertyChanged() {
        signupViewModel = new SignupViewModel();
        listener = mock(PropertyChangeListener.class);
        signupViewModel.addPropertyChangeListener(listener);
        signupViewModel.firePropertyChanged();

        verify(listener, times(1)).propertyChange(any());
    }

    @Test
    public void testSetState() {
        signupViewModel = new SignupViewModel();
        listener = mock(PropertyChangeListener.class);
        signupViewModel.addPropertyChangeListener(listener);
        SignupState newState = new SignupState();
        newState.setPassword("123");

        signupViewModel.setState(newState);

        SignupState updatedState = signupViewModel.getState();
        assertEquals("123", updatedState.getPassword());
    }

}