//package view;
//
//import interface_adapter.ViewManagerModel;
//import interface_adapter.profile.ProfileViewModel;
//import interface_adapter.recipePopup.RecipePopupViewModel;
//import interface_adapter.saved.SavedController;
//import interface_adapter.saved.SavedState;
//import interface_adapter.saved.SavedViewModel;
//import interface_adapter.search.SearchState;
//import org.junit.jupiter.api.Test;
//
//import java.awt.event.ActionEvent;
//import java.beans.PropertyChangeEvent;
//
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.ArgumentMatchers.isNull;
//import static org.mockito.Mockito.*;
//
//public class SavedViewTest {
//
//    private ProfileViewModel mockProfileViewModel;
//
//    private ViewManagerModel mockViewManagerModel;
//
//    private SavedViewModel mockSavedViewModel;
//
//    private SavedState mockSavedState;
//
//    private RecipePopupViewModel mockRecipePopupViewModel;
//
//    private SavedController mockSavedController;
//
//    private SavedView savedView;
//
//    @Test
//    public void actionPerformed(){
//
//        savedView.actionPerformed(new ActionEvent(savedView.recipeImage, ActionEvent.ACTION_PERFORMED, "save"));
//
//        // Verify that the searchController.execute method was called with the expected parameters
//        verify(mockSavedController, times(1)).execute(eq(mockSavedState.getRecipeName()),
//                mockSavedState.getRecipeURL(), mockSavedState.getRecipeImageURL(), mockSavedState.getUsername());
//    }
//
//    @Test
//    public void propertyChange(){
//        PropertyChangeEvent mockEvent = mock(PropertyChangeEvent.class);
//        when(mockEvent.getNewValue()).thenReturn(mock(SearchState.class));
//
//        // Simulate the propertyChange method being called
//        savedView.propertyChange(mockEvent);
//
//        // Verify that the necessary methods were called
//
//        verify(mockSavedViewModel, times(1)).getState();
//    }
//}
