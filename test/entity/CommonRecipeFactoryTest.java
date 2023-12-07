package entity;

import org.junit.Test;
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
        String ingredients = "Ingredient1";

        // When
        Recipe createdRecipe = recipeFactory.create(name, imagePath, recipeUrl, ingredients);

        // Then
        assertNotNull(createdRecipe);
        assertEquals(name, createdRecipe.getRecipeName());
        assertEquals(imagePath, createdRecipe.getImage());
        assertEquals(recipeUrl, createdRecipe.getRecipeUrl());
        assertEquals(ingredients, createdRecipe.getIngredients());
    }
}