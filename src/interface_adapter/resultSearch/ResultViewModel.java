package interface_adapter.resultSearch;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ResultViewModel extends ViewModel {
    public final String TITLE_LABEL = "Filtered Results";

    private ResultState state = new ResultState();
    public static final String BACK_BUTTON_LABEL = "Back";

    public ResultViewModel() {super("Filtered Results");}

    public void setState(ResultState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged()
    {support.firePropertyChange("state", null, this.state);}

    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }

    public ResultState getState() {
        return state;
    }
}
