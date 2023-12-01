package interface_adapter.search;

import entity.CommonRecipe;

import java.util.ArrayList;
import java.util.List;

public class SearchState {
    private List<CommonRecipe> recipesList = new ArrayList<>();
    private String recipeListError = null;

    public SearchState(SearchState copy) {
        recipesList = copy.recipesList;
        recipeListError = copy.recipeListError;}

    public SearchState(){}

    public List<CommonRecipe> getRecipesList() {
        return recipesList;
    }

    public String getRecipeListError() {
        return recipeListError;
    }

    public void setRecipesList(List<CommonRecipe> recipesList) {
        this.recipesList = recipesList;
    }

    public void setRecipeListError(String recipeListError) {
        this.recipeListError = recipeListError;
    }
}
