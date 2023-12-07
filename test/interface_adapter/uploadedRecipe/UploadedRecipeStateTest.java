package interface_adapter.uploadedRecipe;

import org.junit.Test;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class UploadedRecipeStateTest {
    @Test
    public void testCopyConstructor() {
        // Arrange
        UploadedRecipeState originalState = new UploadedRecipeState();
        originalState.setUploadedRecipeName("OriginalName");
        originalState.setUploadedRecipeIngredients("OriginalIngredients");
        originalState.setUploadedRecipeInstructions("OriginalInstructions");
        Image mockImage = mock(Image.class);
        originalState.setUploadedRecipeImage(mockImage);

        // Act
        UploadedRecipeState copiedState = new UploadedRecipeState(originalState);

        // Assert
        assertEquals(originalState.getUploadedRecipeName(), copiedState.getUploadedRecipeName());
        assertEquals(originalState.getUploadedRecipeIngredients(), copiedState.getUploadedRecipeIngredients());
        assertEquals(originalState.getUploadedRecipeInstructions(), copiedState.getUploadedRecipeInstructions());
        assertEquals(originalState.getUploadedRecipeImage(), copiedState.getUploadedRecipeImage());
    }

    @Test
    public void testDefaultConstructor() {
        // Arrange & Act
        UploadedRecipeState state = new UploadedRecipeState();

        // Assert
        // Add assertions based on your implementation
    }

    @Test
    public void testGetSetUploadedRecipe() {
        // Arrange
        UploadedRecipeState state = new UploadedRecipeState();
        Map<String, Object> mockRecipe = new HashMap<>();

        // Act
        state.setUploadedRecipe(mockRecipe);

        // Assert
        assertEquals(mockRecipe, state.getUploadedRecipe());
    }

    @Test
    public void testGetSetUploadedRecipeName() {
        // Arrange
        UploadedRecipeState state = new UploadedRecipeState();
        String recipeName = "TestRecipe";

        // Act
        state.setUploadedRecipeName(recipeName);

        // Assert
        assertEquals(recipeName, state.getUploadedRecipeName());
    }

    @Test
    public void testGetSetUploadedRecipeIngredients() {
        // Arrange
        UploadedRecipeState state = new UploadedRecipeState();
        String ingredients = "Ingredient1, Ingredient2";

        // Act
        state.setUploadedRecipeIngredients(ingredients);

        // Assert
        assertEquals(ingredients, state.getUploadedRecipeIngredients());
    }

    @Test
    public void testGetSetUploadedRecipeInstructions() {
        // Arrange
        UploadedRecipeState state = new UploadedRecipeState();
        String instructions = "Step 1. Step 2.";

        // Act
        state.setUploadedRecipeInstructions(instructions);

        // Assert
        assertEquals(instructions, state.getUploadedRecipeInstructions());
    }

    @Test
    public void testGetSetUploadedRecipeImage() {
        // Arrange
        UploadedRecipeState state = new UploadedRecipeState();
        Image mockImage = mock(Image.class);

        // Act
        state.setUploadedRecipeImage(mockImage);

        // Assert
        assertEquals(mockImage, state.getUploadedRecipeImage());
    }
}