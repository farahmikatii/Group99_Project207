package interface_adapter.profile;
import interface_adapter.ViewModel;
import interface_adapter.logged_in.LoggedInState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ProfileViewModel extends ViewModel{


   // private static final LoggedInState loggedinstate = new LoggedInState();

//    private static final LoggedInState loggedin_state = new LoggedInState();
//    public static final String SAVED_BUTTON_LABEL = "Saved Recipes";
//    public static final String UPLOAD_BUTTON_LABEL = "Uploaded Recipes";
//    public static final String UPLOAD_NEW_BUTTON_LABEL = "Upload New Recipe";
//
//    public static final String TITLE_LABEL = loggedin_state.getUsername();
//
//    private ProfileState state = new ProfileState();
//
//    public ProfileViewModel(){super(loggedin_state.getUsername());}
//
//    public void setState(ProfileState state) {this.state = state;}
//
//    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
//
//    public void firePropertyChanged(){support.firePropertyChange("state", null, this.state);}
//
//    public void addPropertyChangeListener(PropertyChangeListener listener){
//        support.addPropertyChangeListener(listener);
//    }
//
//    public ProfileState getState(){return state;}


    public static final String SAVED_BUTTON_LABEL = "Saved Recipes";
    public static final String UPLOAD_BUTTON_LABEL = "Uploaded Recipes";
    public static final String UPLOAD_NEW_BUTTON_LABEL = "Upload New Recipe";

    public static final String BACK_BUTTON_LABEL = "Back";


    private ProfileState state = new ProfileState();


//TODO: change this so that it is the user's username, Loggedinstate is defaulting to ""
    public ProfileViewModel()
    {super("profile");}

    private static final LoggedInState loggedInState = new LoggedInState();
    public static final String TITLE_LABEL = loggedInState.getUsername();
    public static final String PROFILE_STATE_PROPERTY_NAME = "profileState";

    public void setState(ProfileState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    public void firePropertyChanged() {
        support.firePropertyChange(PROFILE_STATE_PROPERTY_NAME, null, this.state);
    }


    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }


    public ProfileState getState(){
        return state;}



}



