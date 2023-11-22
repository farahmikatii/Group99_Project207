package interface_adapter.profile;

public class ProfileState {
    private String username = "" ;

    public ProfileState(ProfileState copy) {username = copy.username;}

    public ProfileState(){};

    public String getUsername(){return username;}

    public void setUsername(String username){
        this.username = username;
    }
}
