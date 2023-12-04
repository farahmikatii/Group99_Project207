package use_case.recipePopup;

import interface_adapter.recipePopup.RecipePopupState;

public class RecipePopupInputData {
    private String recipeLabel;

    private String imageUrl;

    private String url;


    public RecipePopupInputData(String recipeLabel, String imageUrl, String url){
        this.recipeLabel = recipeLabel;
        this.imageUrl = imageUrl;
        this.url = url;
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

    public String getUrl() {return url;}
    public void setUrl(String url) {this.url = url;}
}
