package interface_adapter.recipePopup;

import entity.CommonRecipe;

public class RecipePopupState {
    private String recipeLabel = null;

    private String imageUrl = null;


    private String recipeUrl = null;

    private String username = " ";
    private CommonRecipe recipe;

    private String comingFrom = null;


    public RecipePopupState(RecipePopupState copy){
        recipe = copy.recipe;
        username = copy.username;
    }
    public RecipePopupState(){}

    public CommonRecipe getRecipe() {
        return recipe;
    }

    public String getComingFrom() {
        return comingFrom;
    }

    public void setComingFrom(String comingFrom) {
        this.comingFrom = comingFrom;
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
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }
}
