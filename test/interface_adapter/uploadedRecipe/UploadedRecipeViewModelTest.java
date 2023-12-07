package interface_adapter.uploadedRecipe;

import org.junit.Test;

import java.beans.PropertyChangeListener;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UploadedRecipeViewModelTest {
    private PropertyChangeListener listener;

    private UploadedRecipeViewModel uploadedRecipeViewModel;

    @Test
    public void testInitialState() {
        UploadedRecipeViewModel uploadedRecipeViewModel1 = new UploadedRecipeViewModel();
        UploadedRecipeState initialState = uploadedRecipeViewModel1.getState();

        initialState.setUploadedRecipeName("Latte");
        uploadedRecipeViewModel1.setState(initialState);

        assertNotNull(initialState);
        assertEquals("Latte", initialState.getUploadedRecipeName());
    }

    @Test
    public void testFirePropertyChanged() {
        uploadedRecipeViewModel = new UploadedRecipeViewModel();
        listener = mock(PropertyChangeListener.class);
        uploadedRecipeViewModel.addPropertyChangeListener(listener);
        uploadedRecipeViewModel.firePropertyChanged();

        verify(listener, times(1)).propertyChange(any());
    }

    @Test
    public void testSetState() {
        uploadedRecipeViewModel = new UploadedRecipeViewModel();
        listener = mock(PropertyChangeListener.class);
        uploadedRecipeViewModel.addPropertyChangeListener(listener);
        UploadedRecipeState newState = new UploadedRecipeState();
        newState.setUploadedRecipeIngredients("123");

        uploadedRecipeViewModel.setState(newState);

        UploadedRecipeState updatedState = uploadedRecipeViewModel.getState();
        assertEquals("123", updatedState.getUploadedRecipeIngredients());
    }

}