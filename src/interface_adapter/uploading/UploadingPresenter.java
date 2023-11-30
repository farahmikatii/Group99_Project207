package interface_adapter.uploading;

import interface_adapter.ViewManagerModel;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.uploadedRecipe.UploadedRecipeState;
import interface_adapter.uploadedRecipe.UploadedRecipeViewModel;
import interface_adapter.uploads.UploadsViewModel;
import use_case.uploading.UploadingOutputBoundary;
import use_case.uploading.UploadingOutputData;
import view.UploadedRecipeView;

public class UploadingPresenter implements UploadingOutputBoundary {

    private final UploadingViewModel uploadingViewModel;
    private final ProfileViewModel profileViewModel;
    private ViewManagerModel viewManagerModel;

    private final UploadsViewModel uploadsViewModel;

    public UploadingPresenter(ViewManagerModel viewManagerModel,
                              UploadingViewModel uploadingViewModel, ProfileViewModel profileViewModel, UploadsViewModel uploadsViewModel){
        this.uploadingViewModel = uploadingViewModel;
        this.viewManagerModel = viewManagerModel;
        this.profileViewModel = profileViewModel;
        this.uploadsViewModel = uploadsViewModel;
    }
    @Override
    public void prepareSuccessView(UploadingOutputData recipe) {
        // On success, switch to profile view

        UploadingState uploadingState = uploadingViewModel.getState();
        ProfileState profileState = profileViewModel.getState();
        profileState.setUsername(recipe.getRecipeName());
        profileViewModel.setState(profileState);
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

    @Override
    public void prepareUploadedRecipeView(UploadingOutputData uploadedRecipe){
        // when a user clicks on an uploaded recipe in UploadsView, creates a new page for that recipe

        UploadedRecipeView uploadedRecipeView = new UploadedRecipeView(uploadingViewModel, uploadedRecipe.getRecipeName(), uploadedRecipe.getRecipeIngredients(), uploadedRecipe.getRecipeInstructions(), uploadedRecipe.getRecipeImage(), uploadsViewModel);
        UploadedRecipeViewModel uploadedRecipeViewModel = new UploadedRecipeViewModel();
        uploadedRecipeViewModel.addPropertyChangeListener(uploadedRecipeView);

        UploadedRecipeState uploadedRecipeState = uploadedRecipeViewModel.getState();
        uploadedRecipeViewModel.setState(uploadedRecipeState);
        uploadedRecipeViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(uploadedRecipeView.getViewName());
        viewManagerModel.firePropertyChanged();

    }
}
