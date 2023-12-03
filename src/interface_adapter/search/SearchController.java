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
        HashMap<String, String> filterDict = new HashMap<>();

        filterDict.put(new String("q"), query);
        filterDict.put(new String("diet"), dietLabel);
        filterDict.put(new String("health"), healthLabel);
        filterDict.put(new String("cuisineType"), cuisine);
        filterDict.put(new String("mealType"), mealType);
        filterDict.put(new String("dishType"), dishType);

        SearchInputData searchInputData = new SearchInputData(filterDict);
        userSearchInteractor.execute(searchInputData);
    }
}
