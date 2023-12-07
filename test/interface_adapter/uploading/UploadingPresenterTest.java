package interface_adapter.uploading;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.recipePopup.RecipePopupViewModel;
import interface_adapter.uploads.UploadsViewModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import use_case.login.LoginOutputData;
import use_case.uploading.UploadingOutputData;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UploadingPresenterTest {

    @Mock
    private ViewManagerModel viewManagerModel;

    @Mock
    private UploadsViewModel uploadsViewModel;

    @Mock
    private UploadingViewModel uploadingViewModel;

    @Mock
    private ProfileViewModel profileViewModel;

    private UploadingPresenter uploadingPresenter;

    @Before
    public void setUp() {
        UploadingState state = new UploadingState();
        uploadingViewModel.setState(state);
        uploadingPresenter = new UploadingPresenter(viewManagerModel, uploadingViewModel,profileViewModel, uploadsViewModel);
    }

    @Test
    public void testPrepareSuccessView() {
        UploadingOutputData response = new UploadingOutputData("Hi", "", "", null);
        when(profileViewModel.getState()).thenReturn(new ProfileState());

        uploadingPresenter.prepareSuccessView(response);

        // Verify that the view models are updated correctly
        verify(profileViewModel).firePropertyChanged();
        verify(viewManagerModel).setActiveView(profileViewModel.getViewName());
        verify(viewManagerModel).firePropertyChanged();
    }

}