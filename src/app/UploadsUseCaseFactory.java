package app;

import entity.CommonUploadedRecipeFactory;
import entity.UploadedRecipeFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.uploading.UploadingController;
import interface_adapter.uploading.UploadingPresenter;
import interface_adapter.uploading.UploadingViewModel;
import interface_adapter.uploads.UploadsViewModel;
import use_case.uploading.UploadingDataAccessInterface;
import use_case.uploading.UploadingInputBoundary;
import use_case.uploading.UploadingInteractor;
import use_case.uploading.UploadingOutputBoundary;
import view.UploadingView;
import view.UploadsView;

public class UploadsUseCaseFactory {
    private UploadsUseCaseFactory() {}

    public static UploadsView create(ViewManagerModel viewManagerModel, UploadingViewModel uploadingViewModel, UploadsViewModel uploadsViewModel, ProfileViewModel profileViewModel, UploadingDataAccessInterface uploadingDataAccessInterface){
        UploadingController uploadingController = createUploadsUseCase(viewManagerModel, uploadingViewModel, uploadsViewModel, profileViewModel, uploadingDataAccessInterface);
        return new UploadsView(uploadsViewModel,profileViewModel,viewManagerModel, uploadingController);
    }

    private static UploadingController createUploadsUseCase(ViewManagerModel viewManagerModel,UploadingViewModel uploadingViewModel, UploadsViewModel uploadsViewModel, ProfileViewModel profileViewModel, UploadingDataAccessInterface uploadingDataAccessInterface){
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