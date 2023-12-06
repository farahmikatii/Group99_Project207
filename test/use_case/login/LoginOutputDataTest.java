package use_case.login;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginOutputDataTest {

    @Test
    void getUsername() {
        LoginOutputData outputData = new LoginOutputData("john.doe", false);
        assertEquals("john.doe", outputData.getUsername());
    }

}