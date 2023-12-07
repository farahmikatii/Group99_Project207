package interface_adapter.logged_in;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoggedInStateTest {

    @Test
    public void testLoggedInStateCopyConstructor() {
        // Arrange
        LoggedInState originalState = new LoggedInState();
        originalState.setUsername("testUser");

        // Act
        LoggedInState copyState = new LoggedInState(originalState);

        // Assert
        assertEquals(originalState.getUsername(), copyState.getUsername());
    }

    @Test
    public void getUsername() {
        LoggedInState loggedInState = new LoggedInState();
        assertEquals(" ", loggedInState.getUsername());
    }

    @Test
    public void getUsernameError() {
        LoggedInState loggedInState = new LoggedInState();
        loggedInState.setUsername("JohnDoe");
        assertEquals("JohnDoe", loggedInState.getUsername());
    }

    @Test
    public void setUsername() {
        assertNull(LoggedInState.getUsernameError());
    }
}