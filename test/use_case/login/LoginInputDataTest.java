package use_case.login;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginInputDataTest {

    @Test
    void getUsername() {
        LoginInputData inputData = new LoginInputData("john.doe", "password123");
        assertEquals("john.doe", inputData.getUsername());
    }

    @Test
    void getPassword() {
        LoginInputData inputData = new LoginInputData("jane.smith", "securePass");
        assertEquals("securePass", inputData.getPassword());
    }
}