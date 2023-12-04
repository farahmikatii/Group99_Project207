package app;

import entity.CommonRecipeFactory;
import entity.RecipeFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.recipePopup.RecipePopupController;
import interface_adapter.recipePopup.RecipePopupPresenter;
import interface_adapter.recipePopup.RecipePopupState;
import interface_adapter.recipePopup.RecipePopupViewModel;
import use_case.recipePopup.RecipePopupDataAccessInterface;
import use_case.recipePopup.RecipePopupInputBoundary;
import use_case.recipePopup.RecipePopupInteractor;
import use_case.recipePopup.RecipePopupOutputBoundary;
import view.RecipePopupView;

import javax.swing.*;
import java.io.IOException;

public class RecipePopupUseCaseFactory {
    private RecipePopupUseCaseFactory(){}

    public static RecipePopupView create(
            ViewManagerModel viewManagerModel, RecipePopupViewModel recipePopupViewModel, RecipePopupDataAccessInterface recipePopupDataAccessInterface, LoggedInViewModel loggedInViewModel, RecipePopupState recipePopupState){

        try{
            RecipePopupController recipePopupController = createUserRecipePopupUseCase(viewManagerModel, recipePopupViewModel, loggedInViewModel, recipePopupDataAccessInterface);
            return new RecipePopupView( viewManagerModel, recipePopupState, recipePopupViewModel, loggedInViewModel);
        }
        catch (IOException e){
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static RecipePopupController createUserRecipePopupUseCase(ViewManagerModel viewManagerModel, RecipePopupViewModel recipePopupViewModel, LoggedInViewModel loggedInViewModel, RecipePopupDataAccessInterface recipePopupDataAccessInterface) throws IOException{

        RecipePopupOutputBoundary recipePopupOutputBoundary = new RecipePopupPresenter(viewManagerModel, recipePopupViewModel, loggedInViewModel);

        RecipeFactory recipeFactory = new CommonRecipeFactory();

        RecipePopupInputBoundary recipePopupInteractor = new RecipePopupInteractor(recipePopupDataAccessInterface, recipePopupOutputBoundary);
        return new RecipePopupController(recipePopupInteractor);
    }
}
