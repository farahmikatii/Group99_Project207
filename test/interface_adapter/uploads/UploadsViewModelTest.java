package interface_adapter.uploads;

import org.junit.Test;

import java.beans.PropertyChangeListener;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UploadsViewModelTest {
    private PropertyChangeListener listener;

    private UploadsViewModel uploadsViewModel;

    @Test
    public void testInitialState() {
        UploadsViewModel uploadsViewModel1 = new UploadsViewModel();
        UploadsState initialState = uploadsViewModel1.getState();

        initialState.setUploadedrecipesList(null);
        uploadsViewModel1.setState(initialState);

        assertNotNull(initialState);
        assertNull(initialState.getUploadedrecipesList());
    }

    @Test
    public void testFirePropertyChanged() {
        uploadsViewModel = new UploadsViewModel();
        listener = mock(PropertyChangeListener.class);
        uploadsViewModel.addPropertyChangeListener(listener);
        uploadsViewModel.firePropertyChanged();

        verify(listener, times(1)).propertyChange(any());
    }

    @Test
    public void testSetState() {
        uploadsViewModel = new UploadsViewModel();
        listener = mock(PropertyChangeListener.class);
        uploadsViewModel.addPropertyChangeListener(listener);
        UploadsState newState = new UploadsState();
        newState.setUploadedrecipesList(null);

        uploadsViewModel.setState(newState);

        UploadsState uploadsState = uploadsViewModel.getState();
        assertNull(uploadsState.getUploadedrecipesList());
    }

}