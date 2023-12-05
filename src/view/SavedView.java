package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.recipePopup.RecipePopupState;
import interface_adapter.saved.SavedState;
import interface_adapter.saved.SavedViewModel;
import interface_adapter.uploadedRecipe.UploadedRecipeState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SavedView extends JPanel implements ActionListener,PropertyChangeListener {
    public final String viewName = "Saved Recipes";

    private final ProfileViewModel profileViewModel;
    private final ViewManagerModel viewManagerModel;

    private final SavedViewModel savedViewModel;

    final JButton back;

    public SavedView(SavedViewModel savedViewModel, ViewManagerModel viewManagerModel, ProfileViewModel profileViewModel) {
        this.profileViewModel = profileViewModel;
        this.savedViewModel = savedViewModel;
        this.viewManagerModel = viewManagerModel;
        this.savedViewModel.addPropertyChangeListener(this);


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
        }
    }
}
