package use_case.uploading;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UploadingInputDataTest {

    @Test
    void getRecipeName() {
        UploadingInputData inputData = new UploadingInputData("Pasta", "Flour, Water, Salt", "Mix and cook", /* Image instance */ null);
        assertEquals("Pasta", inputData.getRecipeName());
    }

    @Test
    void getRecipeIngredients() {
        UploadingInputData inputData = new UploadingInputData("Pizza", "Dough, Cheese, Tomato", "Bake in oven", /* Image instance */ null);
        assertEquals("Dough, Cheese, Tomato", inputData.getRecipeIngredients());
    }

    @Test
    void getRecipeInstructions() {
        UploadingInputData inputData = new UploadingInputData("Salad", "Lettuce, Tomato, Cucumber", "Chop and mix", /* Image instance */ null);
        assertEquals("Chop and mix", inputData.getRecipeInstructions());
    }

}