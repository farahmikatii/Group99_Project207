package interface_adapter.login;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoginStateTest {

    @Test
    public void testDefaultState() {
        LoginState loginState = new LoginState();
        assertEquals("", loginState.getUsername());
        assertNull(loginState.getUsernameError());
        assertEquals("", loginState.getPassword());
        assertNull(loginState.getPasswordError());
    }

    @Test
    public void testCopyConstructor() {
        LoginState originalState = new LoginState();
        originalState.setUsername("user");
        originalState.setUsernameError("Username error");
        originalState.setPassword("pass");
        originalState.setPasswordError("Password error");

        LoginState copiedState = new LoginState(originalState);

        assertEquals(originalState.getUsername(), copiedState.getUsername());
        assertEquals(originalState.getUsernameError(), copiedState.getUsernameError());
        assertEquals(originalState.getPassword(), copiedState.getPassword());
        assertEquals(originalState.getPasswordError(), copiedState.getPasswordError());
    }

    @Test
    public void testSettersAndGetters() {
        LoginState loginState = new LoginState();
        loginState.setUsername("user");
        loginState.setUsernameError("Username error");
        loginState.setPassword("pass");
        loginState.setPasswordError("Password error");

        assertEquals("user", loginState.getUsername());
        assertEquals("Username error", loginState.getUsernameError());
        assertEquals("pass", loginState.getPassword());
        assertEquals("Password error", loginState.getPasswordError());
    }

    @Test
    public void testSetUsername() {
        LoginState loginState = new LoginState();
        loginState.setUsername("user");

        assertEquals("user", loginState.getUsername());
    }

    @Test
    public void testSetUsernameError() {
        LoginState loginState = new LoginState();
        loginState.setUsernameError("Username error");

        assertEquals("Username error", loginState.getUsernameError());
    }

    @Test
    public void testSetPassword() {
        LoginState loginState = new LoginState();
        loginState.setPassword("pass");

        assertEquals("pass", loginState.getPassword());
    }

    @Test
    public void testSetPasswordError() {
        LoginState loginState = new LoginState();
        loginState.setPasswordError("Password error");

        assertEquals("Password error", loginState.getPasswordError());
    }
}