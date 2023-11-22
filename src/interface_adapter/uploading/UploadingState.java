package interface_adapter.uploading;

import java.awt.*;

public class UploadingState {
    private String recipe = "";

    private String recipeError = null;

    private String ingredients = "";

    private String ingredientsError = null;

    private String instructions = "";

    private String instructionsError = null;

    private Image recipeImage = null;

    public UploadingState(UploadingState copy){
        recipe = copy.recipe;
        ingredients = copy.ingredients;
        instructions = copy.instructions;
        recipeImage = copy.recipeImage;
    }

    public UploadingState(){}

    public String getRecipe(){return recipe;}

    public String getRecipeError(){return recipeError;}

    public String getIngredientsError(){return ingredientsError;}

    public String getIngredients(){return ingredients;}

    public String getInstructions(){return instructions;}

    public String getInstructionsError(){return instructionsError;}

    public Image getRecipeImage(){return recipeImage;}

    public void setRecipe(String recipe){this.recipe = recipe;}
    public void setRecipeError(String recipeError){this.recipeError = recipeError;}

    public void setIngredients(String ingredients){this.ingredients = ingredients;}

    public void setIngredientsError(String ingredientsError){this.ingredientsError = ingredientsError;}

    public void setInstructions(String instructions){this.instructions = instructions;}

    public void setInstructionsError(String instructionsError) {this.instructionsError = instructionsError;}
}
