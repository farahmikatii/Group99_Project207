package use_case.search;

import entity.CommonRecipe;

import java.util.List;

public class SearchOutputData {

    private final List<CommonRecipe> recipesList;

    public SearchOutputData(List<CommonRecipe> recipesList) {
        this.recipesList = recipesList;
    }

    public List<CommonRecipe> getRecipesList(){
        return recipesList;
    }
}
