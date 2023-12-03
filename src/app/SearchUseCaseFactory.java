package app;

import data_access.FileUserDataAccessObject;
import data_access.FilterAPICallDataAccessObject;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.resultSearch.ResultViewModel;
import interface_adapter.search.SearchViewModel;
import interface_adapter.search.SearchController;
import interface_adapter.signup.SignupController;
import use_case.search.SearchInteractor;
import use_case.search.SearchInputBoundary;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.uploading.UploadingPresenter;
import interface_adapter.uploading.UploadingViewModel;
import interface_adapter.uploads.UploadsViewModel;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.uploading.UploadingDataAccessInterface;
import use_case.uploading.UploadingInputBoundary;
import use_case.uploading.UploadingInteractor;
import use_case.uploading.UploadingOutputBoundary;
import view.SearchView;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.search.SearchOutputBoundary;
import interface_adapter.search.SearchPresenter;
import interface_adapter.logged_in.LoggedInState;
import view.SignupView;

import javax.swing.*;
import java.io.IOException;

public class SearchUseCaseFactory {

    //public static SearchView create(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel, LoggedInViewModel loggedInViewModel, LoggedInState loggedInState){
        //SearchController searchController = createSearchUseCase(viewManagerModel, searchViewModel, loggedInViewModel);
        //return new SearchView(searchController, searchViewModel, viewManagerModel, loggedInViewModel, loggedInState);
    //}

    //private static SearchController createSearchUseCase(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel, LoggedInViewModel loggedInViewModel) {
        //SearchOutputBoundary searchOutputBoundary = new SearchPresenter(viewManagerModel, searchViewModel, loggedInViewModel);

        //UploadedRecipeFactory uploadedRecipeFactory = new CommonUploadedRecipeFactory();

        //SearchInputBoundary searchInteractor = new SearchInteractor(
                //searchDataAccessInterface, don't need
                //recipeDataAcessObject,
                //commonRecipeDataAccessObject,
                //searchPresenter,
                //recipeFactory

        //);

        //return new SearchController(searchInteractor);
    //}
    //FilterAPICallDataAccessObject recipeDataAccessObject, CommonRecipeDataAccessObject commonRecipeDataAccessObject, SearchOutputBoundary searchPresenter, RecipeFactory recipeFactory

    public static SearchView create(
            ViewManagerModel viewManagerModel, SearchViewModel searchViewModel, LoggedInViewModel loggedInViewModel, ResultViewModel resultViewModel){

        try{
            SearchController searchController = createUserSearchUseCase(viewManagerModel, searchViewModel, resultViewModel);
            return new SearchView(searchController, searchViewModel, viewManagerModel, loggedInViewModel);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Could not open.");
        }

        return null;
    }

    private static SearchController createUserSearchUseCase(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel, ResultViewModel resultViewModel) throws IOException{
        SearchOutputBoundary searchOutputBoundary = new SearchPresenter(viewManagerModel, resultViewModel, searchViewModel);
        RecipeFactory recipeFactory = new CommonRecipeFactory();
        FilterAPICallDataAccessObject recipeDataAcessObject = new FilterAPICallDataAccessObject();

        SearchInputBoundary searchInteractor = new SearchInteractor(recipeDataAcessObject, searchOutputBoundary, recipeFactory);

        return new SearchController(searchInteractor);
    }
        }
