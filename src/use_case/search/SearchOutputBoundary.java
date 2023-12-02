package use_case.search;

import use_case.signup.SignupOutputData;

public interface SearchOutputBoundary {
    void prepareSuccessView(SearchOutputData searchOutputData);

    void prepareFailView(String error);
}
