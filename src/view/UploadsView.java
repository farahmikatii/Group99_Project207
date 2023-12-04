package view;

import interface_adapter.ViewManagerModel;

import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.uploadedRecipe.UploadedRecipeState;
import interface_adapter.uploadedRecipe.UploadedRecipeViewModel;
import interface_adapter.uploading.UploadingController;
import interface_adapter.uploads.UploadsState;
import interface_adapter.uploads.UploadsViewModel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Map;

public class UploadsView extends JPanel implements ActionListener, PropertyChangeListener{

    public final String viewName = "Uploaded Recipes";

    private final UploadsViewModel uploadsViewModel;

    private final ProfileViewModel profileViewModel;

    private final ViewManagerModel viewManagerModel;

    private UploadedRecipeViewModel uploadedRecipeViewModel;

    //private final UploadingController uploadingController;

    private final UploadsState uploadsState;

    private JList<Object> uploadedRecipeList;

    final JButton back;

    JButton viewButton;

    public UploadsView(UploadsViewModel uploadsViewModel, ProfileViewModel profileViewModel, ViewManagerModel viewManagerModel) {
        this.uploadsViewModel = uploadsViewModel;
        this.profileViewModel = profileViewModel;
        this.viewManagerModel = viewManagerModel;
        //this.uploadingController = uploadingController;
        uploadsViewModel.addPropertyChangeListener(this);
        viewManagerModel.addPropertyChangeListener(this);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //DefaultListModel<Object> uploadedRecipeListModel = new DefaultListModel<>();

        JPanel mainPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(mainPanel);

        //ist<Map<String, Object>> recipesList = uploadingController.uploadedRecipes();
        //create state of uploadsviewmodel getState, then get the recipes from this state and then loop over them

        uploadsState = uploadsViewModel.getState();
        List<Map<String, Object>> recipesList = uploadsState.getUploadedrecipesList();

// for loop is where uploaded recipes are added from the controller

        for (Map<String, Object> recipe : recipesList){

            String recipeName = (String) recipe.get("Name");
            //uploadedRecipeListModel.addElement(recipeName);
            // Create a button for each recipe and add an action listener

            viewButton = new JButton("View " + recipeName);
            viewButton.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (e.getSource().equals(viewButton)) {
                                String recipeName = (String) recipe.get("Name");
                                String recipeIngredients = (String) recipe.get("Ingredients");
                                String recipeInstructions = (String) recipe.get("Instructions");
                                Image recipeImage = (Image) recipe.get("Image");

                                //take to recipe page view

                                UploadedRecipeState currentRecipeState = uploadedRecipeViewModel.getState();
                                currentRecipeState.setUploadedRecipe(recipe);
                                currentRecipeState.setUploadedRecipeName(recipeName);
                                currentRecipeState.setUploadedRecipeIngredients(recipeIngredients);
                                currentRecipeState.setUploadedRecipeInstructions(recipeInstructions);
                                currentRecipeState.setUploadedRecipeImage(recipeImage);

                                uploadedRecipeViewModel.setState(currentRecipeState);
                                viewManagerModel.setActiveView(uploadedRecipeViewModel.getViewName());
                                viewManagerModel.firePropertyChanged();

                            }

                        }
                    }
            );
            mainPanel.add(viewButton);
        }


        JLabel title = new JLabel(uploadsViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        back = new JButton(uploadsViewModel.BACK_BUTTON_LABEL);
        buttons.add(back);

        back.addActionListener(this);

        this.add(title);
        this.add(buttons);
        this.add(scrollPane);
        //this.add(mainPanel);

        back.addActionListener(
                // takes back to profile page
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(back)){
                            ProfileState profileState = profileViewModel.getState();
                            profileViewModel.setState(profileState);
                            profileViewModel.firePropertyChanged();
                            viewManagerModel.setActiveView(profileViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO: implement
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //TODO: implement
    }
}
