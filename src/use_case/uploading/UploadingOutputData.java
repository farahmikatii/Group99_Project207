package use_case.uploading;

import java.awt.*;

public class UploadingOutputData {
    final private String recipeName;

    final private String recipeIngredients;

    final private String recipeInstructions;
    final private Image recipeImage;

    public UploadingOutputData(String recipeName, String recipeIngredients, String recipeInstructions, Image recipeImage) {
        this.recipeName = recipeName;
        this.recipeIngredients = recipeIngredients;
        this.recipeInstructions = recipeInstructions;
        this.recipeImage = recipeImage;
    }

    public String getRecipeName(){return recipeName;}

    public String getRecipeIngredients(){return recipeIngredients;}

    public String getRecipeInstructions() {
        return recipeInstructions;
    }

    public Image getRecipeImage() {
        return recipeImage;
    }
}
