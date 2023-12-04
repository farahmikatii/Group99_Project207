package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.saved.SavedViewModel;
import interface_adapter.uploading.UploadingViewModel;
import interface_adapter.uploads.UploadsController;
import interface_adapter.uploads.UploadsPresenter;
import interface_adapter.uploads.UploadsViewModel;
import use_case.uploads.UploadsDataAccessInterface;
import use_case.uploads.UploadsInputBoundary;
import use_case.uploads.UploadsInteractor;
import use_case.uploads.UploadsOutputBoundary;
import view.ProfileView;
import view.UploadsView;

/*
public class ProfileUseCaseFactory {

    private ProfileUseCaseFactory(){}

    public static ProfileView create(ViewManagerModel viewManagerModel, ProfileViewModel profileViewModel, UploadingViewModel uploadingViewModel, UploadsViewModel uploadsViewModel, SavedViewModel savedViewModel, UploadsDataAccessInterface uploadsDataAccessInterface, LoggedInViewModel loggedInViewModel){
        UploadsController uploadsController = createProfileUseCase(viewManagerModel, uploadingViewModel, profileViewModel, uploadsViewModel, uploadsDataAccessInterface);
        return new ProfileView(uploadingViewModel, profileViewModel, viewManagerModel, savedViewModel, uploadsViewModel, loggedInViewModel, uploadsController);
    }

    private static UploadsController createProfileUseCase(ViewManagerModel viewManagerModel, UploadingViewModel uploadingViewModel, ProfileViewModel profileViewModel, UploadsViewModel uploadsViewMode, UploadsDataAccessInterface uploadsDataAccessInterface){
        UploadsOutputBoundary uploadsOutputBoundary = new UploadsPresenter();

        UploadsInputBoundary uploadsInteractor = new UploadsInteractor(
                uploadsDataAccessInterface,
                uploadsOutputBoundary
        );
        return new UploadsController(uploadsInteractor);
    }
}
*/
