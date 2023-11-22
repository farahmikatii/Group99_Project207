package use_case.uploading;


import java.awt.*;

public class UploadingInputData {

    final private String recipeName;

    final private String recipeIngredients;

    final private String recipeInstructions;

    final private Image recipeImage;

    public UploadingInputData(String recipeName, String recipeIngredients, String recipeInstructions, Image recipeImage) {
        this.recipeName = recipeName;
        this.recipeIngredients = recipeIngredients;
        this.recipeInstructions = recipeInstructions;
        this.recipeImage = recipeImage;
    }

    String getRecipeName(){return recipeName;}

    String getRecipeIngredients(){return recipeIngredients;}

    String getRecipeInstructions(){return recipeInstructions;}

    Image getRecipeImage(){return recipeImage;}
}
