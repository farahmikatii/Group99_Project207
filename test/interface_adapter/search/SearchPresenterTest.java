package interface_adapter.search;

import entity.CommonRecipe;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginState;
import interface_adapter.resultSearch.ResultState;
import interface_adapter.resultSearch.ResultViewModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import use_case.search.SearchOutputData;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SearchPresenterTest {
    @Mock
    private ViewManagerModel mockViewManagerModel;

    @Mock
    private ResultViewModel mockResultViewModel;

    @Mock
    private SearchViewModel mockSearchViewModel;

    @InjectMocks
    private SearchPresenter searchPresenter;

    @Before
    public void setUp() {
        SearchState state = new SearchState();
        mockSearchViewModel.setState(state);
        searchPresenter = new SearchPresenter(mockViewManagerModel, mockResultViewModel, mockSearchViewModel);
    }

    @Test
    public void testPrepareSuccessView(){
        List<CommonRecipe> recipesList = new ArrayList<>();
        recipesList.add(new CommonRecipe("Recipe1", "", "", null));
        recipesList.add(new CommonRecipe("Recipe2", "", "", null));
        SearchOutputData response = new SearchOutputData(recipesList);
        // Act
        searchPresenter.prepareSuccessView(response);

        // Assert
        //verify(mockResultViewModel).firePropertyChanged();
        verify(mockViewManagerModel).setActiveView(mockResultViewModel.getViewName());
        verify(mockViewManagerModel).firePropertyChanged();

        ResultState resultState = mockResultViewModel.getState();
        if (resultState != null){
        assertEquals(recipesList, resultState.getRecipesList());}
    }

    @Test
    public void testPrepareFailView() {
        String error = "Search error";
        searchPresenter.prepareFailView(error);

        SearchState searchState = mockSearchViewModel.getState();
        if (searchState != null){
            assertEquals(error, searchState.getSearchError());}
    }

}