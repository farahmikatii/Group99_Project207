package use_case.signup;

import use_case.signup.SignupOutputData;

public interface SignupOutputBoundary {

    void prepareSuccessView(SignupOutputData user);

    void prepareFailView(String error);
}
