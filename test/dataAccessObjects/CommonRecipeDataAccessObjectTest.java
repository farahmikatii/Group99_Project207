package dataAccessObjects;

import data_access.CommonRecipeDataAccessObject;
import entity.CommonRecipe;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CommonRecipeDataAccessObjectTest {

    @Test
    void returnRecipeList_shouldReturnNonEmptyList() throws Exception {
        // Arrange
        String jsonFilePath = "/Users/farahmikati/IdeaProjects/Group99_Project207/response_output.json";
        CommonRecipeDataAccessObject dao = new CommonRecipeDataAccessObject(CommonRecipeDataAccessObject.readFileAsString(jsonFilePath));

        // Act
        List<CommonRecipe> recipeList = dao.returnRecipeList(1); // Assuming diff = 1

        // Assert
        assertNotNull(recipeList, "Returned recipe list should not be null");
        assertFalse(recipeList.isEmpty(), "Returned recipe list should not be empty");
    }

    @Test
    void findRecipe_shouldFindRecipeByName() throws Exception {
        // Arrange
        String jsonFilePath = "/Users/farahmikati/IdeaProjects/Group99_Project207/response_output.json";
        CommonRecipeDataAccessObject dao = new CommonRecipeDataAccessObject(CommonRecipeDataAccessObject.readFileAsString(jsonFilePath));

        // Act
        List<CommonRecipe> recipeList = dao.returnRecipeList(1); // Assuming diff = 1

        // Assert
        for (CommonRecipe recipe : recipeList) {
            CommonRecipe foundRecipe = dao.findRecipe(recipe.getRecipeName(), recipeList);
            assertNotNull(foundRecipe, "Recipe with name '" + recipe.getRecipeName() + "' should be found");
            assertEquals(recipe, foundRecipe, "Found recipe should be the same as the original recipe");
        }
    }

}
