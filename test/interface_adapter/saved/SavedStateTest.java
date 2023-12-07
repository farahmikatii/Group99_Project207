package interface_adapter.saved;

import entity.CommonRecipe;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SavedStateTest {

        @Test
        public void testDefaultConstructor() {
            // Arrange
            SavedState savedState = new SavedState();
            savedState.setRecipe(new CommonRecipe(null, null, null, null));

            // Act
            String resultUsername = savedState.getUsername();
            String resultRecipeName = savedState.getRecipeName();
            String resultRecipeNameError = savedState.getRecipeNameError();
            String resultRecipeURL = savedState.getRecipeURL();
            String resultRecipeURLError = savedState.getRecipeURLError();
            String resultRecipeImageURL = savedState.getRecipeImageURL();
            String resultRecipeImageURLError = savedState.getRecipeImageURLError();

            // Assert
            assertNull(resultUsername);
            assertNull(resultRecipeName);
            assertNull(resultRecipeNameError);
            assertNull(resultRecipeURL);
            assertNull(resultRecipeURLError);
            assertNull(resultRecipeImageURL);
            assertNull(resultRecipeImageURLError);
        }

        @Test
        public void testCopyConstructor() {
            // Arrange
            SavedState originalState = new SavedState();
            originalState.setUsername(null);

            CommonRecipe recipe = new CommonRecipe(null, "", "", null);

            originalState.setRecipe(recipe);

            // Act
            SavedState copiedState = new SavedState(originalState);

            // Assert
            assertEquals(null, copiedState.getUsername());
            assertEquals(null, copiedState.getRecipeName());
        }

        @Test
        public void testSettersAndGetters() {
            // Arrange
            SavedState savedState = new SavedState();

            // Act
            savedState.setUsername("testUser");
            savedState.setRecipeNameError("NameError");

            // Assert
            assertEquals("testUser", savedState.getUsername());
            assertEquals("NameError", savedState.getRecipeNameError());
        }
    }