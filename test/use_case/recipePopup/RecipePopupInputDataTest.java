package use_case.recipePopup;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipePopupInputDataTest {

    @Test
    void getRecipeLabel() {
        RecipePopupInputData inputData = new RecipePopupInputData("Pasta", "https://example.com/pasta.jpg", "www.example.com/pasta", "flour, water, salt");
        assertEquals("Pasta", inputData.getRecipeLabel());
    }

    @Test
    void setRecipeLabel() {
        RecipePopupInputData inputData = new RecipePopupInputData("Pizza", "https://example.com/pizza.jpg", "www.example.com/pizza", "flour, water, salt");
        inputData.setRecipeLabel("Burger");
        assertEquals("Burger", inputData.getRecipeLabel());
    }

    @Test
    void getImageUrl() {
        RecipePopupInputData inputData = new RecipePopupInputData("Salad", "https://example.com/salad.jpg", "www.example.com/salad", "flour, water, salt");
        assertEquals("https://example.com/salad.jpg", inputData.getImageUrl());
    }

    @Test
    void setImageUrl() {
        RecipePopupInputData inputData = new RecipePopupInputData("Smoothie", "https://example.com/smoothie.jpg", "www.example.com/smoothie", "flour, water, salt");
        inputData.setImageUrl("new/path/to/image");
        assertEquals("new/path/to/image", inputData.getImageUrl());
    }

    @Test
    void getUrl() {
        RecipePopupInputData inputData = new RecipePopupInputData("Test Recipe", "https://example.com/test.jpg", "www.example.com/test", "flour, water, salt");
        assertEquals("www.example.com/test", inputData.getUrl());
    }


    @Test
    void setUrl() {
        RecipePopupInputData inputData = new RecipePopupInputData("Original Recipe", "path/to/image", "www.example.com/original", "flour, water, salt");
        inputData.setUrl("www.example.com/new-url");
        assertEquals("www.example.com/new-url", inputData.getUrl());
    }

    @Test
    void getIngredients() {
        RecipePopupInputData inputData = new RecipePopupInputData("Test Recipe", "https://example.com/test.jpg", "www.example.com/test", "test, ingredient");
        assertEquals("test, ingredient", inputData.getIngredients());
    }

    @Test
    void setIngredients() {
        RecipePopupInputData inputData = new RecipePopupInputData("Original Recipe", "path/to/image", "www.example.com/original", "original, ingredient");
        inputData.setIngredients("new, ingredient");
        assertEquals("new, ingredient", inputData.getIngredients());
    }
}