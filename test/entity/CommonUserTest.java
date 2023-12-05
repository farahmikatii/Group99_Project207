package entity;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

public class CommonUserTest {

    private CommonUser user;

    public CommonUserTest(){
        this.user = new CommonUser(
                "user", "password");
    }

    @Test
    public void getName() {
        assertEquals("user", user.getName());
    }

    @Test
    public void getPassword() {
        assertEquals("password", user.getPassword());
    }
}