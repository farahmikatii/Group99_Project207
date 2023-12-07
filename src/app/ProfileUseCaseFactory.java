package app;

import entity.CommonUploadedRecipeFactory;
import entity.UploadedRecipeFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.saved.SavedViewModel;
import interface_adapter.uploading.UploadingController;
import interface_adapter.uploading.UploadingPresenter;
import interface_adapter.uploading.UploadingViewModel;
import interface_adapter.uploads.UploadsViewModel;
import use_case.uploading.UploadingDataAccessInterface;
import use_case.uploading.UploadingInputBoundary;
import use_case.uploading.UploadingInteractor;
import use_case.uploading.UploadingOutputBoundary;
import view.ProfileView;

public class ProfileUseCaseFactory {

    private ProfileUseCaseFactory(){}

    public static ProfileView create(ViewManagerModel viewManagerModel, ProfileViewModel profileViewModel, UploadingViewModel uploadingViewModel, UploadsViewModel uploadsViewModel, SavedViewModel savedViewModel, UploadingDataAccessInterface uploadingDataAccessInterface, LoggedInViewModel loggedInViewModel){
        UploadingController uploadingController = createUploadsUseCase(viewManagerModel, uploadingViewModel, profileViewModel, uploadsViewModel, uploadingDataAccessInterface);
        return new ProfileView(uploadingViewModel, profileViewModel, viewManagerModel, savedViewModel, uploadsViewModel, loggedInViewModel, uploadingController);
    }

    private static UploadingController createUploadsUseCase(ViewManagerModel viewManagerModel, UploadingViewModel uploadingViewModel, ProfileViewModel profileViewModel, UploadsViewModel uploadsViewModel, UploadingDataAccessInterface uploadingDataAccessInterface) {
        UploadingOutputBoundary uploadingOutputBoundary = new UploadingPresenter(viewManagerModel, uploadingViewModel, profileViewModel, uploadsViewModel);

        UploadedRecipeFactory uploadedRecipeFactory = new CommonUploadedRecipeFactory();

        UploadingInputBoundary uploadingInteractor = new UploadingInteractor(
                uploadingDataAccessInterface,
                uploadingOutputBoundary,
                uploadedRecipeFactory
        );

        return new UploadingController(uploadingInteractor);
    }
}

