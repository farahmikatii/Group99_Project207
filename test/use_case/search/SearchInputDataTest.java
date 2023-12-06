package use_case.search;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class SearchInputDataTest {

    @Test
    void getFilterDict() {
        HashMap<String, String> filterDict = new HashMap<>();
        filterDict.put("ingredient", "lemon");
        filterDict.put("category", "beverage");

        SearchInputData inputData = new SearchInputData(filterDict);

        assertEquals(filterDict, inputData.getFilterDict());
    }

}