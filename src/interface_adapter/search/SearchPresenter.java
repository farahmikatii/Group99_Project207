package interface_adapter.search;

import interface_adapter.ViewManagerModel;
import interface_adapter.resultSearch.ResultState;
import interface_adapter.resultSearch.ResultViewModel;
import use_case.search.SearchOutputBoundary;
import use_case.search.SearchOutputData;

public class SearchPresenter implements SearchOutputBoundary{

    private final SearchViewModel searchViewModel;
    private final ResultViewModel resultViewModel;
    private final ViewManagerModel viewManagerModel;

    public SearchPresenter(ViewManagerModel viewManagerModel, ResultViewModel resultViewModel, SearchViewModel searchViewModel){
        this.resultViewModel = resultViewModel;
        this.viewManagerModel = viewManagerModel;
        this.searchViewModel = searchViewModel;
    }

    @Override
    public void prepareSuccessView(SearchOutputData response){
        ResultState resultState = resultViewModel.getState();
        if (resultState != null){
        resultState.setRecipesList(response.getRecipesList());}
        this.resultViewModel.setState(resultState);

        this.viewManagerModel.setActiveView(resultViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        SearchState searchState = searchViewModel.getState();
        if (searchState != null){
        searchState.setSearchError(error);}
        searchViewModel.firePropertyChanged();
    }
}
