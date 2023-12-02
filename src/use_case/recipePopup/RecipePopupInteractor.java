package use_case.recipePopup;

import app.RecipePopupUseCaseFactory;
import entity.CommonRecipe;

public class RecipePopupInteractor implements RecipePopupInputBoundary{
    final RecipePopupDataAccessInterface recipePopupDAO;
    final RecipePopupOutputBoundary recipePopupPresenter;



    public RecipePopupInteractor(RecipePopupDataAccessInterface recipePopupDAO, RecipePopupOutputBoundary recipePopupPresenter) {
        this.recipePopupDAO = recipePopupDAO;
        this.recipePopupPresenter = recipePopupPresenter;

    }


    @Override
    public void loadRecipeDisplay(RecipePopupInputData recipePopupInputData) {
        CommonRecipe recipe = recipePopupDAO.findRecipe(recipePopupInputData.getRecipeLabel());
        RecipePopupOutputData recipePopupOutputData = new RecipePopupOutputData(recipe.getRecipeName(),recipe.getRecipeUrl());
        recipePopupPresenter.prepareSuccessView(recipePopupOutputData);

    }
}
