package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInController;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.recipePopup.RecipePopupViewModel;
import interface_adapter.search.SearchViewModel;
import interface_adapter.logged_in.LoggedInState;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;

import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.*;

public class LoggedInViewTest {

    private LoggedInController mockLoggedInController;

    private LoggedInViewModel mockLoggedInViewModel;

    private ViewManagerModel mockViewManagerModel;

    private ProfileViewModel mockProfileViewModel;

    private RecipePopupViewModel mockRecipePopupViewModel;

    private SearchViewModel mockSearchViewModel;

    private LoggedInView loggedInView;

    @Test
    public void actionPerformed(){

        //Simulate someone clicked on a recipe in the loggedinview
        loggedInView.actionPerformed(new ActionEvent(loggedInView.recipeImage, ActionEvent.ACTION_PERFORMED, "Olive Panini"));

        //Verify that the LoggedInController.execute method is called with expected parameters
        verify(mockLoggedInController, times(1)).execute("Olive Panini",
                "https://www.delish.com/cooking/recipe-ideas/recipes/a12803/olive-panini-recipe-sgt0710/"
                );
    }

    @Test
    public void propertyChange(){
        PropertyChangeEvent mockEvent = mock(PropertyChangeEvent.class);
        when(mockEvent.getNewValue()).thenReturn(mock(LoggedInState.class));

        //Simulate the propertyChange method being called
        loggedInView.propertyChange(mockEvent);

        //verify necessary methods are called
        verify(mockLoggedInViewModel, times(1)).getState();
    }
}
