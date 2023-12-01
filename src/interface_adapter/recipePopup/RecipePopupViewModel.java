package interface_adapter.recipePopup;

import interface_adapter.ViewModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.login.LoginState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RecipePopupViewModel extends ViewModel {

    private RecipePopupState state = new RecipePopupState();
    private String recipeLabel;

    public  final String RECIPE_LABEL = recipeLabel;

    public RecipePopupViewModel() {
        super("recipePopup");
    }

    public void setState(RecipePopupState state) {
        this.state = state;
    }

    public void setRecipeLabel(String recipeLabel) {
        this.recipeLabel = recipeLabel;
        firePropertyChanged();
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
        support.firePropertyChange("recipeLabel", null, this.recipeLabel);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public RecipePopupState getState() {
        return state;
    }
}

