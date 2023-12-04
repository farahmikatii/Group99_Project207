package interface_adapter.recipePopup;

import entity.CommonRecipe;

public class RecipePopupState {
    private String recipeLabel = null;

    private String imageUrl = null;


    private String recipeUrl = null;

    private CommonRecipe recipe;


    public RecipePopupState(RecipePopupState copy){
        recipe = copy.recipe;
    }
    public RecipePopupState(){}

    public CommonRecipe getRecipe() {
        return recipe;
    }

    public void setRecipe(CommonRecipe recipe) {
        this.recipe = recipe;
    }

    public String getRecipeLabel(){
        return recipe.getRecipeName();
    }


    public void setRecipeLabel(CommonRecipe recipe){
        this.recipeLabel = recipe.getRecipeName();
    }
    public String getRecipeUrl() {
        return recipe.getRecipeUrl();
    }

    public void setRecipeUrl(CommonRecipe recipe) {
        this.recipeUrl = recipe.getRecipeUrl();
    }

    public String getImageUrl() {
        return recipe.getImage();
    }
    public void setImageUrl(CommonRecipe imageUrl) {
        this.imageUrl = recipe.getImage();
    }
}
