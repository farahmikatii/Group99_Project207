package data_access;

import entity.CommonUserFactory;
import entity.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InMemoryUserDataAccessObjectTest {

    @Test
    void existsByName_shouldReturnFalseForNonExistingUser() {
        // Arrange
        InMemoryUserDataAccessObject userDao = new InMemoryUserDataAccessObject();
        String nonExistingUsername = "nonexistent";

        // Act
        boolean userExists = userDao.existsByName(nonExistingUsername);

        // Assert
        assertFalse(userExists, "User should not exist");
    }

    @Test
    void existsByName_shouldReturnTrueForExistingUser() {
        // Arrange
        InMemoryUserDataAccessObject userDao = new InMemoryUserDataAccessObject();
        String existingUsername = "existing";
        CommonUserFactory commonUserFactory = new CommonUserFactory();
        User existingUser = commonUserFactory.create(existingUsername, "password");

        // Act
        userDao.save(existingUser);
        boolean userExists = userDao.existsByName(existingUsername);

        // Assert
        assertTrue(userExists, "User should exist");
    }

    @Test
    void save_shouldAddUserToMemory() {
        // Arrange
        InMemoryUserDataAccessObject userDao = new InMemoryUserDataAccessObject();
        String username = "newuser";
        CommonUserFactory commonUserFactory = new CommonUserFactory();
        User newUser = commonUserFactory.create(username, "password");

        // Act
        userDao.save(newUser);
        boolean userExists = userDao.existsByName(username);

        // Assert
        assertTrue(userExists, "User should exist after saving");
    }

}
