package interface_adapter.search;

import org.junit.Test;

import java.beans.PropertyChangeListener;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SearchViewModelTest {
    private SearchViewModel searchViewModel;
    private PropertyChangeListener listener;

    @Test
    public void testInitialState() {
        SearchViewModel searchViewModel1 = new SearchViewModel();
        SearchState initialState = searchViewModel1.getState();

        assertNotNull(initialState);
        assertEquals("", initialState.getCuisine());
    }

    @Test
    public void testSetState() {
        searchViewModel = new SearchViewModel();
        listener = mock(PropertyChangeListener.class);
        searchViewModel.addPropertyChangeListener(listener);
        SearchState newState = new SearchState();
        newState.setCuisine("Indian");

        searchViewModel.setState(newState);

        SearchState updatedState = searchViewModel.getState();
        assertEquals("Indian", updatedState.getCuisine());
    }

    @Test
    public void testFirePropertyChanged() {
        searchViewModel = new SearchViewModel();
        listener = mock(PropertyChangeListener.class);
        searchViewModel.addPropertyChangeListener(listener);
        searchViewModel.firePropertyChanged();

        verify(listener, times(1)).propertyChange(any());
    }

}