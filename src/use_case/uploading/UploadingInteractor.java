package use_case.uploading;

import entity.UploadedRecipe;
import entity.UploadedRecipeFactory;

public class UploadingInteractor implements UploadingInputBoundary {

    final UploadingDataAccessInterface uploadingDataAccessInterface;

    final UploadingOutputBoundary uploadingPresenter;

    final UploadedRecipeFactory uploadedRecipeFactory;

    public UploadingInteractor(UploadingDataAccessInterface uploadingDataAccessInterface, UploadingOutputBoundary uploadingPresenter, UploadedRecipeFactory uploadedRecipeFactory){
        this.uploadingDataAccessInterface = uploadingDataAccessInterface;
        this.uploadingPresenter = uploadingPresenter;
        this.uploadedRecipeFactory = uploadedRecipeFactory;
    }
    @Override
    public void execute(UploadingInputData uploadingInputData) {
        if (uploadingInputData.getRecipeName().isEmpty()){
            uploadingPresenter.prepareFailView("Enter a recipe name");
        } else if (uploadingInputData.getRecipeIngredients().isEmpty()){
            uploadingPresenter.prepareFailView("Enter recipe ingredients");
        } else if (uploadingInputData.getRecipeInstructions().isEmpty()){
            uploadingPresenter.prepareFailView("Enter recipe instructions");
        } else {

            UploadedRecipe uploadedRecipe = uploadedRecipeFactory.create(uploadingInputData.getRecipeName(), uploadingInputData.getRecipeIngredients(), uploadingInputData.getRecipeInstructions(), uploadingInputData.getRecipeImage());

            //TODO: save the uploaded recipe as a data access object

            UploadingOutputData uploadingOutputData = new UploadingOutputData(uploadedRecipe.getUploadedRecipeName(), uploadedRecipe.getIngredients(), uploadedRecipe.getInstructions(), uploadedRecipe.getImage());
            uploadingPresenter.prepareSuccessView(uploadingOutputData);
        }

    }
}
