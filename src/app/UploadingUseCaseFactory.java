package app;

import entity.CommonUploadedRecipeFactory;
import entity.UploadedRecipeFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.uploading.UploadingController;
import interface_adapter.uploading.UploadingPresenter;
import interface_adapter.uploading.UploadingViewModel;
import use_case.uploading.UploadingDataAccessInterface;
import use_case.uploading.UploadingInputBoundary;
import use_case.uploading.UploadingInteractor;
import use_case.uploading.UploadingOutputBoundary;
import view.UploadedRecipeView;

public class UploadingUseCaseFactory {

/*    private UploadingUseCaseFactory(){}

    public static UploadedRecipeView create(ViewManagerModel viewManagerModel, UploadingViewModel uploadingViewModel, ProfileViewModel profileViewModel, UploadingDataAccessInterface uploadingDataAccessInterface){
        UploadingController uploadingController = createUploadingUseCase(viewManagerModel, uploadingViewModel, profileViewModel, uploadingDataAccessInterface);
        return new UploadedRecipeView(uploadingController, uploadingViewModel);
    }

    private static UploadingController createUploadingUseCase(ViewManagerModel viewManagerModel, UploadingViewModel uploadingViewModel, ProfileViewModel profileViewModel, UploadingDataAccessInterface uploadingDataAccessInterface){
        UploadingOutputBoundary uploadingOutputBoundary = new UploadingPresenter(viewManagerModel, uploadingViewModel, profileViewModel);

        UploadedRecipeFactory uploadedRecipeFactory = new CommonUploadedRecipeFactory();

        UploadingInputBoundary uploadingInteractor = new UploadingInteractor(
                uploadingDataAccessInterface,
                uploadingOutputBoundary,
                uploadedRecipeFactory
        );
        return new UploadingController(uploadingInteractor);*/
   // }
}
