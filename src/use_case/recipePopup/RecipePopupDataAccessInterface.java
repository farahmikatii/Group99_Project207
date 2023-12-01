package use_case.recipePopup;

import entity.CommonRecipe;

import java.util.List;

public interface RecipePopupDataAccessInterface {
    List<CommonRecipe> returnRecipeList(int diff);
    CommonRecipe findRecipe(String label);

}
