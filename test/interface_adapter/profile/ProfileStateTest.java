package interface_adapter.profile;

import static org.junit.Assert.*;
import org.junit.Test;

public class ProfileStateTest {
    @Test
    public void testInitialState() {
        ProfileState profileState = new ProfileState();
        assertEquals("", profileState.getUsername());
    }

    @Test
    public void testCopyConstructor() {
        ProfileState originalState = new ProfileState();
        originalState.setUsername("testUser");

        ProfileState copyState = new ProfileState(originalState);

        assertEquals("testUser", copyState.getUsername());
    }

    @Test
    public void testSetUsername() {
        ProfileState profileState = new ProfileState();
        profileState.setUsername("newUser");

        assertEquals("newUser", profileState.getUsername());
    }

    @Test
    public void testSetUsernameWithCopyConstructor() {
        ProfileState originalState = new ProfileState();
        originalState.setUsername("testUser");

        ProfileState copyState = new ProfileState(originalState);
        copyState.setUsername("newUser");

        assertEquals("newUser", copyState.getUsername());
    }

}