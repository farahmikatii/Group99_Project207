package interface_adapter.search;

import use_case.search.SearchInputBoundary;
import use_case.search.SearchInputData;

import java.util.HashMap;
import java.util.Map;

public class SearchController {
    final SearchInputBoundary userSearchInteractor;

    public SearchController(SearchInputBoundary userSearchInteractor){
        this.userSearchInteractor = userSearchInteractor;
    }

    public void execute(String query, String dietLabel, String healthLabel, String cuisine, String mealType, String dishType){
        HashMap<StringBuilder, StringBuilder> filterDict = new HashMap<>();

        filterDict.put(new StringBuilder("q"), new StringBuilder(query));
        filterDict.put(new StringBuilder("diet"), new StringBuilder(dietLabel));
        filterDict.put(new StringBuilder("health"), new StringBuilder(healthLabel));
        filterDict.put(new StringBuilder("cuisineType"), new StringBuilder(cuisine));
        filterDict.put(new StringBuilder("mealType"), new StringBuilder(mealType));
        filterDict.put(new StringBuilder("dishType"), new StringBuilder(dishType));

        SearchInputData searchInputData = new SearchInputData(filterDict);
        userSearchInteractor.execute(searchInputData);
    }
}
