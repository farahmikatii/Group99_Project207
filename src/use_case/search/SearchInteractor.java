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
    final CommonRecipeDataAccessObject commonRecipeDataAccessObject;
    final SearchOutputBoundary searchPresenter;
    final RecipeFactory recipeFactory;

    public SearchInteractor(FilterAPICallDataAccessObject recipeDataAccessObject, CommonRecipeDataAccessObject commonRecipeDataAccessObject, SearchOutputBoundary searchPresenter, RecipeFactory recipeFactory){
        this.recipeDataAccessObject = recipeDataAccessObject;
        this.commonRecipeDataAccessObject = commonRecipeDataAccessObject;
        this.searchPresenter = searchPresenter;
        this.recipeFactory = recipeFactory;
    }

    @Override
    public void execute(SearchInputData searchInputData) {
        HashMap<String, String> filterDict = searchInputData.getFilterDict();
        String jsonFile = null;
        try {
            jsonFile = recipeDataAccessObject.searchApiCall(filterDict);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String file = null;
        try {
            file = CommonRecipeDataAccessObject.readFileAsString(jsonFile);
        } catch (Exception e) {
            searchPresenter.prepareFailView("The File Doesn't exist and no recipes to display.");
        }

        CommonRecipeDataAccessObject commonRecipeDAO = new CommonRecipeDataAccessObject(file);

        List<CommonRecipe> recipesList = commonRecipeDAO.returnRecipeList(2);

        SearchOutputData searchOutputData = new SearchOutputData(recipesList);
        searchPresenter.prepareSuccessView(searchOutputData);
    }
}
