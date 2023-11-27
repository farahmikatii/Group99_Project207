package interface_adapter.saved;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SavedViewModel extends ViewModel {

    public final String TITLE_LABEL = "Saved Recipes";

    private SavedState state = new SavedState();

    public final String BACK_BUTTON_LABEL = "back";

    public SavedViewModel(){super("saved recipes");}

    public void setState(SavedState state){this.state = state;}

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public SavedState getState(){return state;}
}
