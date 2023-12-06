package use_case.recipePopup;

public class RecipePopupInputData {
    private String recipeLabel;

    private String imageUrl;

    private String url;

    private String ingredients;


    public RecipePopupInputData(String recipeLabel, String imageUrl, String url, String ingredients){
        this.recipeLabel = recipeLabel;
        this.imageUrl = imageUrl;
        this.url = url;
        this.ingredients = ingredients;
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

    public String getIngredients() {return ingredients;}

    public void setIngredients(String ingredients) {this.ingredients = ingredients;}
}
