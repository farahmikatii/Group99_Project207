package view;

import entity.CommonRecipe;
import interface_adapter.ViewManagerModel;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.recipePopup.RecipePopupState;
import interface_adapter.saved.SavedState;
import interface_adapter.saved.SavedViewModel;
import interface_adapter.saved.SavedController;
import interface_adapter.uploadedRecipe.UploadedRecipeState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Map;

public class SavedView extends JPanel implements ActionListener,PropertyChangeListener {
    public final String viewName = "Saved Recipes";

    private final ProfileViewModel profileViewModel;
    private final ViewManagerModel viewManagerModel;

    private final SavedViewModel savedViewModel;

    private final SavedController savedController;

    private List<Map<String, CommonRecipe>> savedRecipeList;

    private List<String> buttonsList;

    final JButton back;

    JButton viewButton;
    private String username;

    public SavedView(SavedViewModel savedViewModel, ViewManagerModel viewManagerModel, ProfileViewModel profileViewModel,
                     SavedController savedController) {
        this.profileViewModel = profileViewModel;
        this.savedViewModel = savedViewModel;
        this.viewManagerModel = viewManagerModel;
        this.savedController = savedController;
        savedViewModel.addPropertyChangeListener(this);


        JLabel title = new JLabel(savedViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        back = new JButton(savedViewModel.BACK_BUTTON_LABEL);
        buttons.add(back);

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

      this.add(title);
      this.add(buttons);



      //TODO: show saved recipes here (in same presentation as results)
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO: implement

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object newValue = evt.getNewValue();
        if (newValue  instanceof SavedState) {
            SavedState state = (SavedState) evt.getNewValue();
            System.out.println(state.getRecipeName());
            System.out.println(state.getUsername());
            this.username = state.getUsername();
            System.out.println(username);

        }
//        List<Map<String, CommonRecipe>> savedList = savedController.savedRecipes();
//        this.savedRecipeList = savedList;
//        JPanel mainPanel = new JPanel();
//
//        for (Map<String, CommonRecipe> recipe : savedRecipeList){
//            String recipeName = (String) recipe.get();

//            if (!buttonsList.contains(recipeName)){
//                buttonsList.add(recipeName);
//                viewButton = new JButton("View " + recipeName + " Recipe");
//                viewButton.addActionListener(
//                        new ActionListener() {
//                            @Override
//                            public void actionPerformed(ActionEvent e) {
//                                if (e.getSource().equals(viewButton)){
////                                    String recipeName = (String) recipe.get("Name");
////                                    String recipeURL = (String) recipe.get("RecipeURL");
////                                    String recipeImageURL = (String) recipe.get("RecipeImageURL");
////
////                                    SavedState currentSavedState = SavedViewModel.getState();
////                                    currentSavedState.setRecipe(recipe);
////                                    currentSavedState.setRecipeName(recipe);
////                                    currentSavedState.setRecipeURL(recipeURL);
////                                    currentSavedState.setRecipeImageURL(recipeImageURL);
////
////                                    SavedViewModel.setState(currentSavedState);
////                                    SavedViewModel.firePropertyChanged();
////                                    viewManagerModel.setActiveView(SavedViewModel.getViewName());
////                                    viewManagerModel.firePropertyChanged();
//                                }
//                            }
//                        }
//                );
//                mainPanel.add(viewButton);
//            }
//        }
//        this.add(mainPanel);
    }
}
