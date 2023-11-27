package view;

import data_access.CommonRecipeDataAccessObject;
import entity.CommonRecipe;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;

    private final ViewManagerModel viewManagerModel;

    private final ProfileViewModel profileViewModel;

    JLabel username;

    final JButton logOut;
    final JButton search;
    final JButton account;
    JButton recipeImage;
    public LoggedInView(LoggedInViewModel loggedInViewModel, ViewManagerModel viewManagerModel, ProfileViewModel profileViewModel) throws Exception {
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
        this.profileViewModel = profileViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Recipe Flow");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        //String jsonFile = "/Users/duahussain/IdeaProjects/Group99_Project207/response_output.csv";
        String jsonFile = "/Users/farahmikati/IdeaProjects/Group99_Project207/response_output.json";
        String file = CommonRecipeDataAccessObject.readFileAsString(jsonFile);
        CommonRecipeDataAccessObject commonRecipeDAO = new CommonRecipeDataAccessObject(file); // replace jsonFile with the actual JSON file content or path

        // Call returnRecipeList method
        List<CommonRecipe> recipesList = commonRecipeDAO.returnRecipeList();



        //JLabel usernameInfo = new JLabel("Currently Logged in: ");
        //username = new JLabel();

        //JPanel buttons = new JPanel();
        JPanel searchButton = new JPanel();
        JPanel accountButton = new JPanel();
        JPanel recipes = new JPanel();
        logOut = new JButton(loggedInViewModel.LOGOUT_BUTTON_LABEL);
        search = new JButton(loggedInViewModel.SEARCH_BUTTON_LABEL);
        account = new JButton(loggedInViewModel.ACCOUNT_BUTTON_LABEL);
        //recipesList.get(0).getImage()
        //ImageIcon saveRecipeImage = new ImageIcon(recipesList.get(0).getImage());
        recipeImage = new JButton(recipesList.get(0).getRecipeName());



        //Panel topRightButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchButton.setLayout(null);
        accountButton.setLayout(null);
        recipes.setLayout(new GridLayout(0,2));



        //buttons.add(logOut);
        searchButton.add(search);
        searchButton.add(account);
        recipes.add(recipeImage);
        //accountButton.add(account);
        accountButton.setLocation(0,0);
        //buttons.add(account);
        search.setBounds(500, 10, 100, 30);
        account.setBounds(10, 10, 100, 30);
        //account.setBounds(10, -20, 100, 30);

        logOut.addActionListener(this);
        search.addActionListener(this);
        account.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(account)){
                            ProfileState currentState = profileViewModel.getState();
                            profileViewModel.setState(currentState);
                            profileViewModel.firePropertyChanged();
                            viewManagerModel.setActiveView(profileViewModel.getViewName());
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
        this.add(recipes);

        searchButton.setLocation(0,0);
        //accountButton.setLocation(200,200);
    }
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoggedInState state = (LoggedInState) evt.getNewValue();
        //username.setText(state.getUsername());
    }
}
