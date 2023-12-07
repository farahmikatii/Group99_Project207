package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.uploadedRecipe.UploadedRecipeViewModel;
import interface_adapter.uploading.UploadingViewModel;
import interface_adapter.uploads.UploadsViewModel;
import org.junit.Test;
import use_case.uploading.UploadingDataAccessInterface;
import view.UploadsView;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;

public class UploadsUseFactoryTest {
    @Test
    public void createUploadsView_Success() {
        ViewManagerModel viewManagerModel = mock(ViewManagerModel.class);
        UploadingViewModel uploadingViewModel = mock(UploadingViewModel.class);
        UploadsViewModel uploadsViewModel = mock(UploadsViewModel.class);
        ProfileViewModel profileViewModel = mock(ProfileViewModel.class);
        UploadingDataAccessInterface uploadingDataAccessInterface = mock(UploadingDataAccessInterface.class);
        UploadedRecipeViewModel uploadedRecipeViewModel = mock(UploadedRecipeViewModel.class);

        UploadsView uploadsView = UploadsUseCaseFactory.create(viewManagerModel, uploadingViewModel, uploadsViewModel, profileViewModel, uploadingDataAccessInterface, uploadedRecipeViewModel);

        assertNotNull(uploadsView);
    }

    @Test
    public void createUploadsView_Failure() {
        ViewManagerModel viewManagerModel = mock(ViewManagerModel.class);
        UploadingViewModel uploadingViewModel = mock(UploadingViewModel.class);
        UploadsViewModel uploadsViewModel = mock(UploadsViewModel.class);
        ProfileViewModel profileViewModel = mock(ProfileViewModel.class);
        UploadingDataAccessInterface uploadingDataAccessInterface = mock(UploadingDataAccessInterface.class);
        UploadedRecipeViewModel uploadedRecipeViewModel = mock(UploadedRecipeViewModel.class);


        UploadsView uploadsView = UploadsUseCaseFactory.create(viewManagerModel, uploadingViewModel, uploadsViewModel, profileViewModel, uploadingDataAccessInterface, uploadedRecipeViewModel);

        assertNotNull(uploadsView);
    }
}