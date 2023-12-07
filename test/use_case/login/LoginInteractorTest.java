package use_case.login;

import data_access.FileUserDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.CommonUserFactory;
import entity.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.login.*;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

public class LoginInteractorTest {

    @Test
    void successTest() throws IOException {
        LoginInputData inputData = new LoginInputData("Farah", "password");
        UserFactory userFactory = new CommonUserFactory();
        LoginUserDataAccessInterface loginUserDataAccessInterface = new FileUserDataAccessObject("/Users/duahussain/IdeaProjects/new2/Group99_Project207/test/user1.csv", userFactory);

        LoginOutputBoundary loginPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertEquals("Farah", user.getUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");

            }
        };

        LoginInputBoundary interactor = new LoginInteractor(loginUserDataAccessInterface, loginPresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureAccountDoesNotExistTest() throws IOException {
        LoginInputData inputData = new LoginInputData("Farah", "password");
        UserFactory userFactory = new CommonUserFactory();
        LoginUserDataAccessInterface loginUserDataAccessInterface = new FileUserDataAccessObject("/Users/duahussain/IdeaProjects/new2/Group99_Project207/test/user1.csv", userFactory);

        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected.");

            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Farah" + ": Account does not exist.", error);
            }
        };

        LoginInputBoundary interactor = new LoginInteractor(loginUserDataAccessInterface, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void incorrectPasswordTest() throws IOException {
        LoginInputData inputData = new LoginInputData("Farah", "password1");
        UserFactory userFactory = new CommonUserFactory();
        LoginUserDataAccessInterface loginUserDataAccessInterface = new FileUserDataAccessObject("/Users/duahussain/IdeaProjects/new2/Group99_Project207/test/user1.csv", userFactory);

        LoginOutputBoundary loginOutputBoundary = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Incorrect password for " + "Farah" + ".", error);
            }
        };
        LoginInputBoundary interactor = new LoginInteractor(loginUserDataAccessInterface, loginOutputBoundary);
        interactor.execute(inputData);
    }

    @Test
    void setUsernameTest() throws IOException {
        LoginInputData inputData = new LoginInputData("Farah", "password1");
        UserFactory userFactory = new CommonUserFactory();
        LoginUserDataAccessInterface loginUserDataAccessInterface = new FileUserDataAccessObject("/Users/duahussain/IdeaProjects/new2/Group99_Project207/test/user1.csv", userFactory);

        LoginOutputBoundary loginOutputBoundary = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {

            }

            @Override
            public void prepareFailView(String error) {

            }
        };
        LoginInputBoundary interactor = new LoginInteractor(loginUserDataAccessInterface, loginOutputBoundary);
        interactor.setUsername(inputData);

        assertEquals(loginUserDataAccessInterface.get(inputData.getUsername()).getName(), inputData.getUsername());
    }

}

