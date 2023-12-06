package use_case.loggedIn;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoggedInInputDataTest {
    @Test
    void getRecipeLabel() {
        LoggedInInputData inputData = new LoggedInInputData("Pasta", "https://example.com/pasta.jpg");
        assertEquals("Pasta", inputData.getRecipeLabel());
    }

    @Test
    void setRecipeLabel() {
        LoggedInInputData inputData = new LoggedInInputData("Pizza", "https://example.com/pizza.jpg");
        inputData.setRecipeLabel("Burger");
        assertEquals("Burger", inputData.getRecipeLabel());
    }

    @Test
    void getImageUrl() {
        LoggedInInputData inputData = new LoggedInInputData("Salad", "https://example.com/salad.jpg");
        assertEquals("https://example.com/salad.jpg", inputData.getImageUrl());
    }

    @Test
    void setImageUrl() {
        LoggedInInputData inputData = new LoggedInInputData("Smoothie", "https://example.com/smoothie.jpg");
        inputData.setImageUrl("https://example.com/new-smoothie.jpg");
        assertEquals("https://example.com/new-smoothie.jpg", inputData.getImageUrl());
    }
}
