package interface_adapter;

import org.junit.Test;

import java.beans.PropertyChangeListener;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class ViewManagerModelTest {

    private PropertyChangeListener listener;

    private ViewManagerModel viewManagerModel;

    @Test
    public void testSetActiveView() {
        // Arrange
        ViewManagerModel viewManagerModel = new ViewManagerModel();

        // Act
        viewManagerModel.setActiveView("TestView");

        // Assert
        assertEquals("TestView", viewManagerModel.getActiveView());
    }

    @Test
    public void testFirePropertyChanged() {
        // Arrange
        viewManagerModel = new ViewManagerModel();
        listener = mock(PropertyChangeListener.class);
        viewManagerModel.addPropertyChangeListener(listener);

        // Act
        viewManagerModel.setActiveView("TestView");
        viewManagerModel.firePropertyChanged();

        // Assert
        verify(listener, times(1)).propertyChange(any());
    }

    @Test
    public void testAddPropertyChangeListener() {
        // Arrange
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        PropertyChangeListener mockListener = mock(PropertyChangeListener.class);

        // Act
        viewManagerModel.addPropertyChangeListener(mockListener);

        // Assert
        assertTrue(viewManagerModel.support.getPropertyChangeListeners().length > 0);
    }

}