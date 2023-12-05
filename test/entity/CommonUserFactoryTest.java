package entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class CommonUserFactoryTest {
    private CommonUserFactory userFactory = new CommonUserFactory();

    @Test
    public void testCreate() {
        // Given
        String name = "testUser";
        String password = "testPassword";

        // When
        User createdUser = userFactory.create(name, password);

        // Then
        assertNotNull(createdUser);
        assertTrue(createdUser instanceof CommonUser);

        CommonUser commonUser = (CommonUser) createdUser;
        assertEquals(name, commonUser.getName());
        assertEquals(password, commonUser.getPassword());
    }
}