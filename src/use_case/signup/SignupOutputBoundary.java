package use_case.signup;

import use_case.signup.SignupOuputData;

public interface SignupOutputBoundary {

    void prepareSuccessView(SignupOuputData user);

    void prepareFailView(String error);
}
