package interface_adapter.uploading;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class UploadingStateTest {
    @Test
    public void testCopyConstructor() {
        // Arrange
        UploadingState originalState = new UploadingState();
        originalState.setRecipe("OriginalRecipe");
        originalState.setIngredients("OriginalIngredients");
        originalState.setInstructions("OriginalInstructions");
        Image mockImage = mock(Image.class);
        originalState.setRecipeImage(mockImage);

        // Act
        UploadingState copiedState = new UploadingState(originalState);

        // Assert
        assertEquals(originalState.getRecipe(), copiedState.getRecipe());
        assertEquals(originalState.getIngredients(), copiedState.getIngredients());
        assertEquals(originalState.getInstructions(), copiedState.getInstructions());
        assertEquals(originalState.getRecipeImage(), copiedState.getRecipeImage());
    }

    @Test
    public void testGetSetRecipe() {
        // Arrange
        UploadingState state = new UploadingState();
        String recipe = "TestRecipe";

        // Act
        state.setRecipe(recipe);

        // Assert
        assertEquals(recipe, state.getRecipe());
    }

    @Test
    public void testGetSetRecipeError() {
        // Arrange
        UploadingState state = new UploadingState();
        String recipeError = "RecipeError";

        // Act
        state.setRecipeError(recipeError);

        // Assert
        assertEquals(recipeError, state.getRecipeError());
    }

    @Test
    public void testGetSetIngredients() {
        // Arrange
        UploadingState state = new UploadingState();
        String ingredients = "Ingredient1, Ingredient2";

        // Act
        state.setIngredients(ingredients);

        // Assert
        assertEquals(ingredients, state.getIngredients());
    }

    @Test
    public void testGetSetIngredientsError() {
        // Arrange
        UploadingState state = new UploadingState();
        String ingredientsError = "IngredientsError";

        // Act
        state.setIngredientsError(ingredientsError);

        // Assert
        assertEquals(ingredientsError, state.getIngredientsError());
    }

    @Test
    public void testGetSetInstructions() {
        // Arrange
        UploadingState state = new UploadingState();
        String instructions = "Step 1. Step 2.";

        // Act
        state.setInstructions(instructions);

        // Assert
        assertEquals(instructions, state.getInstructions());
    }

    @Test
    public void testGetSetInstructionsError() {
        // Arrange
        UploadingState state = new UploadingState();
        String instructionsError = "InstructionsError";

        // Act
        state.setInstructionsError(instructionsError);

        // Assert
        assertEquals(instructionsError, state.getInstructionsError());
    }

    @Test
    public void testGetSetRecipeImage() {
        // Arrange
        UploadingState state = new UploadingState();
        Image mockImage = mock(Image.class);

        // Act
        state.setRecipeImage(mockImage);

        // Assert
        assertEquals(mockImage, state.getRecipeImage());
    }

}