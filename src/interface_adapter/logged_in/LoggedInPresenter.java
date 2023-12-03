//package interface_adapter.logged_in;
//
//import interface_adapter.ViewManagerModel;
//import interface_adapter.recipePopup.RecipePopupState;
//import interface_adapter.recipePopup.RecipePopupViewModel;
//import use_case.loggedIn.LoggedInOutputBoundary;
//import use_case.loggedIn.LoggedInOutputData;
//
//public class LoggedInPresenter implements LoggedInOutputBoundary {
//    private final LoggedInViewModel loggedInViewModel;
//    private ViewManagerModel viewManagerModel;
//    private final RecipePopupViewModel recipePopupViewModel;
//
//    public LoggedInPresenter(LoggedInViewModel loggedInViewModel, ViewManagerModel viewManagerModel, RecipePopupViewModel recipePopupViewModel) {
//        this.loggedInViewModel = loggedInViewModel;
//        this.viewManagerModel = viewManagerModel;
//        this.recipePopupViewModel = recipePopupViewModel;
//    }
//
//    @Override
//    public void prepareSuccessView(LoggedInOutputData loggedInOutputData) {
//        //on success switch to recipePopup
//
//        RecipePopupState recipePopupState = recipePopupViewModel.getState();
//
//        recipePopupState.setRecipeLabel(loggedInOutputData.getRecipeLabel());
//        this.recipePopupViewModel.setState(recipePopupState);
//
//
//        this.recipePopupViewModel.firePropertyChanged();
//
//        this.viewManagerModel.setActiveView(loggedInViewModel.getViewName());
//        this.viewManagerModel.firePropertyChanged();
//
//    }
//}
