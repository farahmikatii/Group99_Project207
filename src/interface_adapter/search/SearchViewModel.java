package interface_adapter.search;

import interface_adapter.ViewModel;
import interface_adapter.search.SearchState;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchViewModel extends ViewModel {

    public final String TITLE_LABEL = "search";

    private SearchState state = new SearchState();

    public static final String BACK_BUTTON_LABEL = "back";

    public static final String SEARCH_BUTTON_LABEL = "search";

    public static final String SELECT_BUTTON_LABEL = "select";

    public static final String SELECT1_BUTTON_LABEL = "select";

    public static final String SELECT2_BUTTON_LABEL = "select";

    public static final String SELECT3_BUTTON_LABEL = "select";

    public static final String SELECT4_BUTTON_LABEL = "select";

    public static final String SELECT5_BUTTON_LABEL = "select";

    public static final String SELECT6_BUTTON_LABEL = "select";


    public SearchViewModel(){super("search");}

    public void setState(SearchState state) {this.state = state;}

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged(){support.firePropertyChange("state", null, this.state);}

    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }
    public SearchState getState(){return state;}
}
