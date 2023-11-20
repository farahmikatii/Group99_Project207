package entity;

import java.util.List;

public class CommonRecipeFactory implements RecipeFactory{
    public Recipe create(String name, String image, String recipeUrl){
        return new CommonRecipe(name, image, recipeUrl);
    }
}
