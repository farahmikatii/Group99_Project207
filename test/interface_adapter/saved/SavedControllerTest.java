package interface_adapter.saved;

import org.junit.Test;
import use_case.saved.SavedInputBoundary;
import use_case.saved.SavedInputData;

import static org.mockito.Mockito.*;

public class SavedControllerTest {

        @Test
        public void testExecute() {
            // Arrange
            SavedInputBoundary mockSavedInteractor = mock(SavedInputBoundary.class);
            SavedController savedController = new SavedController(mockSavedInteractor);

            String recipeName = "Test Recipe";
            String recipeURL = "http://example.com/recipe";
            String recipeImageURL = "http://example.com/image";
            String username = "testUser";

            // Act
            savedController.execute(recipeName, recipeURL, recipeImageURL, username);

            // Assert
            verify(mockSavedInteractor, times(1)).execute(any(SavedInputData.class));
        }
    }
