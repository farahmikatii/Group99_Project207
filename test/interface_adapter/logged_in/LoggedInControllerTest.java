package interface_adapter.logged_in;

import org.junit.Test;
import use_case.loggedIn.LoggedInInputBoundary;
import use_case.loggedIn.LoggedInInputData;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class LoggedInControllerTest {

    @Test
    public void execute() {
        LoggedInInputBoundary loggedInInteractor = mock(LoggedInInputBoundary.class);
        LoggedInController loggedInController = new LoggedInController(loggedInInteractor);

        // Create test data
        String recipeLabel = "Test Recipe";
        String recipeUrl = "http://example.com/test-recipe";

        // Call the execute method
        loggedInController.execute(recipeLabel, recipeUrl);

        // Verify that the execute method of the interactor was called with the correct input data
        verify(loggedInInteractor, times(1)).execute(any(LoggedInInputData.class));
    }
}