package use_case.saved;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SavedInputDataTest {

    @Test
    void getRecipeName() {
        SavedInputData inputData = new SavedInputData("Pasta", "https://example.com/pasta", "https://example.com/pasta.jpg", "john.doe");
        assertEquals("Pasta", inputData.getRecipeName());
    }

    @Test
    void setRecipeName() {
        SavedInputData inputData = new SavedInputData("Pizza", "https://example.com/pizza", "https://example.com/pizza.jpg", "jane.smith");
        inputData.setRecipeName("Burger");
        assertEquals("Burger", inputData.getRecipeName());
    }

    @Test
    void getRecipeURL() {
        SavedInputData inputData = new SavedInputData("Salad", "https://example.com/salad", "https://example.com/salad.jpg", "test.user");
        assertEquals("https://example.com/salad", inputData.getRecipeURL());
    }

    @Test
    void setRecipeURL() {
        SavedInputData inputData = new SavedInputData("Smoothie", "https://example.com/smoothie", "https://example.com/smoothie.jpg", "user123");
        inputData.setRecipeURL("https://example.com/new-smoothie");
        assertEquals("https://example.com/new-smoothie", inputData.getRecipeURL());
    }

    @Test
    void getRecipeImageURL() {
        SavedInputData inputData = new SavedInputData("Test Recipe", "https://example.com/test", "https://example.com/test.jpg", "test.user");
        assertEquals("https://example.com/test.jpg", inputData.getRecipeImageURL());
    }

    @Test
    void setRecipeImageURL() {
        SavedInputData inputData = new SavedInputData("Original Recipe", "https://example.com/original", "https://example.com/original.jpg", "user456");
        inputData.setRecipeImageURL("https://example.com/new-original.jpg");
        assertEquals("https://example.com/new-original.jpg", inputData.getRecipeImageURL());
    }

    @Test
    void getUsername() {
        SavedInputData inputData = new SavedInputData("Test Recipe", "https://example.com/test", "https://example.com/test.jpg", "test.user");
        assertEquals("test.user", inputData.getUsername());
    }


    @Test
    void setUsername() {
        SavedInputData inputData = new SavedInputData("Original Recipe", "https://example.com/original", "https://example.com/original.jpg", "user789");
        inputData.setUsername("new.user");
        assertEquals("new.user", inputData.getUsername());
    }
}