package dataAccessObjects;
import data_access.FileUserDataAccessObject;
import entity.*;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class FileUserDataAccessObjectTest {

    private final UserFactory userFactory = new CommonUserFactory();
    @Test
    public void existsByName_shouldReturnTrueForExistingUser() {
        // Arrange
        String csvFilePath = "./user.csv";

        FileUserDataAccessObject userDao;
        try {
            userDao = new FileUserDataAccessObject(csvFilePath, userFactory);
        } catch (IOException e) {
            throw new RuntimeException("Error creating user data access object", e);
        }

        // Act
        boolean exists = userDao.existsByName("gojo11");
        // Assert
        assertTrue(exists, "User should exist in the CSV file");
    }

    @Test
    public void save_shouldAddUserToAccountsList() {
        // Arrange
        String csvFilePath = "./user.csv"; // Provide your test CSV file path
        FileUserDataAccessObject userDao;
        try {
            userDao = new FileUserDataAccessObject(csvFilePath, userFactory);
        } catch (IOException e) {
            throw new RuntimeException("Error creating user data access object", e);
        }
        User newUser = userFactory.create("newuser", "newpassword");

        // Act
        userDao.save(newUser);

        // Assert
        assertTrue(userDao.existsByName("newuser"), "User should be added to the CSV file");
    }

    @Test
    public void saveUploadedRecipe_shouldAddRecipeToUploadedRecipeMap() {
        // Arrange
        String csvFilePath = "./user.csv"; // Provide your test CSV file path
        FileUserDataAccessObject userDao;
        try {
            userDao = new FileUserDataAccessObject(csvFilePath, userFactory);
        } catch (IOException e) {
            throw new RuntimeException("Error creating user data access object", e);
        }
        UploadedRecipeFactory uploadedRecipeFactory = new CommonUploadedRecipeFactory();
        UploadedRecipe newRecipe = uploadedRecipeFactory.create("New Recipe", "Ingredients", "Instructions", null);

        // Act
        userDao.saveUploadedRecipe(newRecipe);

        // Assert
        List<Map<String, Object>> uploadedRecipes = userDao.readUploadedRecipesFromCSV();
        boolean recipeFound = uploadedRecipes.stream()
                .anyMatch(recipe -> recipe.get("Name").equals("New Recipe"));

        assertTrue(recipeFound, "Uploaded recipe should be added to the CSV file");
    }


}
