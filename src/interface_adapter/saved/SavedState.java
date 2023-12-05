package interface_adapter.saved;

import entity.CommonRecipe;

public class SavedState {

    private String username;


    private String recipeName = null;

    private String recipeNameError;

    private String recipeURL = null;

    private String recipeURLError;

    private String recipeImageURL = null;



    private CommonRecipe recipe;


    private String recipeImageURLError;


    public SavedState(SavedState copy){

        recipe = copy.recipe;


    }

    public SavedState(){}
    public CommonRecipe getRecipe() {
        return recipe;
    }

    public void setRecipe(CommonRecipe recipe) {
        this.recipe = recipe;
    }

    public String getUsername(){
        return username;
    }

    public String getRecipeName(){
        return recipe.getRecipeName();
    }

    public String getRecipeNameError(){
        return recipeNameError;
    }

    public String getRecipeURL(){
        return recipeURL;
    }

    public String getRecipeURLError(){
        return recipeURLError;
    }

    public String getRecipeImageURL(){
        return recipeImageURL;
    }

    public String getRecipeImageURLError(){
        return recipeImageURLError;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setRecipeName(CommonRecipe recipe){
        this.recipeName = recipe.getRecipeName();
    }

    public void setRecipeURL(String recipeURL){
        this.recipeURL = recipeURL;
    }

    public void setRecipeImageURL(String recipeImageURL){
        this.recipeImageURL = recipeImageURL;
    }

    public void setRecipeNameError(String recipeNameError) {
        this.recipeNameError = recipeNameError;
    }

    public void setRecipeURLError(String recipeURLError) {
        this.recipeURLError = recipeURLError;
    }


    public void setRecipeImageURLError(String recipeImageURLError){
        this.recipeImageURLError = recipeImageURLError;
    }
}
