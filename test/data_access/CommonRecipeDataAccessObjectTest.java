package data_access;

import data_access.CommonRecipeDataAccessObject;
import entity.CommonRecipe;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class CommonRecipeDataAccessObjectTest {
/*
    @Mock
    private String jsonFile;

    @InjectMocks
    private CommonRecipeDataAccessObject dataAccessObject;

    @Test
    void returnRecipeListTest() {
        // Mocking
        when(jsonFile.getJSONObject("hits")).thenReturn(someMockedJSONArray());

        List<CommonRecipe> recipes = dataAccessObject.returnRecipeList(1);

        // Assertion
        assertEquals(2, recipes.size());
    }

    private JSONArray someMockedJSONArray() {
        return new JSONArray().put(new JSONObject().put("recipe", new JSONObject().put("label", "Recipe1")))
                .put(new JSONObject().put("recipe", new JSONObject().put("label", "Recipe2")));
    }
    @Test
    void findRecipe() throws Exception {
        // Arrange
        String jsonFilePath = "/Users/farahmikati/IdeaProjects/new/Group99_Project207/test/data_access/daTests.json";
        CommonRecipeDataAccessObject dao = new CommonRecipeDataAccessObject(CommonRecipeDataAccessObject.readFileAsString(jsonFilePath));

        List<CommonRecipe> recipeList = dao.returnRecipeList(1); // Assuming diff = 1

        // Assert
        for (CommonRecipe recipe : recipeList) {
            CommonRecipe foundRecipe = dao.findRecipe(recipe.getRecipeName(), recipeList);
            assertNotNull(foundRecipe, "Recipe with name '" + recipe.getRecipeName() + "' should be found");
            assertEquals(recipe, foundRecipe, "Found recipe should be the same as the original recipe");
        }
    }*/

}
