package use_case.saved;

import entity.CommonRecipe;
import use_case.saved.SavedInputData;

import java.util.List;
import java.util.Map;

public interface SavedInputBoundary {
    void execute (SavedInputData savedInputData);

    //List<Map<String, CommonRecipe>> savedRecipes();

    void executeSavedView(SavedInputData savedInputData);
}
