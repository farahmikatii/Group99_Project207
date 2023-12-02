package view;

import interface_adapter.ViewManagerModel;

import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.uploadedRecipe.UploadedRecipeState;
import interface_adapter.uploadedRecipe.UploadedRecipeViewModel;
import interface_adapter.uploading.UploadingController;
import interface_adapter.uploads.UploadsViewModel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

public class UploadsView extends JPanel implements ActionListener, PropertyChangeListener{

    public final String viewName = "Uploaded Recipes";

    private final UploadsViewModel uploadsViewModel;

    private final ProfileViewModel profileViewModel;

    private final ViewManagerModel viewManagerModel;

    private UploadedRecipeViewModel uploadedRecipeViewModel;

    private final UploadingController uploadingController;

    private JList<Object> uploadedRecipeList;

    final JButton back;

    public UploadsView(UploadsViewModel uploadsViewModel, ProfileViewModel profileViewModel, ViewManagerModel viewManagerModel, UploadingController uploadingController) {
        this.uploadsViewModel = uploadsViewModel;
        this.profileViewModel = profileViewModel;
        this.viewManagerModel = viewManagerModel;
        this.uploadingController = uploadingController;
        this.uploadsViewModel.addPropertyChangeListener(this);

       setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        DefaultListModel<Object> uploadedRecipeListModel = new DefaultListModel<>();

        JPanel mainPanel = new JPanel();

// for loop is where uploaded recipes are added from the controller

        for (Map<String, Object> recipesList : uploadingController.uploadedRecipes()){
            System.out.println("its working :3");
            String recipeName = (String) recipesList.get("Name");
            uploadedRecipeListModel.addElement(recipeName);
            // Create a button for each recipe and add an action listener

            JButton viewButton = new JButton("View " + recipeName);
            viewButton.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String recipeIngredients = (String) recipesList.get("Ingredients");
                            String recipeInstructions = (String) recipesList.get("Instructions");
                            Image recipeImage = (Image) recipesList.get("Image");

                            //take to recipe page view

                            uploadingController.executeRecipeView(
                                    recipeName,
                                    recipeIngredients,
                                    recipeInstructions,
                                    recipeImage
                            );

                        }
                    }
            );
            mainPanel.add(viewButton);
        }

        uploadedRecipeList = new JList<>(uploadedRecipeListModel);

        uploadedRecipeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(uploadedRecipeList);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);

        JLabel title = new JLabel(uploadsViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        back = new JButton(uploadsViewModel.BACK_BUTTON_LABEL);
        buttons.add(back);

        back.addActionListener(this);

        this.add(title);
        this.add(buttons);

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
