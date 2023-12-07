package use_case.recipePopup;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipePopupOutputDataTest {

    @Test
    void getRecipeLabel() {
        RecipePopupOutputData outputData = new RecipePopupOutputData("Pasta", "https://example.com/pasta.jpg");
        assertEquals("Pasta", outputData.getRecipeLabel());
    }

    @Test
    void setRecipeLabel() {
        RecipePopupOutputData outputData = new RecipePopupOutputData("Pizza", "https://example.com/pizza.jpg");
        outputData.setRecipeLabel("Burger");
        assertEquals("Burger", outputData.getRecipeLabel());
    }

    @Test
    void getImageUrl() {
        RecipePopupOutputData outputData = new RecipePopupOutputData("Salad", "https://example.com/salad.jpg");
        assertEquals("https://example.com/salad.jpg", outputData.getImageUrl());
    }

    @Test
    void setImageUrl() {
        RecipePopupOutputData outputData = new RecipePopupOutputData("Smoothie", "https://example.com/smoothie.jpg");
        outputData.setImageUrl("new/path/to/image");
        assertEquals("new/path/to/image", outputData.getImageUrl());
    }

}