package interface_adapter.logged_in;

import use_case.loggedIn.LoggedInInputBoundary;
import use_case.loggedIn.LoggedInInputData;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;
import use_case.recipePopup.RecipePopupInputData;

public class LoggedInController {

    final LoggedInInputBoundary loggedInInteractor;
    public LoggedInController( LoggedInInputBoundary loggedInInteractor) {
        this.loggedInInteractor = loggedInInteractor;
    }

    public void execute(String recipeLabel, String recipeUrl){
        LoggedInInputData loggedInInputData = new LoggedInInputData(recipeLabel, recipeUrl);
        loggedInInteractor.execute(loggedInInputData);

    }
}
