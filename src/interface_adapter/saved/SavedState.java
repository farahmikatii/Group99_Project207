package interface_adapter.saved;

import entity.CommonRecipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SavedState {

    private String username;


    private String recipeName = null;

    private String recipeNameError = null;

    private String recipeURL = null;

    private String recipeURLError = null;

    private String recipeImageURL = null;

    private Map<String, String> savedMap = new HashMap<>();

    private List<CommonRecipe> savedList = new ArrayList<>();



    private CommonRecipe recipe;


    private String recipeImageURLError = null;


    public SavedState(SavedState copy){

        recipe = copy.recipe;
//        recipeName = copy.recipeName;
//        recipeURL = copy.recipeURL;
//        recipeImageURL = copy.recipeImageURL;
        savedMap = copy.savedMap;
        savedList = copy.savedList;


    }

    public SavedState(){}
    public CommonRecipe getRecipe() {
        return recipe;
    }

    public void setRecipe(CommonRecipe recipe) {
        this.recipe = recipe;
    }

    public String getUsername(){
        return username;
    }

    public String getRecipeName(){
        return recipe.getRecipeName();
    }

    public String getRecipeNameError(){
        return recipeNameError;
    }

    public String getRecipeURL(){
        return recipeURL;
    }

    public String getRecipeURLError(){
        return recipeURLError;
    }

    public String getRecipeImageURL(){
        return recipe.getImage();
    }

    public String getRecipeImageURLError(){
        return recipeImageURLError;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setRecipeName(CommonRecipe recipe){
        this.recipeName = recipe.getRecipeName();
    }

    public void setRecipeURL(CommonRecipe recipe){
        this.recipeURL = recipe.getRecipeUrl();
    }

    public void setRecipeImageURL(CommonRecipe recipe){
        this.recipeImageURL = recipe.getImage();
    }

    public void setRecipeNameError(String recipeNameError) {
        this.recipeNameError = recipeNameError;
    }

    public void setRecipeURLError(String recipeURLError) {
        this.recipeURLError = recipeURLError;
    }


    public void setRecipeImageURLError(String recipeImageURLError){
        this.recipeImageURLError = recipeImageURLError;
    }
    public Map<String, String> getSavedMap() {
        return savedMap;
    }

    public void setSavedMap(Map<String, String> savedMap) {
        this.savedMap = savedMap;

    }
    public List<CommonRecipe> getSavedList() {
        return savedList;
    }

    public void setSavedList(List<CommonRecipe> savedList) {
        this.savedList = savedList;
    }
}
