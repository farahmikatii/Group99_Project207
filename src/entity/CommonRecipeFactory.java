package entity;

public class CommonRecipeFactory implements RecipeFactory{
    public Recipe create(String name, String imagePath, String recipeUrl, String[] ingredients){
        return new CommonRecipe(name, imagePath, recipeUrl, ingredients);
    }
}
