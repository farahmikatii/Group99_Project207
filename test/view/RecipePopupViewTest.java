package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.recipePopup.RecipePopupViewModel;
import interface_adapter.resultSearch.ResultState;
import interface_adapter.resultSearch.ResultViewModel;
import interface_adapter.saved.SavedViewModel;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;

import static org.mockito.Mockito.*;

public class RecipePopupViewTest {

    private ViewManagerModel mockViewManagerModel;

    private RecipePopupViewModel mockRecipePopupViewModel;

    private LoggedInViewModel mockLoggedInViewModel;

    private ResultViewModel mockResultViewModel;

    private ResultState mockResultState;

    private SavedViewModel mockSavedViewModel;

    private RecipePopupView recipePopupView;

    @Test
    public void propertyChange(){
        PropertyChangeEvent mockEvent = mock(PropertyChangeEvent.class);
        when(mockEvent.getNewValue()).thenReturn(mock(LoggedInState.class));

        //Simulate the propertyChange method being called
        recipePopupView.propertyChange(mockEvent);

        //verify necessary methods are called
        verify(mockRecipePopupViewModel, times(1)).getState();
    }
}
