package use_case.search;

import static org.junit.jupiter.api.Assertions.*;

import data_access.FilterAPICallDataAccessObject;
import entity.CommonRecipeFactory;
import entity.RecipeFactory;
import org.junit.jupiter.api.Test;
import use_case.search.*;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class SearchInteractorTest {

    @Test
    void successTest(){
        HashMap<String, String> filterDict = new HashMap<>();
        filterDict.put("q", "pizza");
        SearchInputData searchInputData = new SearchInputData(filterDict);
        FilterAPICallDataAccessObject filterAPICallDataAccessObject = new FilterAPICallDataAccessObject();
        RecipeFactory recipeFactory = new CommonRecipeFactory();
        SearchOutputBoundary searchOutputBoundary = new SearchOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchOutputData searchOutputData) {

            }

            @Override
            public void prepareFailView(String error) {
                fail("Unexpected use case fail.");

            }
        };

        SearchInputBoundary searchInteractor = new SearchInteractor(filterAPICallDataAccessObject, searchOutputBoundary,recipeFactory);
        searchInteractor.execute(searchInputData);
    }

    @Test
    void searchFailTest(){
        AtomicBoolean shouldContinue = new AtomicBoolean(true);

        FilterAPICallDataAccessObject filterAPICallDataAccessObject = new FilterAPICallDataAccessObject(){
            @Override
            public String searchApiCall(HashMap<String, String> filterDict) {
                shouldContinue.set(false);
                return "";
            }
        };
        RecipeFactory recipeFactory = new CommonRecipeFactory();
        HashMap<String, String> filterDict = new HashMap<>();
        filterDict.put("", "");
        SearchInputData searchInputData = new SearchInputData(filterDict);
        SearchOutputBoundary searchOutputBoundary = new SearchOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchOutputData searchOutputData) {
                if (shouldContinue.get()) {
                    fail("Unexpected use case fail.");
                }
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("The File Doesn't exist and no recipes to display.", error);
                shouldContinue.set(false);
            }
        };
        SearchInputBoundary searchfailInteractor = new SearchInteractor(filterAPICallDataAccessObject, searchOutputBoundary,recipeFactory);
        searchfailInteractor.execute(searchInputData);

    }

}
