package use_case.saved;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SavedOutputDataTest {

    @Test
    void getRecipeName() {
        SavedOutputData outputData = new SavedOutputData("Pasta", "https://example.com/pasta", "https://example.com/pasta.jpg");
        assertEquals("Pasta", outputData.getRecipeName());
    }

    @Test
    void getRecipeURL() {
        SavedOutputData outputData = new SavedOutputData("Salad", "https://example.com/salad", "https://example.com/salad.jpg");
        assertEquals("https://example.com/salad", outputData.getRecipeURL());
    }

    @Test
    void getRecipeImageURL() {
        SavedOutputData outputData = new SavedOutputData("Test Recipe", "https://example.com/test", "https://example.com/test.jpg");
        assertEquals("https://example.com/test.jpg", outputData.getRecipeImageURL());
    }
}