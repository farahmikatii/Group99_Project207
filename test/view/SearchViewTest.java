package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SearchViewTest {
    private SearchController mockSearchController;
    private SearchViewModel mockSearchViewModel;
    private ViewManagerModel mockViewManagerModel;
    private LoggedInViewModel mockLoggedInViewModel;

    private SearchView searchView;

    @Test
    void actionPerformed() {
        searchView.searchInputField.setText("Pasta");

        // Simulate a search button click
        searchView.actionPerformed(new ActionEvent(searchView.search, ActionEvent.ACTION_PERFORMED, "Search"));

        // Verify that the searchController.execute method was called with the expected parameters
        verify(mockSearchController, times(1)).execute(eq("Pasta"), isNull(), isNull(), isNull(), isNull(), isNull());
    }

    @Test
    void propertyChange() {
        PropertyChangeEvent mockEvent = mock(PropertyChangeEvent.class);
        when(mockEvent.getNewValue()).thenReturn(mock(SearchState.class));

        // Simulate the propertyChange method being called
        searchView.propertyChange(mockEvent);

        // Verify that the necessary methods were called
        // (you might need to update this based on what should happen in the propertyChange method)
        verify(mockSearchViewModel, times(1)).getState();
    }
}