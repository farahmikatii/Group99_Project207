package interface_adapter.search;

import org.junit.Test;
import use_case.search.SearchInputBoundary;

import java.util.HashMap;

import static org.mockito.Mockito.*;

public class SearchControllerTest {

    private SearchInputBoundary mockUserSearchInteractor;

    private SearchController searchController;

    @Test
    public void testExecute() {
        mockUserSearchInteractor = mock(SearchInputBoundary.class);
        searchController = new SearchController(mockUserSearchInteractor);
        // Arrange
        String query = "chicken";
        String dietLabel = "vegetarian";
        String healthLabel = "low-sugar";
        String cuisine = "italian";
        String mealType = "lunch";
        String dishType = "main-course";

        // Act
        searchController.execute(query, dietLabel, healthLabel, cuisine, mealType, dishType);

        // Assert
        HashMap<String, String> expectedFilterDict = new HashMap<>();
        expectedFilterDict.put("q", query);
        expectedFilterDict.put("diet", dietLabel);
        expectedFilterDict.put("health", healthLabel);
        expectedFilterDict.put("cuisineType", cuisine);
        expectedFilterDict.put("mealType", mealType);
        expectedFilterDict.put("dishType", dishType);

        verify(mockUserSearchInteractor, times(1)).execute(argThat(
                input -> input.getFilterDict().equals(expectedFilterDict)));

    }
}