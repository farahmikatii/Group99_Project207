package interface_adapter.logged_in;

import interface_adapter.ViewModel;
import interface_adapter.login.LoginState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoggedInViewModel extends ViewModel {
    public final String TITLE_LABEL = "Logged In View";

    private  LoggedInState state = new LoggedInState();

    public static final String LOGOUT_BUTTON_LABEL = "Log out";
    public final String SEARCH_BUTTON_LABEL = "Search";
    public final String ACCOUNT_BUTTON_LABEL = "Account";


    public LoggedInViewModel(){super("logged in");}

    public void setState(LoggedInState state) {this.state = state;}

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged()
    {support.firePropertyChange("state", null, this.state);}

    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }

    public LoggedInState getState(){return state;}
}
