package use_case.recipePopup;

public class RecipePopupOutputData {
    private String recipeLabel;


    private String imageUrl;

    private String[] ingredients;

    public RecipePopupOutputData(String recipeLabel, String imageUrl, String[] ingredients){
        this.recipeLabel = recipeLabel;
        this.imageUrl = imageUrl;
        this.ingredients = ingredients;
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

    public String[] getIngredients() {return ingredients;}

    public void setIngredients(String[] ingredients) {this.ingredients = ingredients;}
}
