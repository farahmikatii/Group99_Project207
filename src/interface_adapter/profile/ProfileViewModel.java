package interface_adapter.profile;
import interface_adapter.ViewModel;
import interface_adapter.logged_in.LoggedInState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ProfileViewModel extends ViewModel{

   // private static final LoggedInState loggedinstate = new LoggedInState();
    public static final String SAVED_BUTTON_LABEL = "Saved Recipes";
    public static final String UPLOAD_BUTTON_LABEL = "Uploaded Recipes";
    public static final String UPLOAD_NEW_BUTTON_LABEL = "Upload New Recipe";

    public static final String TITLE_LABEL = "Account";

    private ProfileState state = new ProfileState();


//TODO: change this so that it is the user's username, Loggedinstate is defaulting to ""
    public ProfileViewModel(){super("profile");}

    public void setState(ProfileState state) {this.state = state;}

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged(){
        support.firePropertyChange("state", null, this.state);}

    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }

    public ProfileState getState(){
        return state;}
}


