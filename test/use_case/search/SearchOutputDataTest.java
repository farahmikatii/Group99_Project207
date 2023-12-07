package use_case.search;

import data_access.CommonRecipeDataAccessObject;
import entity.CommonRecipe;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchOutputDataTest {
    //farah: not passing
    @Test
    void getRecipesList() throws Exception {
        String jsonFilePath = "./response_output.json";
        CommonRecipeDataAccessObject dao = new CommonRecipeDataAccessObject(CommonRecipeDataAccessObject.readFileAsString(jsonFilePath));
        List<CommonRecipe> recipesList = dao.returnRecipeList(1);
        SearchOutputData outputData = new SearchOutputData(recipesList);

        assertEquals(recipesList, outputData.getRecipesList());
    }
}