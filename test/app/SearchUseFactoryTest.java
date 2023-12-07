
package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.resultSearch.ResultViewModel;
import interface_adapter.search.SearchViewModel;
import org.junit.Test;
import view.SearchView;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;

public class SearchUseFactoryTest {

    @Test
    public void createSearchView_Success() {
        ViewManagerModel viewManagerModel = mock(ViewManagerModel.class);
        SearchViewModel searchViewModel = mock(SearchViewModel.class);
        LoggedInViewModel loggedInViewModel = mock(LoggedInViewModel.class);
        ResultViewModel resultViewModel = mock(ResultViewModel.class);

        SearchView searchView = SearchUseCaseFactory.create(viewManagerModel, searchViewModel, loggedInViewModel, resultViewModel);

        assertNotNull(searchView);
    }

    @Test
    public void createSearchView_Failure() {
        ViewManagerModel viewManagerModel = mock(ViewManagerModel.class);
        SearchViewModel searchViewModel = mock(SearchViewModel.class);
        LoggedInViewModel loggedInViewModel = mock(LoggedInViewModel.class);
        ResultViewModel resultViewModel = mock(ResultViewModel.class);


        SearchView searchView = SearchUseCaseFactory.create(viewManagerModel, searchViewModel, loggedInViewModel, resultViewModel);

        assertNotNull(searchView);
    }

}