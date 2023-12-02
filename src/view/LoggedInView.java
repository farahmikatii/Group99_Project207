package view;

import data_access.CommonRecipeDataAccessObject;
import entity.CommonRecipe;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.recipePopup.RecipePopupState;
import interface_adapter.recipePopup.RecipePopupViewModel;
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
import java.util.List;
import java.util.Objects;

public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;

    private final ViewManagerModel viewManagerModel;

    private final ProfileViewModel profileViewModel;

    private final RecipePopupViewModel recipePopupViewModel;


    JLabel username;


    final JButton logOut;
    final JButton search;
    final JButton account;
    JButton recipeImage;
    String file;
    private int previousScrollValue = 0;
    private int prevHorizontalValue = 0;
    public LoggedInView(LoggedInViewModel loggedInViewModel, ViewManagerModel viewManagerModel, ProfileViewModel profileViewModel, RecipePopupViewModel recipePopupViewModel) throws Exception {
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
        this.profileViewModel = profileViewModel;


        this.recipePopupViewModel = recipePopupViewModel;

        loggedInViewModel.addPropertyChangeListener(this);
        profileViewModel.addPropertyChangeListener(this);
        viewManagerModel.addPropertyChangeListener(this);





        JLabel title = new JLabel("Recipe Flow");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        //String jsonFile = "/Users/duahussain/IdeaProjects/Group99_Project207/response_output.csv";

        //String jsonFile = "/Users/farahmikati/IdeaProjects/Group99_Project207/response_output.json";
        //String jsonFile = "/Users/duahussain/IdeaProjects/Group99_Project207/response_output.csv";
        //String jsonFile = "/Users/farahmikati/IdeaProjects/Group99_Project207/response_output.json";
        String jsonFile = "./response_output.json";
        file = CommonRecipeDataAccessObject.readFileAsString(jsonFile);
        final CommonRecipeDataAccessObject[] commonRecipeDAO = {new CommonRecipeDataAccessObject(file)}; // replace jsonFile with the actual JSON file content or path

        // Call returnRecipeList method
        final List<CommonRecipe>[] recipesList = new List[]{commonRecipeDAO[0].returnRecipeList(1)};



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



        //buttons.add(logOut);
        searchButton.add(search);
        searchButton.add(account);
        //divider.add(logOut);
        //recipes.add(recipeImage);

        for (CommonRecipe recipe : recipesList[0]){
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

                                    RecipePopupState currentPopupState = recipePopupViewModel.getState();
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


                                System.out.println(nextUrl);

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
                                        String filePath = "C:/Users/rahman/Desktop/Year 2/CSC207 - Software Design/Weekly Activities/Group99_Project207/response_output.json";


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

                                CommonRecipeDataAccessObject commonRecipeDAO = new CommonRecipeDataAccessObject(file); // replace jsonFile with the actual JSON file content or path

                                // Call returnRecipeList method
                                recipesList[0] = commonRecipeDAO.returnRecipeList(1);

                                for (CommonRecipe recipe : recipesList[0]){
                                    ImageIcon saveRecipeImage = new ImageIcon(recipe.getImage());
                                    recipeImage = new JButton(recipe.getRecipeName(), saveRecipeImage);
                                    //setting position of label of recipe
                                    recipeImage.setVerticalTextPosition(SwingConstants.TOP);
                                    recipeImage.setHorizontalTextPosition(SwingConstants.CENTER);
                                    recipeImage.addActionListener(
                                            // This creates an anonymous subclass of ActionListener and instantiates it.
                                            new ActionListener() {
                                                public void actionPerformed(ActionEvent evt) {

                                                    if (evt.getSource().equals(recipeImage)) {

                                                        RecipePopupState currentPopupState = recipePopupViewModel.getState();
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
                    public void actionPerformed(ActionEvent e) {

                    }
                }
        );
        search.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

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
}
