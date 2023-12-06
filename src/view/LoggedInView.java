package view;

import data_access.CommonRecipeDataAccessObject;
import entity.CommonRecipe;
import interface_adapter.ViewManagerModel;

import interface_adapter.logged_in.LoggedInController;

import interface_adapter.logged_in.LoggedInState;

import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.recipePopup.RecipePopupState;
import interface_adapter.recipePopup.RecipePopupViewModel;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.BufferedSink;
import okio.Okio;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import java.nio.file.Files;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;

    private final ViewManagerModel viewManagerModel;

    private final ProfileViewModel profileViewModel;

    private final RecipePopupViewModel recipePopupViewModel;
    //private final LoggedInController loggedInController;

    private final SearchViewModel searchViewModel;


    JLabel username;


    final JButton logOut;
    final JButton search;
    final JButton account;
    JButton recipeImage;
    String file;
    private int previousScrollValue = 0;
    private int prevHorizontalValue = 0;
    public LoggedInView(LoggedInViewModel loggedInViewModel, ViewManagerModel viewManagerModel, ProfileViewModel profileViewModel, RecipePopupViewModel recipePopupViewModel, SearchViewModel searchViewModel) throws Exception {
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
        this.profileViewModel = profileViewModel;
        this.searchViewModel = searchViewModel;


        this.recipePopupViewModel = recipePopupViewModel;

        loggedInViewModel.addPropertyChangeListener(this);
        profileViewModel.addPropertyChangeListener(this);
        viewManagerModel.addPropertyChangeListener(this);
        searchViewModel.addPropertyChangeListener(this);


        ImageIcon titleImage = new ImageIcon("./src/app_pictures/title_logo.png");
        JLabel title = new JLabel(titleImage);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        String jsonFile = "./response_output.json";
        file = CommonRecipeDataAccessObject.readFileAsString(jsonFile);
        final CommonRecipeDataAccessObject[] commonRecipeDAO = {new CommonRecipeDataAccessObject(file)}; // replace jsonFile with the actual JSON file content or path

        // Call returnRecipeList method
        final List<CommonRecipe>[][][] recipesList = new List[][][]{new List[][]{new List[]{commonRecipeDAO[0].returnRecipeList(1)}}};

        JPanel searchButton = new JPanel();
        JPanel accountButton = new JPanel();
        JPanel recipes = new JPanel();
        JScrollPane scroll = new JScrollPane(recipes);

        logOut = new JButton(loggedInViewModel.LOGOUT_BUTTON_LABEL);
        search = new JButton(loggedInViewModel.SEARCH_BUTTON_LABEL);
        account = new JButton(loggedInViewModel.ACCOUNT_BUTTON_LABEL);
        logOut.setBackground(new Color(254,232,210));
        search.setBackground(new Color(254,232,210));
        account.setBackground(new Color(254,232,210));

        recipes.setLayout(new GridLayout(0,4,5,5));

        searchButton.add(account);
        searchButton.add(search);
        searchButton.add(logOut);

        for (CommonRecipe recipe : recipesList[0][0][0]){
            ImageIcon saveRecipeImage = new ImageIcon(recipe.getImage());
            recipeImage = new JButton(cutName(recipe.getRecipeName()), saveRecipeImage);
            //setting position of label of recipe
            recipeImage.setVerticalTextPosition(SwingConstants.TOP);
            recipeImage.setHorizontalTextPosition(SwingConstants.CENTER);
            recipeImage.addActionListener(
                    // This creates an anonymous subclass of ActionListener and instantiates it.
                    new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if (evt.getSource() instanceof JButton sourceButton) {
                                if (evt.getSource().equals(sourceButton)) {
                                    RecipePopupState currentPopupState = recipePopupViewModel.getState();
                                    currentPopupState.setRecipe(recipe);
                                    currentPopupState.setRecipeLabel(recipe);
                                    currentPopupState.setImageUrl(recipe);
                                    currentPopupState.setRecipeUrl(recipe);
                                    currentPopupState.setIngredients(recipe);
                                    currentPopupState.setComingFrom("loggedin");


                                    System.out.println(currentPopupState);

                                    recipePopupViewModel.setState(currentPopupState);
                                    System.out.println(recipePopupViewModel.getState());

                                    recipePopupViewModel.firePropertyChanged();

                                    viewManagerModel.setActiveView(recipePopupViewModel.getViewName());
                                    System.out.println(viewManagerModel.getActiveView());
                                    viewManagerModel.firePropertyChanged();

                                }


                            }
                        }
                    }
            );
            recipes.add(recipeImage);
        }

        //Lazy loading
        scroll.getVerticalScrollBar().addAdjustmentListener(
                new AdjustmentListener() {
                    @Override
                    public void adjustmentValueChanged(AdjustmentEvent e) {
                        JScrollBar verticalScrollBar = scroll.getVerticalScrollBar();
                        JScrollBar horizontalScrollBar = scroll.getHorizontalScrollBar();

                        if (verticalScrollBar.getValue() == verticalScrollBar.getMaximum() - verticalScrollBar.getVisibleAmount()) {

                            previousScrollValue = verticalScrollBar.getValue();
                            prevHorizontalValue = horizontalScrollBar.getValue();

                            JSONObject jsonObject = new JSONObject(file);
                            JSONObject links = jsonObject.getJSONObject("_links");


                            if (!Objects.equals(links.toString(), "{}")) {
                                JSONObject next = links.getJSONObject("next");
                                String nextUrl = next.getString("href");

                                System.out.println(nextUrl);
                                File folder = new File("./src/images");
                                deleteFolder(folder);

                                try{
                                    OkHttpClient client = new OkHttpClient();

                                    Request request = new Request.Builder()
                                            .url(nextUrl)
                                            .get()
                                            .build();

                                    Response response = client.newCall(request).execute();
                                    if (response.isSuccessful()) {
                                        assert response.body() != null;
                                        //String filePath = "C:/Working/UoFT/Year 2/CSC207/shar2435/Group99_Project207/response_output.json"; // Change the file extension or name as needed
                                        String filePath = "./response_output.json";


                                        // Write the response to a file
                                        try (BufferedSink sink = Okio.buffer(Okio.sink(new File(filePath))) ) {
                                            sink.writeAll(response.body().source());
                                        } catch (IOException ex) {
                                            ex.printStackTrace();
                                        }

                                        System.out.println("API response saved to file: " + filePath);
                                    }
                                    response.close();
                                } catch(IOException ex) {
                                    try {
                                        throw new IOException("error");
                                    } catch (IOException exc) {
                                        throw new RuntimeException(exc);
                                    }
                                }

                                try {
                                    file = CommonRecipeDataAccessObject.readFileAsString(jsonFile);
                                } catch (Exception ex) {
                                    throw new RuntimeException(ex);
                                }

                                CommonRecipeDataAccessObject[] commonRecipeDAO = {new CommonRecipeDataAccessObject(file)};

                                // Call returnRecipeList method
                                recipesList[0] = new List[][]{new List[]{commonRecipeDAO[0].returnRecipeList(1)}};

                                System.out.println(Arrays.toString(recipesList[0][0]));

                                for (CommonRecipe recipe : recipesList[0][0][0]){
                                    ImageIcon saveRecipeImage = new ImageIcon(recipe.getImage());
                                    recipeImage = new JButton(cutName(recipe.getRecipeName()), saveRecipeImage);
                                    //setting position of label of recipe
                                    recipeImage.setVerticalTextPosition(SwingConstants.TOP);
                                    recipeImage.setHorizontalTextPosition(SwingConstants.CENTER);
                                    recipeImage.addActionListener(
                                            // This creates an anonymous subclass of ActionListener and instantiates it.
                                            new ActionListener() {
                                                public void actionPerformed(ActionEvent evt) {

                                                    if (evt.getSource() instanceof JButton sourceButton) {
                                                        if (evt.getSource().equals(sourceButton)) {

                                                            RecipePopupState currentPopupState = recipePopupViewModel.getState();
                                                            System.out.println(currentPopupState);


                                                       
                                                            currentPopupState.setRecipe(recipe);
                                                            currentPopupState.setRecipeLabel(recipe);
                                                            currentPopupState.setImageUrl(recipe);
                                                            currentPopupState.setComingFrom("loggedin");
                                                            System.out.println(currentPopupState);


                                                            recipePopupViewModel.setState(currentPopupState);
                                                            System.out.println(recipePopupViewModel.getState());

                                                            
                                                            

                                                            recipePopupViewModel.firePropertyChanged();


                                                            viewManagerModel.setActiveView(recipePopupViewModel.getViewName());
                                                            System.out.println(viewManagerModel.getActiveView());
                                                            viewManagerModel.firePropertyChanged();

                                                        }


                                                    }


                                                }
                                            }
                                    );
                                    recipes.add(recipeImage);
                                }
                            }

                            revalidate();
                            repaint();

                            verticalScrollBar.setValue(previousScrollValue);
                            horizontalScrollBar.setValue(prevHorizontalValue);
                            //}
                        }
                    }
                }
        );

        accountButton.setLocation(0,0);

        logOut.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logOut)) {
//                            SignupState currentSignupState = signupViewModel.getState();
//                            signupViewModel.setState(currentSignupState);
//                            signupViewModel.firePropertyChanged();
//                            viewManagerModel.setActiveView(signupViewModel.getViewName());
//                            viewManagerModel.firePropertyChanged();

                            System.exit(0);
//                            LoggedInOutputBoundary.confirmation("You have been logged out.");
                        }
                    }
                }
            
        );

        search.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(search)) {
                            SearchState currentSearchState = searchViewModel.getState();
                            searchViewModel.setState(currentSearchState);
                            searchViewModel.firePropertyChanged();
                            viewManagerModel.setActiveView(searchViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        account.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(account)) {
                            ProfileState currentLoginState = profileViewModel.getState();
                            System.out.println(currentLoginState);
                            profileViewModel.setState(currentLoginState);
                            System.out.println(profileViewModel.getState());
                            profileViewModel.firePropertyChanged();
                            viewManagerModel.setActiveView(profileViewModel
                                    .getViewName());
                            System.out.println(viewManagerModel.getActiveView());
                            viewManagerModel.firePropertyChanged();

                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(searchButton);
        this.add(scroll);

        searchButton.setLocation(0,0);
    }
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        Object newValue = evt.getNewValue();
        if (newValue instanceof LoggedInState) {
            LoggedInState newState = (LoggedInState) newValue;
            System.out.println(newState.getUsername());
        }

    }

    private static void deleteFolder(File folder) {
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteFolder(file);
                } else {
                    file.delete();
                }
            }
        }

        folder.delete();
    }

    public static String cutName(String name) {
        //Checking if name is longer than 45 chars
        if (name.length() > 42) {
            return name.substring(0, 42) + "...";
        } else {
            return name;
        }
    }
}
