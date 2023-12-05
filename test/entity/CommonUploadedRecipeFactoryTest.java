package entity;

import org.junit.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.*;

public class CommonUploadedRecipeFactoryTest {

    private CommonUploadedRecipeFactory uploadedRecipeFactory = new CommonUploadedRecipeFactory();

    @Test
    public void testCreate() {
        // Given
        String name = "Uploaded Recipe";
        String ingredients = "Ingredient 1, Ingredient 2";
        String instructions = "Step 1: Do this, Step 2: Do that";
        Image recipeImage = new ImageIcon("path/to/image.jpg").getImage();

        // When
        UploadedRecipe createdRecipe = uploadedRecipeFactory.create(name, ingredients, instructions, recipeImage);

        // Then
        assertNotNull(createdRecipe);
        assertTrue(createdRecipe instanceof CommonUploadedRecipe);

        CommonUploadedRecipe commonUploadedRecipe = (CommonUploadedRecipe) createdRecipe;
        assertEquals(name, commonUploadedRecipe.getUploadedRecipeName());
        assertEquals(ingredients, commonUploadedRecipe.getIngredients());
        assertEquals(instructions, commonUploadedRecipe.getInstructions());
        assertEquals(recipeImage, commonUploadedRecipe.getImage());
    }

}