package entity;

import java.util.List;

public class CommonRecipeFactory implements RecipeFactory{
    public CommonRecipe create(String name, String imagePath, String recipeUrl){
        return new CommonRecipe(name, imagePath, recipeUrl);
    }
}
