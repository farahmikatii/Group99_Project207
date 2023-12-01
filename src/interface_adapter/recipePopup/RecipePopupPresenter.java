package interface_adapter.recipePopup;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.signup.SignupState;
import use_case.recipePopup.RecipePopupOutputBoundary;
import use_case.recipePopup.RecipePopupOutputData;

public class RecipePopupPresenter implements RecipePopupOutputBoundary {
    private final RecipePopupViewModel recipePopupViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private ViewManagerModel viewManagerModel;

    public RecipePopupPresenter(ViewManagerModel viewManagerModel, RecipePopupViewModel recipePopupViewModel, LoggedInViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.recipePopupViewModel = recipePopupViewModel;

        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void prepareSuccessView(RecipePopupOutputData recipePopupOutputData) {
        recipePopupViewModel.setRecipeLabel(recipePopupOutputData.getRecipeLabel());

        // Additional logic to update other parts of the view model if needed

        // Switch to the RecipePopup view
        viewManagerModel.setActiveView(recipePopupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();


    }
}
