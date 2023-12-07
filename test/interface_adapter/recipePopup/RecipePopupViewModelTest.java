package interface_adapter.recipePopup;

import entity.CommonRecipe;
import org.junit.Test;

import java.beans.PropertyChangeListener;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RecipePopupViewModelTest {

    private PropertyChangeListener listener;

    private RecipePopupViewModel recipePopupViewModel;

    @Test
    public void testInitialState() {
        RecipePopupViewModel recipePopupViewModel1 = new RecipePopupViewModel();
        RecipePopupState initialState = recipePopupViewModel1.getState();

        initialState.setRecipe(new CommonRecipe("", "", "", "food"));
        recipePopupViewModel1.setState(initialState);

        assertNotNull(initialState);
        assertEquals("", initialState.getRecipeLabel());
    }

    @Test
    public void testFirePropertyChanged() {
        recipePopupViewModel = new RecipePopupViewModel();
        listener = mock(PropertyChangeListener.class);
        recipePopupViewModel.addPropertyChangeListener(listener);
        recipePopupViewModel.firePropertyChanged();

        verify(listener, times(1)).propertyChange(any());
    }

    @Test
    public void testSetState() {
        recipePopupViewModel = new RecipePopupViewModel();
        listener = mock(PropertyChangeListener.class);
        recipePopupViewModel.addPropertyChangeListener(listener);
        RecipePopupState newState = new RecipePopupState();
        newState.setRecipe(new CommonRecipe("", "", "", "food"));

        recipePopupViewModel.setState(newState);

        RecipePopupState updatedState = recipePopupViewModel.getState();
        assertEquals("", updatedState.getRecipeLabel());
    }

    @Test
    public void testSetRecipeLabel() {
        recipePopupViewModel = new RecipePopupViewModel();
        listener = mock(PropertyChangeListener.class);
        String recipeLabel = "Test Recipe";
        recipePopupViewModel.setRecipeLabel(recipeLabel);

        assertEquals(recipeLabel, recipePopupViewModel.recipeLabel);
    }

}