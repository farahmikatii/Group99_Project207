package entity;

import java.awt.*;

public class CommonUploadedRecipeFactory implements UploadedRecipeFactory{
    public UploadedRecipe create(String name, String ingredients, String instructions, Image recipeImage) {
        return new CommonUploadedRecipe(name, ingredients, instructions, recipeImage);
    }
}
