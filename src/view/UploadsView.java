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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UploadsView extends JPanel implements ActionListener, PropertyChangeListener{

    public final String viewName = "Uploaded Recipes";

    private final UploadsViewModel uploadsViewModel;

    private final ProfileViewModel profileViewModel;

    private final ViewManagerModel viewManagerModel;

    private UploadedRecipeViewModel uploadedRecipeViewModel;

    private final UploadingController uploadingController;

    private UploadsState uploadsState;

    private List<String> buttonsList;

    private  List<Map<String, Object>> uploadedRecipeList;

    final JButton back;

    JButton viewButton;

    JButton viewButtonTest;

    public UploadsView(UploadsViewModel uploadsViewModel, ProfileViewModel profileViewModel, ViewManagerModel viewManagerModel, UploadedRecipeViewModel uploadedRecipeViewModel, UploadingController uploadingController) {
        this.uploadsViewModel = uploadsViewModel;
        this.profileViewModel = profileViewModel;
        this.viewManagerModel = viewManagerModel;
        this.uploadedRecipeViewModel = uploadedRecipeViewModel;
        this.uploadingController = uploadingController;
        uploadsViewModel.addPropertyChangeListener(this);
        this.uploadedRecipeList = new ArrayList<>();
        uploadedRecipeViewModel.addPropertyChangeListener(this);
        //viewManagerModel.addPropertyChangeListener(this);

        this.buttonsList = new ArrayList<>();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //DefaultListModel<Object> uploadedRecipeListModel = new DefaultListModel<>();

        //JPanel mainPanel = new JPanel();
        //JScrollPane scrollPane = new JScrollPane(mainPanel);

        //ist<Map<String, Object>> recipesList = uploadingController.uploadedRecipes();
        //create state of uploadsviewmodel getState, then get the recipes from this state and then loop over them

        this.uploadsState = uploadsViewModel.getState();
        //List<Map<String, Object>> recipesList = uploadsState.getUploadedrecipesList();
        JPanel buttons = new JPanel();

// for loop is where uploaded recipes are added from the controller
        if (uploadedRecipeList.size() == 1){
            //viewButton = new JButton(uploadedRecipeList.get(0).get("Name").toString());
            viewButton = new JButton("Hello");
            buttons.add(viewButton);
        }

        //buttons.add(viewButton);
        //uploadedRecipeList.get(0);

        JLabel title = new JLabel(uploadsViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        back = new JButton(uploadsViewModel.BACK_BUTTON_LABEL);
        buttons.add(back);

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


        this.add(title);
        this.add(buttons);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO: implement
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //UploadsState uploadsState = uploadsViewModel.getState();
        List<Map<String, Object>> recipesList = uploadingController.uploadedRecipes();
        this.uploadedRecipeList = recipesList;
        JPanel mainPanel = new JPanel();

        //add if statement to check if button is already in the panel 

        for (Map<String, Object> recipe : uploadedRecipeList){

            String recipeName = (String) recipe.get("Name");
            // Create a button for each recipe and add an action listener

            if (!buttonsList.contains(recipeName)) {
                buttonsList.add(recipeName);
                viewButton = new JButton("View " + recipeName + " Recipe");
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
                                    uploadedRecipeViewModel.firePropertyChanged();
                                    viewManagerModel.setActiveView(uploadedRecipeViewModel.getViewName());
                                    viewManagerModel.firePropertyChanged();

                                }

                            }
                        }
                );
                mainPanel.add(viewButton);
            }
        }
        this.add(mainPanel);
    }
}
