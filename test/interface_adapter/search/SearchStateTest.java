package interface_adapter.search;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SearchStateTest {
    @Test
    public void testSearchStateGettersAndSetters() {
        // Arrange
        SearchState searchState = new SearchState();
        String testValue = "TestValue";

        // Act
        searchState.setQuery(testValue);
        searchState.setDietLabel(testValue);
        searchState.setHealthLabel(testValue);
        searchState.setCuisine(testValue);
        searchState.setMealType(testValue);
        searchState.setDishType(testValue);
        searchState.setSearchError(testValue);

        // Assert
        assertEquals(testValue, searchState.getQuery());
        assertNull(searchState.getQueryEmpty());
        assertEquals(testValue, searchState.getDietLabel());
        assertNull(searchState.getDietLabelEmpty());
        assertEquals(testValue, searchState.getHealthLabel());
        assertNull(searchState.getHealthLabelEmpty());
        assertEquals(testValue, searchState.getCuisine());
        assertNull(searchState.getCuisineEmpty());
        assertEquals(testValue, searchState.getMealType());
        assertNull(searchState.getMealTypeEmpty());
        assertEquals(testValue, searchState.getDishType());
        assertNull(searchState.getDishTypeEmpty());
        assertEquals(testValue, searchState.getSearchError());
    }

    @Test
    public void testSearchStateCopyConstructor() {
        // Arrange
        SearchState originalState = new SearchState();
        originalState.setQuery("Query");
        originalState.setDietLabel("DietLabel");
        originalState.setHealthLabel("HealthLabel");
        originalState.setCuisine("Cuisine");
        originalState.setMealType("MealType");
        originalState.setDishType("DishType");
        originalState.setSearchError("SearchError");

        // Act
        SearchState copyState = new SearchState(originalState);

        // Assert
        assertEquals(originalState.getQuery(), copyState.getQuery());
        assertEquals(originalState.getDietLabel(), copyState.getDietLabel());
        assertEquals(originalState.getHealthLabel(), copyState.getHealthLabel());
        assertEquals(originalState.getCuisine(), copyState.getCuisine());
        assertEquals(originalState.getMealType(), copyState.getMealType());
        assertEquals(originalState.getDishType(), copyState.getDishType());
        assertEquals(originalState.getSearchError(), copyState.getSearchError());
    }
}