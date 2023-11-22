package entity;

import java.awt.*;

public interface UploadedRecipeFactory {
    UploadedRecipe create(String name, String ingredients, String instructions, Image recipeImage);
}
