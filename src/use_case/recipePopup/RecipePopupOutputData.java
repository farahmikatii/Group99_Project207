package use_case.recipePopup;

public class RecipePopupOutputData {
    private String recipeLabel;


    private String imageUrl;

    public RecipePopupOutputData(String recipeLabel, String imageUrl){
        this.recipeLabel = recipeLabel;
        this.imageUrl = imageUrl;
    }
    public String getRecipeLabel() {
        return recipeLabel;
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
