package interface_adapter.resultSearch;

import org.junit.Test;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ResultViewModelTest {
    private ResultViewModel resultViewModel;
    private PropertyChangeListener listener;

    @Test
    public void testInitialState() {
        ResultViewModel resultViewModel1 = new ResultViewModel();
        ResultState initialState = resultViewModel1.getState();

        assertNotNull(initialState);
        assertEquals(new ArrayList<>(), initialState.getRecipesList());
    }

    @Test
    public void testSetState() {
        resultViewModel = new ResultViewModel();
        listener = mock(PropertyChangeListener.class);
        resultViewModel.addPropertyChangeListener(listener);
        ResultState newState = new ResultState();
        newState.setRecipeListError("testState");

        resultViewModel.setState(newState);

        ResultState updatedState = resultViewModel.getState();
        assertEquals("testState", updatedState.getRecipeListError());
    }

    @Test
    public void testFirePropertyChanged() {
        resultViewModel = new ResultViewModel();
        listener = mock(PropertyChangeListener.class);
        resultViewModel.addPropertyChangeListener(listener);
        resultViewModel.firePropertyChanged();

        verify(listener, times(1)).propertyChange(any());
    }

}