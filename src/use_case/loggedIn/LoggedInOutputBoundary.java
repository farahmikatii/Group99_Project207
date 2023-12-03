package use_case.loggedIn;

import use_case.login.LoginOutputData;

public interface LoggedInOutputBoundary {
    void prepareSuccessView(LoggedInOutputData loggedInOutputData);
}
