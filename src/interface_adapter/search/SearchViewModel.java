package interface_adapter.search;

import interface_adapter.ViewModel;
import interface_adapter.search.SearchState;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchViewModel extends ViewModel {

    public final String TITLE_LABEL = "Search";

    private SearchState state = new SearchState();

    public static final String BACK_BUTTON_LABEL = "back";

    public static final String SEARCH_BUTTON_LABEL = "search";

    public static final String SELECT_BUTTON_LABEL = "select";

    public SearchViewModel(){super("search");}

    public void setState(SearchState state) {this.state = state;}

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged(){support.firePropertyChange("state", null, this.state);}

    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }
    public SearchState getState(){return state;}
}
