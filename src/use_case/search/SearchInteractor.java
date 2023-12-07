package use_case.search;

import data_access.CommonRecipeDataAccessObject;
import data_access.FilterAPICallDataAccessObject;
import entity.CommonRecipe;
import entity.RecipeFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SearchInteractor implements SearchInputBoundary{
    final FilterAPICallDataAccessObject recipeDataAccessObject;
    final SearchOutputBoundary searchPresenter;
    final RecipeFactory recipeFactory;

    public SearchInteractor(FilterAPICallDataAccessObject recipeDataAccessObject, SearchOutputBoundary searchPresenter, RecipeFactory recipeFactory){
        this.recipeDataAccessObject = recipeDataAccessObject;
        this.searchPresenter = searchPresenter;
        this.recipeFactory = recipeFactory;
    }

    @Override
    public void execute(SearchInputData searchInputData) {
        HashMap<String, String> filterDict = searchInputData.getFilterDict();
        String jsonFile = null;
        jsonFile = recipeDataAccessObject.searchApiCall(filterDict);
        String file = null;
        try {
            file = CommonRecipeDataAccessObject.readFileAsString(jsonFile);
        } catch (Exception e) {
            searchPresenter.prepareFailView("The File Doesn't exist and no recipes to display.");
            return;
        }

        CommonRecipeDataAccessObject commonRecipeDAO = new CommonRecipeDataAccessObject(file);

        List<CommonRecipe> recipesList = commonRecipeDAO.returnRecipeList(2);

        SearchOutputData searchOutputData = new SearchOutputData(recipesList);
        searchPresenter.prepareSuccessView(searchOutputData);
    }
}
