package interface_adapter.logged_in;

public class LoggedInState {
    private String username = " ";

    private static String usernameError = null;

    public LoggedInState(LoggedInState copy){
        username = copy.username;
    }

    public LoggedInState(){}

    public String getUsername(){
        return username;
    }

    public static String getUsernameError() {
        return usernameError;
    }

    public void setUsername(String username){
        this.username = username;
    }
}
