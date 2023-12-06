package use_case.recipePopup;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipePopupOutputDataTest {

    @Test
    void getRecipeLabel() {
        RecipePopupOutputData outputData = new RecipePopupOutputData("Pasta", "https://example.com/pasta.jpg", new String[]{"flour", "water", "salt"});
        assertEquals("Pasta", outputData.getRecipeLabel());
    }

    @Test
    void setRecipeLabel() {
        RecipePopupOutputData outputData = new RecipePopupOutputData("Pizza", "https://example.com/pizza.jpg", new String[]{"dough", "cheese", "tomato"});
        outputData.setRecipeLabel("Burger");
        assertEquals("Burger", outputData.getRecipeLabel());
    }

    @Test
    void getImageUrl() {
        RecipePopupOutputData outputData = new RecipePopupOutputData("Salad", "https://example.com/salad.jpg", new String[]{"lettuce", "tomato", "cucumber"});
        assertEquals("https://example.com/salad.jpg", outputData.getImageUrl());
    }

    @Test
    void setImageUrl() {
        RecipePopupOutputData outputData = new RecipePopupOutputData("Smoothie", "https://example.com/smoothie.jpg", new String[]{"banana", "strawberry", "yogurt"});
        outputData.setImageUrl("new/path/to/image");
        assertEquals("new/path/to/image", outputData.getImageUrl());
    }

    @Test
    void getIngredients() {
        RecipePopupOutputData outputData = new RecipePopupOutputData("Test Recipe", "https://example.com/test.jpg", new String[]{"test", "ingredient"});
        assertArrayEquals(new String[]{"test", "ingredient"}, outputData.getIngredients());
    }

    @Test
    void setIngredients() {
        RecipePopupOutputData outputData = new RecipePopupOutputData("Original Recipe", "path/to/image", new String[]{"original", "ingredient"});
        outputData.setIngredients(new String[]{"new", "ingredient"});
        assertArrayEquals(new String[]{"new", "ingredient"}, outputData.getIngredients());
    }
}