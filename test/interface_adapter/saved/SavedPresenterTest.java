package interface_adapter.saved;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.recipePopup.RecipePopupViewModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import use_case.login.LoginOutputData;
import use_case.saved.SavedOutputData;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SavedPresenterTest {
    @Mock
    private ViewManagerModel viewManagerModel;

    @Mock
    private SavedViewModel savedViewModel;

    @Mock
    private ProfileViewModel profileViewModel;

    private SavedPresenter savedPresenter;

    @Before
    public void setUp() {
        SavedState state = new SavedState();
        savedViewModel.setState(state);
        savedPresenter = new SavedPresenter(viewManagerModel, savedViewModel, profileViewModel);
    }

    @Test
    public void testPrepareSuccessView() {
        SavedOutputData response = new SavedOutputData("Hi", "", "");
        when(savedViewModel.getState()).thenReturn(new SavedState());

        savedPresenter.prepareSuccessView(response);

        // Verify that the view models are updated correctly
        verify(viewManagerModel).setActiveView(savedViewModel.getViewName());
        verify(viewManagerModel).firePropertyChanged();
    }

    @Test
    public void testPrepareFailView() {
        String error = "Invalid credentials";
        savedPresenter.prepareFailView(error);
        verify(savedViewModel).firePropertyChanged();

        // Check the state of the LoginViewModel
    }
}