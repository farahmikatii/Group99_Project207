package interface_adapter.saved;

import entity.CommonRecipe;
import use_case.saved.SavedInputBoundary;
import use_case.saved.SavedInputData;

import java.util.List;
import java.util.Map;

public class SavedController {

    final SavedInputBoundary savedInteractor;

    public SavedController (SavedInputBoundary savedInteractor){
        this.savedInteractor = savedInteractor;
    }

    public void execute (String recipeName, String recipeURL, String recipeImageURL, String username){
        SavedInputData savedInputData = new SavedInputData(recipeName, recipeURL, recipeImageURL, username);
        savedInteractor.execute(savedInputData);
    }

//    public List<Map<String, CommonRecipe>> savedRecipes() {
//        return savedInteractor.savedRecipes();
//
//    }

    public void executeSavedView(String recipeName, String recipeURL, String recipeImageURL, String username){
        SavedInputData savedInputData = new SavedInputData(
                recipeName, recipeURL, recipeImageURL, username);
        savedInteractor.executeSavedView(savedInputData);
    }
}
