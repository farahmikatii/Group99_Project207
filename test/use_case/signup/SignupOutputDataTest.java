package use_case.signup;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignupOutputDataTest {

    @Test
    void getUsername() {
        SignupOutputData outputData = new SignupOutputData("john.doe", false);
        assertEquals("john.doe", outputData.getUsername());
    }
}