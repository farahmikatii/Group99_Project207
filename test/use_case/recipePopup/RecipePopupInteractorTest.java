package use_case.recipePopup;

import data_access.CommonRecipeDataAccessObject;
import entity.CommonRecipe;
import entity.CommonRecipeFactory;
import entity.Recipe;
import entity.RecipeFactory;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class RecipePopupInteractorTest {
    //farah: not passing
    @Test
    public void successTest(){
        RecipePopupDataAccessInterface recipePopupDAO = mock(RecipePopupDataAccessInterface.class);
        RecipePopupOutputBoundary recipePopupPresenter = mock(RecipePopupOutputBoundary.class);
        String mockIngredients = "Ingredient1, Ingredient2";
        CommonRecipeFactory recipeFactory = new CommonRecipeFactory();
        CommonRecipe mockRecipe = recipeFactory.create("MockRecipe", "MockUrl", "MockUrl1",  mockIngredients);

        RecipePopupInputData recipePopupInputData = new RecipePopupInputData("RecipeLabel", "ImageUrl", "RecipeUrl", mockIngredients);

        when(recipePopupDAO.returnRecipeList(1)).thenReturn(Arrays.asList(mockRecipe));
        when(recipePopupDAO.findRecipe("RecipeLabel", Arrays.asList(mockRecipe))).thenReturn(mockRecipe);

        RecipePopupInputBoundary  recipePopupInputBoundary = new RecipePopupInteractor(recipePopupDAO, recipePopupPresenter);
        recipePopupInputBoundary.loadRecipeDisplay(recipePopupInputData);
        verify(recipePopupDAO, times(1)).returnRecipeList(1);
        verify(recipePopupDAO, times(1)).findRecipe("RecipeLabel", Arrays.asList(mockRecipe));

    }
}
