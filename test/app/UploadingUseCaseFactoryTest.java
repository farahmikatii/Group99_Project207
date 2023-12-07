
package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.uploading.UploadingViewModel;
import interface_adapter.uploads.UploadsViewModel;
import org.junit.Test;
import use_case.uploading.UploadingDataAccessInterface;
import view.UploadingView;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;

public class UploadingUseCaseFactoryTest {
    @Test
    public void createUploadingView_Success() {
        ViewManagerModel viewManagerModel = mock(ViewManagerModel.class);
        UploadingViewModel uploadingViewModel = mock(UploadingViewModel.class);
        ProfileViewModel profileViewModel = mock(ProfileViewModel.class);
        UploadsViewModel uploadsViewModel = mock(UploadsViewModel.class);
        UploadingDataAccessInterface uploadingDataAccessInterface = mock(UploadingDataAccessInterface.class);

        UploadingView uploadingView = UploadingUseCaseFactory.create(viewManagerModel, uploadingViewModel, profileViewModel, uploadsViewModel, uploadingDataAccessInterface);

        assertNotNull(uploadingView);
    }

    @Test
    public void createUploadingView_Failure() {
        ViewManagerModel viewManagerModel = mock(ViewManagerModel.class);
        UploadingViewModel uploadingViewModel = mock(UploadingViewModel.class);
        ProfileViewModel profileViewModel = mock(ProfileViewModel.class);
        UploadsViewModel uploadsViewModel = mock(UploadsViewModel.class);
        UploadingDataAccessInterface uploadingDataAccessInterface = mock(UploadingDataAccessInterface.class);

        UploadingView uploadingView = UploadingUseCaseFactory.create(viewManagerModel, uploadingViewModel, profileViewModel, uploadsViewModel, uploadingDataAccessInterface);

        assertNotNull(uploadingView);
    }
}