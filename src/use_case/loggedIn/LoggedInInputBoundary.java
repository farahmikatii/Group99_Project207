package use_case.loggedIn;

import use_case.login.LoginInputData;

public interface LoggedInInputBoundary {
    void execute(LoggedInInputData loggedInInputData);
}
