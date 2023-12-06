package interface_adapter.logged_in;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoggedInStateTest {

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