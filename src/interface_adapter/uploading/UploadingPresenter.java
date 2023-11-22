package interface_adapter.uploading;

import interface_adapter.ViewManagerModel;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import use_case.uploading.UploadingOutputBoundary;
import use_case.uploading.UploadingOutputData;

public class UploadingPresenter implements UploadingOutputBoundary {

    private final UploadingViewModel uploadingViewModel;
    private final ProfileViewModel profileViewModel;

    private ViewManagerModel viewManagerModel;

    public UploadingPresenter(ViewManagerModel viewManagerModel,
                              UploadingViewModel uploadingViewModel, ProfileViewModel profileViewModel){
        this.uploadingViewModel = uploadingViewModel;
        this.viewManagerModel = viewManagerModel;
        this.profileViewModel = profileViewModel;
    }
    @Override
    public void prepareSuccessView(UploadingOutputData recipe) {
        // On success, switch to profile view

        UploadingState uploadingState = uploadingViewModel.getState();
        ProfileState profileState = profileViewModel.getState();
        profileState.setUsername(recipe.getRecipeName());
        this.profileViewModel.setState(profileState);
        profileViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(profileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        // is this only supposed to consider the setrecipeerror?
        UploadingState uploadingState = uploadingViewModel.getState();
        uploadingState.setRecipeError(error);
        uploadingViewModel.firePropertyChanged();
    }
}
