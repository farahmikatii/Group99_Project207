package interface_adapter.profile;

import org.junit.Test;

import java.beans.PropertyChangeListener;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProfileViewModelTest {

    private ProfileViewModel profileViewModel;
    private PropertyChangeListener listener;

    @Test
    public void testInitialState() {
        ProfileViewModel profileViewModel = new ProfileViewModel();
        ProfileState initialState = profileViewModel.getState();

        assertNotNull(initialState);
        assertEquals("", initialState.getUsername());
    }

    @Test
    public void testSetState() {
        profileViewModel = new ProfileViewModel();
        listener = mock(PropertyChangeListener.class);
        profileViewModel.addPropertyChangeListener(listener);
        ProfileState newState = new ProfileState();
        newState.setUsername("testUser");

        profileViewModel.setState(newState);

        ProfileState updatedState = profileViewModel.getState();
        assertEquals("testUser", updatedState.getUsername());
    }

    @Test
    public void testFirePropertyChanged() {
        profileViewModel = new ProfileViewModel();
        listener = mock(PropertyChangeListener.class);
        profileViewModel.addPropertyChangeListener(listener);
        profileViewModel.firePropertyChanged();

        verify(listener, times(1)).propertyChange(any());
    }

}