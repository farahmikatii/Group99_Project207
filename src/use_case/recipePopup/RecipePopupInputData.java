package use_case.recipePopup;

import interface_adapter.recipePopup.RecipePopupState;

public class RecipePopupInputData {
    private String recipeLabel;

    private String imageUrl;


    public RecipePopupInputData(String recipeLabel, String imageUrl){
        this.recipeLabel = recipeLabel;
        this.imageUrl = imageUrl;
    }

    public String getRecipeLabel(){
        return recipeLabel;
    }
    public void setRecipeLabel(String recipeLabel){
        this.recipeLabel = recipeLabel;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
