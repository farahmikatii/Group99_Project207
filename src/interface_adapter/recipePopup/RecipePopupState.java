package interface_adapter.recipePopup;

public class RecipePopupState {
    private String recipeLabel;

    private String imageUrl;


    public RecipePopupState(RecipePopupState copy){
        recipeLabel = copy.recipeLabel;
        imageUrl = copy.imageUrl;
    }
    public RecipePopupState(){}

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
