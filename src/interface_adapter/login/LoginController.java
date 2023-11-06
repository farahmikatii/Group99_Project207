package interface_adapter.login;

import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

public class LoginController {

    final LoginInputBoundary userLoginInteractor;
    public LoginController(LoginInputBoundary userLoginInteractor) {
        this.userLoginInteractor = userLoginInteractor;
    }

    public void execute(String username, String password) {
        LoginInputData loginInputData = new LoginInputData(
                username, password);

        userLoginInteractor.execute(loginInputData);
    }
}
