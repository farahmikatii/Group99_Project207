
package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.saved.SavedViewModel;
import interface_adapter.uploading.UploadingViewModel;
import interface_adapter.uploads.UploadsViewModel;
import org.junit.Test;
import use_case.uploading.UploadingDataAccessInterface;
import view.ProfileView;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;


public class ProfileUseCaseFactoryTest {
    @Test
    public void createProfileView_Success() {
        ViewManagerModel viewManagerModel = mock(ViewManagerModel.class);
        ProfileViewModel profileViewModel = mock(ProfileViewModel.class);
        UploadingViewModel uploadingViewModel = mock(UploadingViewModel.class);
        UploadsViewModel uploadsViewModel = mock(UploadsViewModel.class);
        SavedViewModel savedViewModel = mock(SavedViewModel.class);
        UploadingDataAccessInterface uploadingDataAccessInterface = mock(UploadingDataAccessInterface.class);
        LoggedInViewModel loggedInViewModel = mock(LoggedInViewModel.class);

        ProfileView profileView = ProfileUseCaseFactory.create(
                viewManagerModel, profileViewModel, uploadingViewModel,
                uploadsViewModel, savedViewModel, uploadingDataAccessInterface, loggedInViewModel);

        assertNotNull(profileView);
    }

    @Test
    public void createProfileView_Failure() {
        ViewManagerModel viewManagerModel = mock(ViewManagerModel.class);
        ProfileViewModel profileViewModel = mock(ProfileViewModel.class);
        UploadingViewModel uploadingViewModel = mock(UploadingViewModel.class);
        UploadsViewModel uploadsViewModel = mock(UploadsViewModel.class);
        SavedViewModel savedViewModel = mock(SavedViewModel.class);
        UploadingDataAccessInterface uploadingDataAccessInterface = mock(UploadingDataAccessInterface.class);
        LoggedInViewModel loggedInViewModel = mock(LoggedInViewModel.class);


        ProfileView profileView = ProfileUseCaseFactory.create(
                viewManagerModel, profileViewModel, uploadingViewModel,
                uploadsViewModel, savedViewModel, uploadingDataAccessInterface, loggedInViewModel);

        assertNotNull(profileView);

    }
}