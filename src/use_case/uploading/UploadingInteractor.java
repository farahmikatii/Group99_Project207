package use_case.uploading;

import entity.UploadedRecipe;
import entity.UploadedRecipeFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

            uploadingDataAccessInterface.saveUploadedRecipe(uploadedRecipe);

            UploadingOutputData uploadingOutputData = new UploadingOutputData(uploadedRecipe.getUploadedRecipeName(), uploadedRecipe.getIngredients(), uploadedRecipe.getInstructions(), uploadedRecipe.getImage());
            uploadingPresenter.prepareSuccessView(uploadingOutputData);
        }

    }

    @Override
    public List<Map<String, Object>> uploadedRecipes() {

        return uploadingDataAccessInterface.readUploadedRecipesFromCSV();

    }

    @Override
    public void executeRecipeView(UploadingInputData uploadingInputData) {
        UploadedRecipe uploadedRecipe = uploadedRecipeFactory.create(uploadingInputData.getRecipeName(), uploadingInputData.getRecipeIngredients(), uploadingInputData.getRecipeInstructions(), uploadingInputData.getRecipeImage());
        UploadingOutputData uploadingOutputData = new UploadingOutputData(uploadedRecipe.getUploadedRecipeName(), uploadedRecipe.getIngredients(), uploadedRecipe.getInstructions(), uploadedRecipe.getImage());
        uploadingPresenter.prepareUploadedRecipeView(uploadingOutputData);
    }
}
