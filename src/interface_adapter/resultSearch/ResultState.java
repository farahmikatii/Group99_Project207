package interface_adapter.resultSearch;

import entity.CommonRecipe;

import java.util.ArrayList;
import java.util.List;

public class ResultState {
    private List<CommonRecipe> recipesList = new ArrayList<>();
    private String recipeListError = null;

    public ResultState(ResultState copy) {
        recipesList = copy.recipesList;
        recipeListError = copy.recipeListError;}

    public ResultState(){}

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