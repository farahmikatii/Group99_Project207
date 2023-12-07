package interface_adapter.recipePopup;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.saved.SavedPresenter;
import interface_adapter.saved.SavedState;
import interface_adapter.saved.SavedViewModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import use_case.recipePopup.RecipePopupOutputData;
import use_case.saved.SavedOutputData;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RecipePopupPresenterTest {
    @Mock
    private ViewManagerModel viewManagerModel;

    @Mock
    private RecipePopupViewModel recipePopupViewModel;

    @Mock
    private LoggedInViewModel loggedInViewModel;

    private RecipePopupPresenter recipePopupPresenter;

    @Before
    public void setUp() {
        RecipePopupState state = new RecipePopupState();
        recipePopupViewModel.setState(state);
        recipePopupPresenter = new RecipePopupPresenter(viewManagerModel, recipePopupViewModel, loggedInViewModel);
    }

    @Test
    public void testPrepareSuccessView() {
        RecipePopupOutputData response = new RecipePopupOutputData("Hi", "", "");
        when(recipePopupViewModel.getState()).thenReturn(new RecipePopupState());

        recipePopupPresenter.prepareSuccessView(response);

        // Verify that the view models are updated correctly
        verify(viewManagerModel).setActiveView(recipePopupViewModel.getViewName());
        verify(viewManagerModel).firePropertyChanged();
    }


}