package interface_adapter.uploading;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class UploadingViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Uploading New Recipe";

    public static final String RECIPE_LABEL = "Recipe Title";

    public static final String INGREDIENTS_LABEL = "Ingredients";

    public static final String INSTRUCTIONS_LABEL = "Instructions";

    public static final String UPLOAD_BUTTON_LABEL = "Upload";

    public static final String BACK_BUTTON_LABEL = "Back";

    public static final String RECIPE_PHOTO_LABEL = "Upload Recipe Image";

    private UploadingState state = new UploadingState();

    public UploadingViewModel(){super("Uploading New Recipe");}

    public void setState(UploadingState copy){this.state = state;}

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public UploadingState getState(){return state;}
}
