package interface_adapter.uploadedRecipe;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class UploadedRecipeViewModel extends ViewModel {

    public final String BACK_BUTTON_LABEL = "Back to Uploads";
    private UploadedRecipeState state = new UploadedRecipeState();

    //TODO: change to actual recipe name
    public UploadedRecipeViewModel() {
        super("Uploaded Recipe");
    }

    public UploadedRecipeState getState() { return state;}

    public void setState(UploadedRecipeState uploadedRecipeState) {
        this.state = state;
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);

    }
}
