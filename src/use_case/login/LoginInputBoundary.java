package use_case.login;

import use_case.loggedIn.LoggedInInputData;

public  interface LoginInputBoundary {
    void execute(LoginInputData loginInputData);


}