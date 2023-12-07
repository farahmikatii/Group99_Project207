package view;

import app.UploadingUseCaseFactory;
import app.UploadsUseCaseFactory;
import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.uploadedRecipe.UploadedRecipeViewModel;
import interface_adapter.uploading.UploadingState;
import interface_adapter.uploading.UploadingViewModel;
import interface_adapter.uploads.UploadsViewModel;
import org.junit.jupiter.api.Test;
import use_case.uploading.UploadingDataAccessInterface;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

class UploadingViewTest {

    @Test
    void actionPerformed() {
        UploadingView mockUploadingView = mock(UploadingView.class);
        ActionEvent mockActionEvent = mock(ActionEvent.class);
        when(mockActionEvent.getActionCommand()).thenReturn("someCommand");

        mockUploadingView.actionPerformed(mockActionEvent);
        verify(mockUploadingView).actionPerformed(any(ActionEvent.class));

    }

    @Test
    void propertyChange() throws IOException {
        ViewManagerModel viewManagerModel = new ViewManagerModel() ;
        UploadingViewModel uploadingViewModel = new UploadingViewModel();
        UploadsViewModel uploadsViewModel = new UploadsViewModel();
        ProfileViewModel profileViewModel = new ProfileViewModel();
        UserFactory userFactory = new CommonUserFactory();
        UploadingDataAccessInterface uploadingDataAccessInterface = new FileUserDataAccessObject("test/user1.csv", userFactory);
        UploadedRecipeViewModel uploadedRecipeViewModel = new UploadedRecipeViewModel();

        UploadingState uploadingState = uploadingViewModel.getState();

        UploadingView uploadingView = UploadingUseCaseFactory.create(viewManagerModel,uploadingViewModel, profileViewModel, uploadsViewModel, uploadingDataAccessInterface);
        PropertyChangeEvent mockPropertyChangeEvent = mock(PropertyChangeEvent.class);
        when(mockPropertyChangeEvent.getNewValue()).thenReturn(uploadingState);

        uploadingView.propertyChange(mockPropertyChangeEvent);
    }
}