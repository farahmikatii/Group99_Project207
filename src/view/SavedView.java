package view;

import data_access.CommonRecipeDataAccessObject;
import entity.CommonRecipe;
import interface_adapter.ViewManagerModel;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.recipePopup.RecipePopupState;
import interface_adapter.recipePopup.RecipePopupViewModel;
import interface_adapter.saved.SavedState;
import interface_adapter.saved.SavedViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SavedView extends JPanel implements ActionListener,PropertyChangeListener {
    public final String viewName = "Saved Recipes";

    private final ProfileViewModel profileViewModel;
    private final ViewManagerModel viewManagerModel;

    private final SavedViewModel savedViewModel;
    private final RecipePopupViewModel recipePopupViewModel;

    //private final SavedController savedController;

    private Map<String, CommonRecipe> savedRecipeList;

    private List<String> buttonsList;

    final JButton back;

    JButton viewButton;
    private String username;
    String file;
    JButton recipeImage;
    private Map<String, String> savedMap = new HashMap<>();
    private List<CommonRecipe> savedList = new ArrayList<>();
    JPanel recipes = new JPanel();
    JScrollPane scroll = new JScrollPane(recipes);

    public SavedView(SavedViewModel savedViewModel, ViewManagerModel viewManagerModel, ProfileViewModel profileViewModel, RecipePopupViewModel recipePopupViewModel) throws Exception {
        this.profileViewModel = profileViewModel;
        this.savedViewModel = savedViewModel;
        this.viewManagerModel = viewManagerModel;
        this.recipePopupViewModel = recipePopupViewModel;
        //this.savedController = savedController;
        savedViewModel.addPropertyChangeListener(this);

        String jsonFile = "./response_output.json";
        file = CommonRecipeDataAccessObject.readFileAsString(jsonFile);
        CommonRecipeDataAccessObject commonRecipeDAO = new CommonRecipeDataAccessObject(file);


        JLabel title = new JLabel(savedViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        JPanel recipes = new JPanel();
        JScrollPane scroll = new JScrollPane(recipes);
        this.buttonsList = new ArrayList<>();
        back = new JButton(savedViewModel.BACK_BUTTON_LABEL);
        buttons.add(back);
        recipes.setLayout(new GridLayout(0,4,5,5));

        back.addActionListener(
                // to return to the user's profile page
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(back)){
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
      this.add(buttons);
      this.add(recipes);



      //TODO: show saved recipes here (in same presentation as results)
    }
    public static String cutName(String name) {
        //Checking if name is longer than 45 chars
        if (name.length() > 42) {
            return name.substring(0, 42) + "...";
        } else {
            return name;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO: implement

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object newValue = evt.getNewValue();
        if (newValue  instanceof SavedState) {

//            JPanel recipes = new JPanel();
//            JScrollPane scroll = new JScrollPane(recipes);
            String jsonFile = "./response_output.json";
            try {
                file = CommonRecipeDataAccessObject.readFileAsString(jsonFile);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            CommonRecipeDataAccessObject commonRecipeDAO = new CommonRecipeDataAccessObject(file);

            SavedState state = (SavedState) evt.getNewValue();

            this.username = state.getUsername();
            this.savedMap = state.getSavedMap();
            this.savedList = state.getSavedList();
            for (String username : savedMap.keySet()) {

                String recipeName = savedMap.get(username);
                CommonRecipe recipe = commonRecipeDAO.findRecipe(recipeName, savedList);
                if (!buttonsList.contains(recipeName)) {
                    buttonsList.add(recipeName);
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
                                            RecipePopupState currentPopupState = SavedView.this.recipePopupViewModel.getState();
                                            currentPopupState.setRecipe(recipe);
                                            currentPopupState.setRecipeLabel(recipe);
                                            currentPopupState.setImageUrl(recipe);
                                            currentPopupState.setRecipeUrl(recipe);
                                            currentPopupState.setIngredients(recipe);
                                            currentPopupState.setComingFrom("loggedin");


                                            System.out.println(currentPopupState);

                                            SavedView.this.recipePopupViewModel.setState(currentPopupState);
                                            System.out.println(SavedView.this.recipePopupViewModel.getState());

                                            SavedView.this.recipePopupViewModel.firePropertyChanged();

                                            viewManagerModel.setActiveView(SavedView.this.recipePopupViewModel.getViewName());
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
            this.add(scroll);

        }

    }
}
