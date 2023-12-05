package use_case.saved;

public class SavedInputData {

    private String recipeName;

    private String recipeURL;

    private String recipeImageURL;

    private String username;

    public SavedInputData(String recipeName, String recipeURL, String recipeImageURL, String username){
        this.recipeName = recipeName;
        this.recipeURL = recipeURL;
        this.recipeImageURL = recipeImageURL;
        this.username = username;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeURL() {
        return recipeURL;
    }

    public void setRecipeURL(String recipeURL) {
        this.recipeURL = recipeURL;
    }

    public String getRecipeImageURL(){
        return recipeImageURL;
    }

    public void setRecipeImageURL(String recipeImageURL){
        this.recipeImageURL = recipeImageURL;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }
}
