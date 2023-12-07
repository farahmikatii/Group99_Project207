package interface_adapter.uploads;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UploadsStateTest {
    @Test
    public void testDefaultConstructor() {
        // Arrange
        UploadsState uploadsState = new UploadsState();

        // Act
        List<Map<String, Object>> result = uploadsState.getUploadedrecipesList();

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testCopyConstructor() {
        // Arrange
        List<Map<String, Object>> originalList = new ArrayList<>();
        Map<String, Object> recipe1 = new HashMap<>();
        recipe1.put("name", "Recipe1");
        originalList.add(recipe1);

        UploadsState originalState = new UploadsState();
        originalState.setUploadedrecipesList(originalList);

        // Act
        UploadsState copiedState = new UploadsState(originalState);
        List<Map<String, Object>> result = copiedState.getUploadedrecipesList();

        // Assert
        assertNotNull(result);
        assertEquals(originalList, result);
    }

    @Test
    public void testSetUploadedrecipesList() {
        // Arrange
        UploadsState uploadsState = new UploadsState();

        List<Map<String, Object>> newList = new ArrayList<>();
        Map<String, Object> recipe1 = new HashMap<>();
        recipe1.put("name", "Recipe1");
        newList.add(recipe1);

        // Act
        uploadsState.setUploadedrecipesList(newList);
        List<Map<String, Object>> result = uploadsState.getUploadedrecipesList();

        // Assert
        assertNotNull(result);
        assertEquals(newList, result);
    }

}