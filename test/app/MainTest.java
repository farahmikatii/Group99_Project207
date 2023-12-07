package app;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    @Test
    public void testMainMethod() {
        // Simply call the main method and check for any exceptions
        try {
            Main.main(new String[]{});
        } catch (Exception e) {
            // Fail the test if an exception is thrown
            e.printStackTrace();
            assert false;
        }

        // If the main method runs without exceptions, the test passes
        assert true;
    }

}