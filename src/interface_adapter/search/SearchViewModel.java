package interface_adapter.search;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchViewModel extends ViewModel {
    public final String TITLE_LABEL = "Search Recipes";

    private SearchState state = new SearchState();
    public static final String BACK_BUTTON_LABEL = "Back";

    public SearchViewModel(){super("Filtered Search");}

    public void setState(SearchState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged()
    {support.firePropertyChange("state", null, this.state);}

    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }

    public SearchState getState() {
        return state;
    }
}
