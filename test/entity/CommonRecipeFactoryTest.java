package entity;

import org.testng.annotations.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

public class CommonRecipeFactoryTest {

    private CommonRecipeFactory recipeFactory = new CommonRecipeFactory();

    @Test
    public void testCreate() {
        // Given
        String name = "Recipe Name";
        String imagePath = "/path/to/image.jpg";
        String recipeUrl = "https://example.com/recipe";

        // When
        Recipe createdRecipe = recipeFactory.create(name, imagePath, recipeUrl);

        // Then
        assertNotNull(createdRecipe);
        assertEquals(name, createdRecipe.getRecipeName());
        assertEquals(imagePath, createdRecipe.getImage());
        assertEquals(recipeUrl, createdRecipe.getRecipeUrl());
    }
}