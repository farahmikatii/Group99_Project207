package use_case.recipePopup;

import entity.CommonRecipe;

import java.util.List;

public interface RecipePopupDataAccessInterface {
    List<CommonRecipe> returnRecipeList();
    CommonRecipe findRecipe(String label);

}
