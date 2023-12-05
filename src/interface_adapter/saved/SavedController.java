package interface_adapter.saved;

import use_case.saved.SavedInputBoundary;
import use_case.saved.SavedInputData;

public class SavedController {

    final SavedInputBoundary savedInteractor;

    public SavedController (SavedInputBoundary savedInteractor){
        this.savedInteractor = savedInteractor;
    }

    public void execute (String recipeName, String recipeURL, String recipeImageURL, String username){
        SavedInputData savedInputData = new SavedInputData(recipeName, recipeURL, recipeImageURL, username);
        savedInteractor.execute(savedInputData);
    }
}
