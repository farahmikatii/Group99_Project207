package app;

import data_access.FileUserDataAccessObject;
import data_access.APICallDataAccessObject;
import entity.CommonUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.signup.SignupViewModel;
import view.LoggedInView;
import view.SignupView;
import view.ViewManager;
import view.LoginView;
import interface_adapter.login.LoginViewModel;


import okhttp3.*;
import okio.BufferedSink;
import okio.Okio;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class Main {
    public static void main(String[] args) throws Exception {
        JFrame application = new JFrame("Reciepe Flow");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();

        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./users.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject);
        views.add(loginView, loginView.viewName);

        LoggedInView loggedInView = new LoggedInView(loggedInViewModel);
        views.add(loggedInView, loggedInView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);

        //API STUFF
        // Notes for running: first run the try catch and comment out the code below it,
        // then when you get the file from the try catch run the code below with the proper path
        try{
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://api.edamam.com/api/recipes/v2?app_id=0e94da52&app_key=%20a1c655a3813bf3c3fc6362ee953aa8e3&type=public&mealType=breakfast&mealType=brunch&mealType=lunch&mealType=dinner&mealType=snack&mealType=teatime")
                    .get()
                    .build();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                assert response.body() != null;
                String filePath = "response_output.json"; // Change the file extension or name as needed

                // Write the response to a file
                try (BufferedSink sink = Okio.buffer(Okio.sink(new File(filePath))) ) {
                    sink.writeAll(response.body().source());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("API response saved to file: " + filePath);
            }
            response.close();
        } catch(IOException e) {
            throw new IOException("error");
        }

        //To comment out and change path after runing try catch
        try{
            String jsonFile = "C:/Working/UoFT/Year 2/CSC207/shar2435/Group99_Project207/response_output.json";
            String file = APICallDataAccessObject.readFileAsString(jsonFile);
            APICallDataAccessObject.jsonToCsv(file);
        }
        catch(IOException e){
            throw new NoSuchFileException("C:/Working/UoFT/Year 2/CSC207/shar2435/Group99_Project207/response_output.json");
        }






    }
}
