package use_case.signup;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignupInputDataTest {

    @Test
    void getUsername() {
        SignupInputData inputData = new SignupInputData("john.doe", "password123", "password123");
        assertEquals("john.doe", inputData.getUsername());
    }


    @Test
    void getPassword() {
        SignupInputData inputData = new SignupInputData("jane.smith", "securePass", "securePass");
        assertEquals("securePass", inputData.getPassword());
    }

    @Test
    void getRepeatPassword() {
        SignupInputData inputData = new SignupInputData("test.user", "testPassword", "testPassword");
        assertEquals("testPassword", inputData.getRepeatPassword());
    }

}