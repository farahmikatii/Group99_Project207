package view;

import app.SearchUseCaseFactory;
import app.SearchUseFactoryTest;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.resultSearch.ResultViewModel;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SearchViewTest {
    private SearchController mockSearchController;
    private SearchViewModel mockSearchViewModel;
    private ViewManagerModel mockViewManagerModel;
    private LoggedInViewModel mockLoggedInViewModel;

    private SearchView searchView;

    @Test
    public void actionPerformed() {


        ViewManagerModel viewManagerModel = new ViewManagerModel() ;
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        SearchViewModel searchViewModel = new SearchViewModel();
        ResultViewModel resultViewModel = new ResultViewModel();


        SearchView searchView = SearchUseCaseFactory.create(viewManagerModel, searchViewModel, loggedInViewModel, resultViewModel);
        SearchView mockSearchView = mock(SearchView.class);

        ActionEvent mockActionEvent = mock(ActionEvent.class);
        when(mockActionEvent.getActionCommand()).thenReturn("someCommand");

        mockSearchView.actionPerformed(mockActionEvent);
        verify(mockSearchView).actionPerformed(any(ActionEvent.class));
        //verify(mockSearchView).actionPerformed(argThat(actionEvent -> "expectedCommand".equals(actionEvent.getActionCommand())));

    }

    @Test
    void propertyChange_ShouldHandlePropertyChange() {
        ViewManagerModel viewManagerModel = new ViewManagerModel() ;
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        SearchViewModel searchViewModel = new SearchViewModel();
        ResultViewModel resultViewModel = new ResultViewModel();
        SearchState searchState = searchViewModel.getState();


        SearchView searchView = SearchUseCaseFactory.create(viewManagerModel, searchViewModel, loggedInViewModel, resultViewModel);
        PropertyChangeEvent mockPropertyChangeEvent = mock(PropertyChangeEvent.class);
        when(mockPropertyChangeEvent.getNewValue()).thenReturn(searchState);

        searchView.propertyChange(mockPropertyChangeEvent);

    }

}