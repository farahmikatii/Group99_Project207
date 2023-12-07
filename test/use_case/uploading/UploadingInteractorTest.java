package use_case.uploading;


import data_access.FileUserDataAccessObject;
import entity.CommonUploadedRecipeFactory;
import java.awt.Image;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import entity.CommonUserFactory;
import entity.UploadedRecipeFactory;
import entity.UserFactory;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.uploading.*;

import static org.junit.jupiter.api.Assertions.*;

public class UploadingInteractorTest {

    @Test
    void succesTest() throws IOException {
        UploadingInputData uploadingInputData = new UploadingInputData("Iced Matcha", "Matcha Powder", "Mix", null);
        UserFactory userFactory = new CommonUserFactory();
        UploadingDataAccessInterface uploadingDataAccessInterface = new FileUserDataAccessObject("test/user1.csv", userFactory);
        UploadedRecipeFactory uploadedRecipeFactory = new CommonUploadedRecipeFactory();
        UploadingOutputBoundary uploadingOutputBoundary = new UploadingOutputBoundary() {
            @Override
            public void prepareSuccessView(UploadingOutputData recipe) {
                assertEquals("Iced Matcha", recipe.getRecipeName());
                assertEquals("Matcha Powder", recipe.getRecipeIngredients());
                assertEquals("Mix", recipe.getRecipeInstructions());
                assertNull(recipe.getRecipeImage());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");

            }

            @Override
            public void prepareUploadedRecipeView(UploadingOutputData uploadedRecipe) {
                fail("Use case failure is unexpected.");
            }
        };
        UploadingInputBoundary interactor = new UploadingInteractor(uploadingDataAccessInterface, uploadingOutputBoundary, uploadedRecipeFactory);
        interactor.execute(uploadingInputData);
    }

    @Test
    void recipeNameFailTest() throws IOException {
        UploadingInputData uploadingInputData = new UploadingInputData("", "Matcha Powder", "Mix", null);
        UserFactory userFactory = new CommonUserFactory();
        UploadingDataAccessInterface uploadingDataAccessInterface = new FileUserDataAccessObject("test/user1.csv", userFactory);
        UploadedRecipeFactory uploadedRecipeFactory = new CommonUploadedRecipeFactory();

        UploadingOutputBoundary uploadingOutputBoundary = new UploadingOutputBoundary() {
            @Override
            public void prepareSuccessView(UploadingOutputData recipe) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Enter a recipe name", error);
            }

            @Override
            public void prepareUploadedRecipeView(UploadingOutputData uploadedRecipe) {
                fail("Use case failure is unexpected.");
            }
        };
        UploadingInputBoundary nameInteractor = new UploadingInteractor(uploadingDataAccessInterface, uploadingOutputBoundary, uploadedRecipeFactory);
        nameInteractor.execute(uploadingInputData);
    }

    @Test
    void ingredientsFailTest() throws IOException {
        UploadingInputData uploadingInputData = new UploadingInputData("Iced Matcha", "", "Mix", null);
        UserFactory userFactory = new CommonUserFactory();
        UploadingDataAccessInterface uploadingDataAccessInterface = new FileUserDataAccessObject("test/user1.csv", userFactory);
        UploadedRecipeFactory uploadedRecipeFactory = new CommonUploadedRecipeFactory();

        UploadingOutputBoundary uploadingOutputBoundary = new UploadingOutputBoundary() {
            @Override
            public void prepareSuccessView(UploadingOutputData recipe) {
                fail("Use case failure is unexpected.");

            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Enter recipe ingredients", error);

            }

            @Override
            public void prepareUploadedRecipeView(UploadingOutputData uploadedRecipe) {
                fail("Use case failure is unexpected.");

            }
        };
        UploadingInputBoundary ingredientsInteractor = new UploadingInteractor(uploadingDataAccessInterface, uploadingOutputBoundary, uploadedRecipeFactory);
        ingredientsInteractor.execute(uploadingInputData);
    }

    @Test
    void instructionsFailTest() throws IOException {
        UploadingInputData uploadingInputData = new UploadingInputData("Iced Matcha", "Matcha Powder", "", null);
        UserFactory userFactory = new CommonUserFactory();
        UploadingDataAccessInterface uploadingDataAccessInterface = new FileUserDataAccessObject("test/user1.csv", userFactory);
        UploadedRecipeFactory uploadedRecipeFactory = new CommonUploadedRecipeFactory();

        UploadingOutputBoundary uploadingOutputBoundary = new UploadingOutputBoundary() {
            @Override
            public void prepareSuccessView(UploadingOutputData recipe) {
                fail("Use case failure is unexpected.");

            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Enter recipe instructions", error);

            }

            @Override
            public void prepareUploadedRecipeView(UploadingOutputData uploadedRecipe) {
                fail("Use case failure is unexpected.");

            }
        };
        UploadingInputBoundary instructionsInteractor = new UploadingInteractor(uploadingDataAccessInterface, uploadingOutputBoundary, uploadedRecipeFactory);
        instructionsInteractor.execute(uploadingInputData);
    }

    @Test
    void uploadedRecipesTest() throws IOException {
        UserFactory userFactory = new CommonUserFactory();
        UploadingDataAccessInterface uploadingDataAccessInterface = new FileUserDataAccessObject("test/user1.csv", userFactory);
        UploadedRecipeFactory uploadedRecipeFactory = new CommonUploadedRecipeFactory();

        UploadingOutputBoundary uploadingOutputBoundary = new UploadingOutputBoundary() {
            @Override
            public void prepareSuccessView(UploadingOutputData recipe) {

            }

            @Override
            public void prepareFailView(String error) {

            }

            @Override
            public void prepareUploadedRecipeView(UploadingOutputData uploadedRecipe) {

            }
        };
        UploadingInputBoundary interactor = new UploadingInteractor(uploadingDataAccessInterface, uploadingOutputBoundary, uploadedRecipeFactory);
        List<Map<String, Object>>  expectedRecipes = interactor.uploadedRecipes();
        assertEquals(Collections.emptyList(), expectedRecipes);
    }

    @Test
    void executeRecipeViewTest() throws IOException {
        UploadingInputData uploadingInputData = new UploadingInputData("Iced Matcha", "Matcha Powder", "Mix", null);
        UserFactory userFactory = new CommonUserFactory();
        UploadingDataAccessInterface uploadingDataAccessInterface = new FileUserDataAccessObject("test/user1.csv", userFactory);
        UploadedRecipeFactory uploadedRecipeFactory = new CommonUploadedRecipeFactory();

        UploadingOutputBoundary uploadingOutputBoundary = new UploadingOutputBoundary() {
            @Override
            public void prepareSuccessView(UploadingOutputData recipe) {

            }

            @Override
            public void prepareFailView(String error) {

            }

            @Override
            public void prepareUploadedRecipeView(UploadingOutputData uploadedRecipe) {

            }
        };
        UploadingInputBoundary interactor = new UploadingInteractor(uploadingDataAccessInterface, uploadingOutputBoundary, uploadedRecipeFactory);
        interactor.executeRecipeView(uploadingInputData);

        //how to test view?
    }
}

