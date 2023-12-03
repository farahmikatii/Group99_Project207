//package use_case.loggedIn;
//
//import entity.CommonRecipe;
//import use_case.login.LoginInputBoundary;
//import use_case.login.LoginInputData;
//
//import java.util.List;
//
//public class LoggedInInteractor implements LoggedInInputBoundary {
//
////    final LoggedInDataAccessInterface loggedInDAO;
////    final LoggedInOutputBoundary loggedInPresenter;
////
////
////
////    public LoggedInInteractor(LoggedInDataAccessInterface loggedInDAO, LoggedInOutputBoundary loggedInPresenter) {
////        this.loggedInDAO = loggedInDAO;
////        this.loggedInPresenter = loggedInPresenter;
////
////
////    }
////
////    @Override
////    public void execute(LoggedInInputData loggedInInputData) {
////        List<CommonRecipe> returnRecipeList = loggedInDAO.returnRecipeList(1);
////        CommonRecipe recipe = loggedInDAO.findRecipe(loggedInInputData.getRecipeLabel(), returnRecipeList);
////        LoggedInOutputData loggedInOutputData = new LoggedInOutputData(recipe.getRecipeName(),recipe.getRecipeUrl());
////        loggedInPresenter.prepareSuccessView(loggedInOutputData);
////
////    }
//}
