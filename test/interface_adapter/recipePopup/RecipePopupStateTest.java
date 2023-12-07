package interface_adapter.recipePopup;

import entity.CommonRecipe;
import org.junit.Test;

import static org.junit.Assert.*;


public class RecipePopupStateTest {
    @Test
    public void testRecipePopupStateCopyConstructor() {
        // Arrange
        CommonRecipe testRecipe = new CommonRecipe("TestRecipe", "test.jpg", "http://example.com", new String[]{"Ingredient1", "Ingredient2"});
        RecipePopupState originalState = new RecipePopupState();
        originalState.setUsername("testUser");
        originalState.setRecipe(testRecipe);

        // Act
        RecipePopupState copyState = new RecipePopupState(originalState);

        // Assert
        assertEquals(originalState.getUsername(), copyState.getUsername());
        assertEquals(originalState.getRecipeLabel(), copyState.getRecipeLabel());
        assertEquals(originalState.getRecipeUrl(), copyState.getRecipeUrl());
        assertEquals(originalState.getImageUrl(), copyState.getImageUrl());
        assertEquals(originalState.getUsername(), copyState.getUsername());
        assertEquals(originalState.getIngredients(), copyState.getIngredients());
    }

    @Test
    public void testInitialization() {
        // Create an instance of CommonRecipe for testing
        CommonRecipe commonRecipe = new CommonRecipe("Test Recipe", "http://example.com/image.jpg", "http://example.com", null);

        // Create RecipePopupState with the CommonRecipe instance
        RecipePopupState state = new RecipePopupState();
        state.setRecipe(commonRecipe);

        // Check if the RecipePopupState is properly initialized
        assertNotNull(state.getRecipe());
        assertEquals("Test Recipe", state.getRecipeLabel());
        assertEquals("http://example.com", state.getRecipeUrl());
        assertEquals("http://example.com/image.jpg", state.getImageUrl());
        assertEquals(" ", state.getUsername());
        assertEquals("Test Recipe", state.getRecipeLabel());
        assertEquals(" ", state.getUsername());
        assertNull(state.getIngredients()); // Assuming getIngredients() returns null when not set

        // Update the state with more values
        state.setIngredients(commonRecipe);

        // Check if the state is updated correctly
        assertArrayEquals(commonRecipe.getIngredients(), state.getIngredients());
    }

    @Test
    public void testComingFrom() {
        // Create RecipePopupState
        RecipePopupState state = new RecipePopupState();

        // Set and check the "comingFrom" value
        state.setComingFrom("Test Source");
        assertEquals("Test Source", state.getComingFrom());
    }

    @Test
    public void testSetRecipeLabel() {
        // Create RecipePopupState
        RecipePopupState state = new RecipePopupState();

        CommonRecipe recipe = new CommonRecipe("Recipe 123", "", "", null);

        // Set and check the recipe label
        state.setRecipe(recipe);
        assertEquals("Recipe 123", state.getRecipeLabel());
    }

    @Test
    public void testSetRecipeUrl() {
        // Create RecipePopupState
        RecipePopupState state = new RecipePopupState();

        // Set and check the recipe URL
        state.setRecipe(new CommonRecipe("", "", "http://example.com", null));
        assertEquals("http://example.com", state.getRecipeUrl());
    }

    @Test
    public void testSetImageUrl() {
        // Create RecipePopupState
        RecipePopupState state = new RecipePopupState();

        // Set and check the image URL
        state.setRecipe(new CommonRecipe("", "http://example.com/image.jpg", "", null));
        assertEquals("http://example.com/image.jpg", state.getImageUrl());
    }

    @Test
    public void testSetUsername() {
        // Create RecipePopupState
        RecipePopupState state = new RecipePopupState();

        // Set and check the username
        state.setUsername("testUser");
        assertEquals("testUser", state.getUsername());
    }

    @Test
    public void testSetIngredients() {
        // Create RecipePopupState
        RecipePopupState state = new RecipePopupState();

        // Create an instance of CommonRecipe for testing
        CommonRecipe commonRecipe = new CommonRecipe("Test Recipe", "", "", new String[]{"food"});

        // Set and check the ingredients
        state.setRecipe(commonRecipe);
        assertArrayEquals(commonRecipe.getIngredients(), state.getIngredients());
    }
}