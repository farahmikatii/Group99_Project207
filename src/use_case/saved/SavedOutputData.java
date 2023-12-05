package use_case.saved;

public class SavedOutputData {

    final private String recipeName;

    final private String recipeURL;

    final private String recipeImageURL;

    public SavedOutputData(String recipeName, String recipeURL, String recipeImageURL){
        this.recipeName = recipeName;
        this.recipeURL = recipeURL;
        this.recipeImageURL = recipeImageURL;
    }

    public String getRecipeName(){
        return recipeName;
    }

    public String getRecipeURL(){
        return recipeURL;
    }

    public String getRecipeImageURL(){
        return recipeImageURL;
    }
}
