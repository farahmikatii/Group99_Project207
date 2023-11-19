package interface_adapter.profile_page;

public class ProfileState {
    private String username = "" ;

    public ProfileState(ProfileState copy) {username = copy.username;}

    public ProfileState(){};

    public String getUsername(){return username;}
}
