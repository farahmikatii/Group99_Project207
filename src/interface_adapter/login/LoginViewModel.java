package interface_adapter.login;

import interface_adapter.login.LoginState;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Login";
    public static final String USERNAME_LABEL = "Enter username";
    public static final String PASSWORD_LABEL = "Enter password";

    public static final String LOGIN_BUTTON_LABEL = "Login";

    private LoginState state = new LoginState();

    public LoginViewModel() {
        super("login");
    }

    public void setState(LoginState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // The Login Presenter will call this to let the ViewModel know to alert the view

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public LoginState getState() {
        return state;
    }
}
