package use_case.loggedIn;

import entity.CommonRecipe;

public class LoggedInOutputData {
    private String recipeLabel;


    private String imageUrl;
    private CommonRecipe recipe;

    public LoggedInOutputData(String recipeLabel, String imageUrl, CommonRecipe recipe){
        this.recipeLabel = recipeLabel;
        this.imageUrl = imageUrl;
        this.recipe = recipe;
    }
    public String getRecipeLabel() {
        return recipe.getRecipeName();
    }

    public void setRecipeLabel(String recipeLabel) {
        this.recipeLabel = recipeLabel;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
