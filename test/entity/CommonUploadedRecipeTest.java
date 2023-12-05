package entity;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.awt.*;

import static org.junit.Assert.*;

public class CommonUploadedRecipeTest {

    private CommonUploadedRecipe uploadedRecipe;
    private static final String NAME = "Recipe Name";
    private static final String INGREDIENTS = "Ingredient 1, Ingredient 2";
    private static final String INSTRUCTIONS = "Step 1. Do something";
    private static final Image IMAGE = null;  // Replace with an actual Image instance if needed

    public CommonUploadedRecipeTest(){
        this.uploadedRecipe = new CommonUploadedRecipe(
                NAME, INGREDIENTS, INSTRUCTIONS, IMAGE);
    }

    @Test
    public void testGetUploadedRecipeName() {
        assertEquals(NAME, uploadedRecipe.getUploadedRecipeName());
    }

    @Test
    public void testGetIngredients() {
        assertEquals(INGREDIENTS, uploadedRecipe.getIngredients());
    }

    @Test
    public void testGetInstructions() {
        assertEquals(INSTRUCTIONS, uploadedRecipe.getInstructions());
    }

    @Test
    public void testGetImage() {
        assertEquals(IMAGE, uploadedRecipe.getImage());
    }
}