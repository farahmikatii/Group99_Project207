package use_case.loggedIn;

import entity.CommonRecipe;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoggedInOutputDataTest {

    @Test
    void getRecipeLabel() {
        CommonRecipe commonRecipe = new CommonRecipe("Pizza", "path/to/image", "www.example.com", "dough, cheese, tomato");
        LoggedInOutputData outputData = new LoggedInOutputData("Pizza", "path/to/image", commonRecipe);
        assertEquals("Pizza", outputData.getRecipeLabel());
    }

    @Test
    void setRecipeLabel() {
        CommonRecipe commonRecipe = new CommonRecipe("Burger", "path/to/image", "www.example.com", "dough, cheese, tomato");
        LoggedInOutputData outputData = new LoggedInOutputData("Burger", "path/to/image", commonRecipe);
        outputData.setRecipeLabel("Burger");
        assertEquals("Burger", outputData.getRecipeLabel());
    }

    @Test
    void getImageUrl() {
        CommonRecipe commonRecipe = new CommonRecipe("Salad", "path/to/image", "www.example.com", "dough, cheese, tomato");
        LoggedInOutputData outputData = new LoggedInOutputData("Salad", "path/to/image", commonRecipe);
        assertEquals("path/to/image", outputData.getImageUrl());
    }

    @Test
    void setImageUrl() {
        CommonRecipe commonRecipe = new CommonRecipe("Test Recipe", "path/to/image", "www.example.com", "dough, cheese, tomato");
        LoggedInOutputData outputData = new LoggedInOutputData("Test Recipe", "path/to/image", commonRecipe);
        assertEquals("path/to/image", outputData.getImageUrl());
    }
}