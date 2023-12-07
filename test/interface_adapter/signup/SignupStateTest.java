package interface_adapter.signup;

import org.junit.Test;

import static org.junit.Assert.*;

public class SignupStateTest {
    @Test
    public void testSettersAndGetters() {
        // Arrange
        SignupState signupState = new SignupState();

        // Act
        signupState.setUsername("testUser");
        signupState.setUsernameError("UsernameError");
        signupState.setPassword("testPassword");
        signupState.setPasswordError("PasswordError");
        signupState.setRepeatPassword("testRepeatPassword");
        signupState.setRepeatPasswordError("RepeatPasswordError");

        // Assert
        assertEquals("testUser", signupState.getUsername());
        assertEquals("UsernameError", signupState.getUsernameError());
        assertEquals("testPassword", signupState.getPassword());
        assertEquals("PasswordError", signupState.getPasswordError());
        assertEquals("testRepeatPassword", signupState.getRepeatPassword());
        assertEquals("RepeatPasswordError", signupState.getRepeatPasswordError());
    }

    @Test
    public void testSignupStateCopyConstructor() {
        // Arrange
        SignupState originalState = new SignupState();
        originalState.setUsername("testUser");
        originalState.setUsernameError("UsernameError");
        originalState.setPassword("testPassword");
        originalState.setPasswordError("PasswordError");
        originalState.setRepeatPassword("testRepeatPassword");
        originalState.setRepeatPasswordError("RepeatPasswordError");

        // Act
        SignupState copyState = new SignupState(originalState);

        // Assert
        assertEquals(originalState.getUsername(), copyState.getUsername());
        assertEquals(originalState.getUsernameError(), copyState.getUsernameError());
        assertEquals(originalState.getPassword(), copyState.getPassword());
        assertEquals(originalState.getPasswordError(), copyState.getPasswordError());
        assertEquals(originalState.getRepeatPassword(), copyState.getRepeatPassword());
        assertEquals(originalState.getRepeatPasswordError(), copyState.getRepeatPasswordError());
    }
}