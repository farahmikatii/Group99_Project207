package interface_adapter.saved;

import entity.CommonRecipe;
import org.junit.Test;

import java.beans.PropertyChangeListener;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SavedViewModelTest {
    private PropertyChangeListener listener;

    private SavedViewModel savedViewModel;

    @Test
    public void testInitialState() {
        SavedViewModel savedViewModel1 = new SavedViewModel();
        SavedState initialState = savedViewModel1.getState();
        CommonRecipe recipe = new CommonRecipe(null, "Hi", null, null);
        initialState.setRecipe(recipe);

        initialState.setRecipeImageURL(recipe);
        savedViewModel1.setState(initialState);

        assertNotNull(initialState);
        assertEquals("Hi", initialState.getRecipeImageURL());
    }

    @Test
    public void testFirePropertyChanged() {
        savedViewModel = new SavedViewModel();
        listener = mock(PropertyChangeListener.class);
        savedViewModel.addPropertyChangeListener(listener);
        savedViewModel.firePropertyChanged();

        verify(listener, times(1)).propertyChange(any());
    }

    @Test
    public void testSetState() {
        savedViewModel = new SavedViewModel();
        listener = mock(PropertyChangeListener.class);
        savedViewModel.addPropertyChangeListener(listener);
        SavedState newState = new SavedState();
        newState.setUsername("person");

        savedViewModel.setState(newState);

        SavedState savedState = savedViewModel.getState();
        assertEquals("person", savedState.getUsername());
    }

}