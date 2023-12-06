package app;

import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.recipePopup.RecipePopupState;
import interface_adapter.resultSearch.ResultViewModel;
import interface_adapter.saved.SavedViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.uploadedRecipe.UploadedRecipeState;
import interface_adapter.uploadedRecipe.UploadedRecipeViewModel;
import interface_adapter.uploading.UploadingViewModel;
import interface_adapter.uploads.UploadsViewModel;
import interface_adapter.saved.SavedController;


import interface_adapter.recipePopup.RecipePopupViewModel;
import interface_adapter.search.SearchViewModel;
import view.*;
import interface_adapter.login.LoginViewModel;


import okhttp3.*;
import okio.BufferedSink;
import okio.Okio;

import java.io.File;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {



        //API STUFF
        // Notes for running: first run the try catch and comment out the code below it,
        // then when you get the file from the try catch run the code below with the proper path
        try{
            OkHttpClient client = new OkHttpClient();

            String[] cuisines = {"american", "asian", "british", "caribbean", "central europe",
                    "chinese", "eastern europe", "french", "greek", "indian", "italian", "japanese",
                    "korean", "kosher", "mediterranean", "mexican", "middle eastern", "nordic",
                    "south american", "south east asian", "world"};

            List<String> cuisinesList = Arrays.asList(cuisines);
            Random random = new Random();
            String randCuisine = cuisinesList.get(random.nextInt(cuisinesList.size()));

            Request request = new Request.Builder()
                    .url("https://api.edamam.com/api/recipes/v2?app_id=ce388b32&app_key=99c8807d127dc1f464c4e9d959b9446d&type=public&cuisineType="
                            + randCuisine)
                    .get()
                    .build();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                assert response.body() != null;

                //String filePath = "/Users/farahmikati/IdeaProjects/Group99_Project207/response_output.json"; // Change the file extension or name as needed
                //String filePath = "/Users/farahmikati/IdeaProjects/Group99_Project207/response_output.json"; // Change the file extension or name as needed
                //String filePath = "C:/Working/UoFT/Year 2/CSC207/shar2435/Group99_Project207/response_output.json"; // Change the file extension or name as needed
                String filePath = "/Users/sedakchuckal/IdeaProjects/Group99_Project207/user.csv"; // Change the file extension or name as needed

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
//        try{
//            BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/duahussain/IdeaProjects/Group99_Project207/saved.csv"));
//            writer.write("username,savedList");
//            writer.newLine();
//            writer.close();
//
//        } catch (IOException ex) {
//            throw new RuntimeException(ex);
//
//        }

        JFrame application = new JFrame("Recipe Flow");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ImageIcon egg = new ImageIcon("./src/app_pictures/egg2.png");
        application.setIconImage(egg.getImage());

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        ProfileViewModel profileViewModel = new ProfileViewModel();
        SavedViewModel savedViewModel = new SavedViewModel();
        UploadsViewModel uploadsViewModel = new UploadsViewModel();
        UploadingViewModel uploadingViewModel = new UploadingViewModel();
        SearchViewModel searchViewModel = new SearchViewModel();
        ResultViewModel resultViewModel = new ResultViewModel();
        UploadedRecipeViewModel uploadedRecipeViewModel = new UploadedRecipeViewModel();

        RecipePopupViewModel recipePopupViewModel = new RecipePopupViewModel();
        RecipePopupState recipePopupState = new RecipePopupState();

        UploadedRecipeState uploadedRecipeState = new UploadedRecipeState();


        FileUserDataAccessObject userDataAccessObject;
        try {


           

            userDataAccessObject = new FileUserDataAccessObject("./user.csv", new CommonUserFactory());


            //userDataAccessObject = new FileUserDataAccessObject("C:/Working/UoFT/Year 2/CSC207/shar2435/Group99_Project207/users.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject, signupViewModel, recipePopupViewModel);
        views.add(loginView, loginView.viewName);

        LoggedInView loggedInView = new LoggedInView(loggedInViewModel, viewManagerModel, profileViewModel, recipePopupViewModel, searchViewModel);

        views.add(loggedInView, loggedInView.viewName);

        //ProfileView profileView = new ProfileView(uploadingViewModel, profileViewModel, viewManagerModel, savedViewModel, uploadsViewModel, loggedInViewModel, uploadingController);
        ProfileView profileView = ProfileUseCaseFactory.create(viewManagerModel, profileViewModel, uploadingViewModel, uploadsViewModel, savedViewModel, userDataAccessObject, loggedInViewModel);
        views.add(profileView, profileView.viewName);

        //SavedView savedView = new SavedView(savedViewModel, viewManagerModel, profileViewModel, savedController);
        //views.add(savedView, savedView.viewName); //needs to be a factory like UploadsView

        SavedView savedView = SavingUseCaseFactory.create(
                viewManagerModel, savedViewModel, profileViewModel);
        views.add(savedView, savedView.viewName);

        //RecipePopupView recipePopupView = RecipePopupUseCaseFactory.create(viewManagerModel,recipePopupViewModel, userDataAccessObject, loggedInViewModel)

        RecipePopupView recipePopupView = new RecipePopupView(viewManagerModel, recipePopupViewModel, loggedInViewModel, resultViewModel, savedViewModel);
        views.add(recipePopupView, recipePopupView.viewName);

        UploadingView uploadingView = UploadingUseCaseFactory.create(viewManagerModel, uploadingViewModel, profileViewModel, uploadsViewModel, userDataAccessObject);
        views.add(uploadingView, uploadingView.viewName);

        UploadsView uploadsView = UploadsUseCaseFactory.create(
                viewManagerModel,
                uploadingViewModel,
                uploadsViewModel,
                profileViewModel,
                userDataAccessObject,
                uploadedRecipeViewModel
        );

        //UploadsView uploadsView = new UploadsView(uploadsViewModel, profileViewModel, viewManagerModel);
        views.add(uploadsView, uploadsView.viewName);

        UploadedRecipeView uploadedRecipeView = new UploadedRecipeView(viewManagerModel, uploadedRecipeState, uploadingViewModel,uploadsViewModel, uploadedRecipeViewModel);
        views.add(uploadedRecipeView, uploadedRecipeView.viewName);

        SearchView searchView = SearchUseCaseFactory.create(viewManagerModel, searchViewModel, loggedInViewModel, resultViewModel);
        views.add(searchView, searchView.viewName);

        ResultsView resultsView = new ResultsView(resultViewModel, recipePopupViewModel, viewManagerModel, loggedInViewModel);
        views.add(resultsView, resultsView.viewName);
        //this is likely to be needed to change after the searchfactory is made

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);



//        //To comment out and change path after running try catch
//        try{
//            String jsonFile = "C:/Users/rahman/Desktop/Year 2/CSC207 - Software Design/Weekly Activities/Group99_Project207/response_output.json";
//            String file = APICallDataAccessObject.readFileAsString(jsonFile);
//            APICallDataAccessObject.jsonToCsv(file);
//        }
//        catch(IOException e){
//            throw new NoSuchFileException("C:/Users/rahman/Desktop/Year 2/CSC207 - Software Design/Weekly Activities/Group99_Project207/response_output.json");
//        }
    }
}
