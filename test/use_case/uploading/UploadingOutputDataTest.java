package use_case.uploading;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UploadingOutputDataTest {

    @Test
    void getRecipeName() {
        UploadingOutputData outputData = new UploadingOutputData("Pasta", "Flour, Water, Salt", "Mix and cook", /* Image instance */ null);
        assertEquals("Pasta", outputData.getRecipeName());
    }

    @Test
    void getRecipeIngredients() {
        UploadingOutputData outputData = new UploadingOutputData("Pizza", "Dough, Cheese, Tomato", "Bake in oven", /* Image instance */ null);
        assertEquals("Dough, Cheese, Tomato", outputData.getRecipeIngredients());
    }

    @Test
    void getRecipeInstructions() {
        UploadingOutputData outputData = new UploadingOutputData("Salad", "Lettuce, Tomato, Cucumber", "Chop and mix", /* Image instance */ null);
        assertEquals("Chop and mix", outputData.getRecipeInstructions());
    }

}