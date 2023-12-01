package use_case.loggedIn;

import entity.CommonRecipe;

import java.util.List;

public interface LoggedInDataAccessInterface {
    List<CommonRecipe> returnRecipeList(int diff);
}
