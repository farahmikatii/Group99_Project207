package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.recipePopup.RecipePopupViewModel;
import interface_adapter.resultSearch.ResultViewModel;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;

import static org.mockito.Mockito.*;

public class ResultsViewTest {

    private ResultViewModel mockResultViewModel;

    private ViewManagerModel mockViewManagerModel;

    private RecipePopupViewModel mockRecipePopupViewModel;

    private LoggedInViewModel mockLoggedInViewModel;

    private LoggedInState mockLoggedInState;

    private ResultsView resultsView;

    @Test
    public void actionPerformed(){
        // Simulate a back button click
        resultsView.actionPerformed(new ActionEvent(resultsView.back, ActionEvent.ACTION_PERFORMED, "back"));
        verify(mockLoggedInViewModel, times(1)).setState(mockLoggedInState);
    }

    @Test
    public void propertyChange(){
        PropertyChangeEvent mockEvent = mock(PropertyChangeEvent.class);
        when(mockEvent.getNewValue()).thenReturn(mock(LoggedInState.class));

        //Simulate the propertyChange method being called
        resultsView.propertyChange(mockEvent);

        //verify necessary methods are called
        verify(mockLoggedInViewModel, times(1)).getState();
    }


}
