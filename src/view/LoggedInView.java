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



        JLabel title = new JLabel("Recipe Flow");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        //String jsonFile = "/Users/duahussain/IdeaProjects/Group99_Project207/response_output.csv";

        //String jsonFile = "/Users/farahmikati/IdeaProjects/Group99_Project207/response_output.json";
        //String jsonFile = "/Users/duahussain/IdeaProjects/Group99_Project207/response_output.csv";
        //String jsonFile = "/Users/farahmikati/IdeaProjects/Group99_Project207/response_output.json";
        String jsonFile = "C:/Working/UoFT/Year 2/CSC207/shar2435/Group99_Project207/response_output.json";
        file = CommonRecipeDataAccessObject.readFileAsString(jsonFile);
        final CommonRecipeDataAccessObject[] commonRecipeDAO = {new CommonRecipeDataAccessObject(file)}; // replace jsonFile with the actual JSON file content or path

        // Call returnRecipeList method
        final List<CommonRecipe>[][][] recipesList = new List[][][]{new List[][]{new List[]{commonRecipeDAO[0].returnRecipeList(1)}}};



        //JLabel usernameInfo = new JLabel("Currently Logged in: ");
        //username = new JLabel();

        //JPanel buttons = new JPanel();
        JPanel searchButton = new JPanel();
        JPanel accountButton = new JPanel();
        JPanel recipes = new JPanel();
        JScrollPane scroll = new JScrollPane(recipes);
        //JPanel divider = new JPanel();
        logOut = new JButton(loggedInViewModel.LOGOUT_BUTTON_LABEL);
        search = new JButton(loggedInViewModel.SEARCH_BUTTON_LABEL);
        account = new JButton(loggedInViewModel.ACCOUNT_BUTTON_LABEL);
        //recipesList.get(0).getImage()
        //ImageIcon saveRecipeImage = new ImageIcon(recipesList.get(0).getImage());
        //recipeImage = new JButton(recipesList.get(0).getRecipeName());



        //Panel topRightButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        //searchButton.setLayout(null);
        //accountButton.setLayout(null);
        recipes.setLayout(new GridLayout(0,4,5,5));
        //recipes.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());



        //buttons.add(logOut);
        searchButton.add(search);
        searchButton.add(account);
        //divider.add(logOut);
        //recipes.add(recipeImage);

        for (CommonRecipe recipe : recipesList[0][0][0]){
            ImageIcon saveRecipeImage = new ImageIcon(recipe.getImage());
            recipeImage = new JButton(recipe.getRecipeName(), saveRecipeImage);
            //setting position of label of recipe
            recipeImage.setVerticalTextPosition(SwingConstants.TOP);
            recipeImage.setHorizontalTextPosition(SwingConstants.CENTER);
            recipeImage.addActionListener(
                    // This creates an anonymous subclass of ActionListener and instantiates it.
                    new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
//                            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
//                                try {
//                                    URI uri = new URI(recipe.getRecipeUrl());
//                                    Desktop.getDesktop().browse(uri);
//                                } catch (IOException | URISyntaxException e) {
//                                    e.printStackTrace();
//                                }
//                            } else {
//                                System.out.println("Opening a link is not supported on this platform.");
//                            }
                            if (evt.getSource() instanceof JButton sourceButton) {
                                if (evt.getSource().equals(sourceButton)) {

                                    //recipePopupViewModel.setSelectedRecipeName(selectedRecipeName);
                                    //recipePopupViewModel.setRecipeLabel(recipe.getRecipeName());

                                    RecipePopupState currentPopupState = recipePopupViewModel.getState();
                                    currentPopupState.setRecipe(recipe);
                                    currentPopupState.setRecipeLabel(recipe);
                                    currentPopupState.setImageUrl(recipe);


                                   

                                    System.out.println(currentPopupState);

                                    recipePopupViewModel.setState(currentPopupState);
                                    System.out.println(recipePopupViewModel.getState());

                                    recipePopupViewModel.firePropertyChanged();

                                    viewManagerModel.setActiveView(recipePopupViewModel.getViewName());
                                    System.out.println(viewManagerModel.getActiveView());
                                    viewManagerModel.firePropertyChanged();

                                    //loggedInController.execute(recipe.getRecipeName(), recipe.getRecipeUrl());

                                }


                            }
                        }
                    }
            );
            //recipeImage.setPreferredSize(new Dimension(10, 10));
            recipes.add(recipeImage);
        }

        //Lazy loading
        //scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


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


                                // WE HAVE TO FIGURE OUT SOMEWHERE TO DELETE IT OR ELSE THERE'S IMAGE ISSUES
                                System.out.println(nextUrl);
                                File folder = new File("C:/Working/UoFT/Year 2/CSC207/shar2435/Group99_Project207/src/images");
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
                                        String filePath = "C:/Working/UoFT/Year 2/CSC207/shar2435/Group99_Project207/response_output.json";


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
                                    //String jsonFile = "C:/Users/rahman/Desktop/Year 2/CSC207 - Software Design/Weekly Activities/Group99_Project207/response_output.json";
                                    file = CommonRecipeDataAccessObject.readFileAsString(jsonFile);
                                } catch (Exception ex) {
                                    throw new RuntimeException(ex);
                                }

                                CommonRecipeDataAccessObject[] commonRecipeDAO = {new CommonRecipeDataAccessObject(file)};

                                // Call returnRecipeList method
                                recipesList[0] = new List[][]{new List[]{commonRecipeDAO[0].returnRecipeList(1)}};
                                //recipesList = new List[][]{new List[]{commonRecipeDAO[0].returnRecipeList(1)}}

                                System.out.println(Arrays.toString(recipesList[0][0]));

                                for (CommonRecipe recipe : recipesList[0][0][0]){
                                    ImageIcon saveRecipeImage = new ImageIcon(recipe.getImage());
                                    recipeImage = new JButton(recipe.getRecipeName(), saveRecipeImage);
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

        //accountButton.add(account);
        accountButton.setLocation(0,0);
        //buttons.add(account);
        //search.setBounds(500, 10, 100, 30);
        //account.setBounds(10, 10, 100, 30);
        //account.setBounds(10, -20, 100, 30);

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
        //this.add(usernameInfo);
        //this.add(username);
        //this.add(buttons);
        this.add(searchButton);
        //this.add(accountButton);
        //this.add(divider);
        //this.add(recipes);
        this.add(scroll);

//        File folder = new File("C:/Users/rahman/Desktop/Year 2/CSC207 - Software Design/Weekly Activities/Group99_Project207/src/images");
//        .setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                // Call method to delete the folder
//                deleteFolder(folder);
//            }
//        });


        searchButton.setLocation(0,0);
        //accountButton.setLocation(200,200);
    }
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
//        try {
//            LoggedInState loggedInState = (LoggedInState) evt.getNewValue();
//            if (loggedInState.getUsernameError() != null) {
//                JOptionPane.showMessageDialog(this, LoggedInState.getUsernameError());
//            }
//        }
//        catch(ClassCastException e){
//            ProfileState profilestate = (ProfileState) evt.getNewValue();
//            if (profilestate.getUsernameError() != null){
//                JOptionPane.showMessageDialog(this, profilestate.getUsernameError());
//            }
//
//        }
    }

    private static void deleteFolder(File folder) {
        // Get the list of files and subdirectories in the folder
        File[] files = folder.listFiles();

        if (files != null) {
            // Iterate over each file/directory and delete recursively
            for (File file : files) {
                if (file.isDirectory()) {
                    // Recursive call for subdirectories
                    deleteFolder(file);
                } else {
                    // Delete the file
                    file.delete();
                }
            }
        }

        // Delete the empty folder after deleting its contents
        folder.delete();
    }
}
