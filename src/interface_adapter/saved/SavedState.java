package interface_adapter.saved;

public class SavedState {

    private String username;

    private String recipeName;

    private String recipeNameError;

    private String recipeURL;

    private String recipeURLError;

    private String recipeImageURL;

    private String recipeImageURLError;


    public SavedState(SavedState copy){
        recipeName = copy.recipeName;
        recipeURL = copy.recipeURL;
        recipeImageURL = copy.recipeImageURL;

    }

    public SavedState(){}

    public String getUsername(){
        return username;
    }

    public String getRecipeName(){
        return recipeName;
    }

    public String getRecipeNameError(){
        return recipeNameError;
    }

    public String getRecipeURL(){
        return recipeURL;
    }

    public String getRecipeURLError(){
        return recipeURLError;
    }

    public String getRecipeImageURL(){
        return recipeImageURL;
    }

    public String getRecipeImageURLError(){
        return recipeImageURLError;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setRecipeName(String recipeName){
        this.recipeName = recipeName;
    }

    public void setRecipeURL(String recipeURL){
        this.recipeURL = recipeURL;
    }

    public void setRecipeImageURL(String recipeImageURL){
        this.recipeImageURL = recipeImageURL;
    }

    public void setRecipeNameError(String recipeNameError) {
        this.recipeNameError = recipeNameError;
    }

    public void setRecipeURLError(String recipeURLError) {
        this.recipeURLError = recipeURLError;
    }

    public void setRecipeImageURLError(String recipeImageURLError){
        this.recipeImageURLError = recipeImageURLError;
    }
}
