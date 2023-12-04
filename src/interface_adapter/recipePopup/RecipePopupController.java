package interface_adapter.recipePopup;

import use_case.recipePopup.RecipePopupInputBoundary;
import use_case.recipePopup.RecipePopupInputData;

public class RecipePopupController {
    RecipePopupInputBoundary recipePopupInputBoundary;

    public RecipePopupController(RecipePopupInputBoundary recipePopupInputBoundary){
        this.recipePopupInputBoundary = recipePopupInputBoundary;
    }

    public void execute(String recipeLabel, String recipeUrl, String url){
        RecipePopupInputData recipePopupInputData = new RecipePopupInputData(recipeLabel, recipeUrl, url);
        recipePopupInputBoundary.loadRecipeDisplay(recipePopupInputData);

    }


}
