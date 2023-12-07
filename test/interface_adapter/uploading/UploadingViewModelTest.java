package interface_adapter.uploading;

import org.junit.Test;

import java.beans.PropertyChangeListener;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UploadingViewModelTest {
    private PropertyChangeListener listener;

    private UploadingViewModel uploadingViewModel;

    @Test
    public void testInitialState() {
        UploadingViewModel uploadingViewModel1 = new UploadingViewModel();
        UploadingState initialState = uploadingViewModel1.getState();

        initialState.setIngredients("A");
        uploadingViewModel1.setState(initialState);

        assertNotNull(initialState);
        assertEquals("A", initialState.getIngredients());
    }

    @Test
    public void testFirePropertyChanged() {
        uploadingViewModel = new UploadingViewModel();
        listener = mock(PropertyChangeListener.class);
        uploadingViewModel.addPropertyChangeListener(listener);
        uploadingViewModel.firePropertyChanged();

        verify(listener, times(1)).propertyChange(any());
    }

    @Test
    public void testSetState() {
        uploadingViewModel = new UploadingViewModel();
        listener = mock(PropertyChangeListener.class);
        uploadingViewModel.addPropertyChangeListener(listener);
        UploadingState newState = new UploadingState();
        newState.setIngredients(null);

        uploadingViewModel.setState(newState);

        UploadingState uploadingState = uploadingViewModel.getState();
        assertEquals("", uploadingState.getIngredients());
    }

}