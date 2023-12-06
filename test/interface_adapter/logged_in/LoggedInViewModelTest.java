package interface_adapter.logged_in;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


public class LoggedInViewModelTest {

    @Test
    public void testInitialState() {
        LoggedInViewModel viewModel = new LoggedInViewModel();

        assertEquals("logged in", viewModel.getViewName());
        assertEquals("Logged In View", viewModel.TITLE_LABEL);
        assertEquals("Log out", viewModel.LOGOUT_BUTTON_LABEL);
        assertEquals("Search", viewModel.SEARCH_BUTTON_LABEL);
        assertEquals("Account", viewModel.ACCOUNT_BUTTON_LABEL);

        assertNotNull(viewModel.getState());
        assertEquals(" ", viewModel.getState().getUsername());
        assertNull(viewModel.getState().getUsernameError());
    }

    @Test
    public void testSetState() {
        LoggedInViewModel viewModel = new LoggedInViewModel();
        LoggedInState newState = new LoggedInState();
        newState.setUsername("testUser");

        viewModel.setState(newState);

        assertEquals("testUser", viewModel.getState().getUsername());
        assertNull(viewModel.getState().getUsernameError());
    }

    @Test
    public void testFirePropertyChanged() {
        LoggedInViewModel viewModel = new LoggedInViewModel();
        PropertyChangeListener listener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(listener);

        viewModel.firePropertyChanged();

        verify(listener, times(1)).propertyChange(any());
    }
}