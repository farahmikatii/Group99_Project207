package interface_adapter.uploads;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class UploadsViewModel extends ViewModel {

    public final String TITLE_LABEL = "Uploaded Recipes";

    private UploadsState state = new UploadsState();

    public final String BACK_BUTTON_LABEL = "back";

    public UploadsViewModel(){super("Uploaded Recipes");}

    public void setState(UploadsState state){this.state = state;}

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public UploadsState getState(){return state;}
}
