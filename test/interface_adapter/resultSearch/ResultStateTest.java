package interface_adapter.resultSearch;

import entity.CommonRecipe;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ResultStateTest {

    @Test
    public void testConstructorAndGetters() {
        // Arrange
        List<CommonRecipe> recipesList = new ArrayList<>();
        recipesList.add(new CommonRecipe("Recipe 1", "Image 1", "URL 1", null));
        recipesList.add(new CommonRecipe("Recipe 2", "Image 2", "URL 2", null));
        String recipeListError = "Error message";

        // Act
        ResultState resultState = new ResultState();
        resultState.setRecipesList(recipesList);
        resultState.setRecipeListError(recipeListError);

        // Assert
        assertEquals(recipesList, resultState.getRecipesList());
        assertEquals(recipeListError, resultState.getRecipeListError());
    }

    @Test
    public void testDefaultConstructor() {
        // Act
        ResultState resultState = new ResultState();

        // Assert
        assertEquals(new ArrayList<>(), resultState.getRecipesList());
        assertNull(resultState.getRecipeListError());
    }

    @Test
    public void testResultStateCopyConstructor() {
        // Arrange
        List<CommonRecipe> originalRecipesList = new ArrayList<>();
        originalRecipesList.add(new CommonRecipe("Recipe1", "Image1", "Url1", null));
        originalRecipesList.add(new CommonRecipe("Recipe2", "Image2", "Url2", null));

        ResultState originalState = new ResultState();
        originalState.setRecipesList(originalRecipesList);
        originalState.setRecipeListError("Error");

        // Act
        ResultState copyState = new ResultState(originalState);

        // Assert
        assertEquals(originalState.getRecipesList(), copyState.getRecipesList());
        assertEquals(originalState.getRecipeListError(), copyState.getRecipeListError());

        // Ensure the list is not the same reference
        assertEquals(originalRecipesList.size(), copyState.getRecipesList().size());
        assertEquals(originalRecipesList.get(0).getRecipeName(), copyState.getRecipesList().get(0).getRecipeName());
        assertEquals(originalRecipesList.get(1).getRecipeName(), copyState.getRecipesList().get(1).getRecipeName());
    }

    @Test
    public void testSetters() {
        // Arrange
        ResultState resultState = new ResultState();
        List<CommonRecipe> newRecipesList = new ArrayList<>();
        newRecipesList.add(new CommonRecipe("New Recipe", "New Image", "New URL", null));
        String newRecipeListError = "New error message";

        // Act
        resultState.setRecipesList(newRecipesList);
        resultState.setRecipeListError(newRecipeListError);

        // Assert
        assertEquals(newRecipesList, resultState.getRecipesList());
        assertEquals(newRecipeListError, resultState.getRecipeListError());
    }

}