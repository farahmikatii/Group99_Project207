package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.saved.SavedViewModel;
import interface_adapter.uploading.UploadingViewModel;
import interface_adapter.uploads.UploadsViewModel;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import java.awt.event.ActionEvent;

public class ProfileViewTest {

    private ProfileViewModel mockProfileViewModel;

    private ViewManagerModel mockViewManagerModel;

    private UploadingViewModel mockUploadingViewModel;

    private UploadsViewModel mockUploadsViewModel;

    private SavedViewModel mockSavedViewModel;

    private LoggedInViewModel mockLoggedInViewModel;

    private LoggedInState mockLoggedInState;

    private ProfileView profileView;

    @Test
    public void actionPerformed(){
        // Simulate a back button click
        profileView.actionPerformed(new ActionEvent(profileView.back, ActionEvent.ACTION_PERFORMED, "back"));
        verify(mockLoggedInViewModel, times(1)).setState(mockLoggedInState);
    }
}
