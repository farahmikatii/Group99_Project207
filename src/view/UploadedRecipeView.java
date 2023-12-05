package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.uploadedRecipe.UploadedRecipeState;
import interface_adapter.uploading.UploadingViewModel;
import interface_adapter.uploads.UploadsState;
import interface_adapter.uploads.UploadsViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UploadedRecipeView extends JPanel implements ActionListener, PropertyChangeListener {

    //TODO: change viewName to the actual recipe name
    public final String viewName = "Uploaded Recipe";
    private final UploadingViewModel uploadingViewModel;

    private final UploadsViewModel uploadsViewModel;

    private ViewManagerModel viewManagerModel;

    private UploadedRecipeState uploadedRecipeState;

    /*final JTextArea recipeNameArea;*/

    JLabel recipeNameArea;

    JLabel recipeInstructionsArea;

    JLabel recipeIngredientsArea;

    //final JTextArea recipeInstructionsArea;

    //final JTextArea recipeIngredientsArea;

    final JButton back;

    public UploadedRecipeView(ViewManagerModel viewManagerModel, UploadedRecipeState uploadedRecipeState, UploadingViewModel uploadingViewModel, UploadsViewModel uploadsViewModel) {
        this.uploadingViewModel = uploadingViewModel;
        this.uploadsViewModel = uploadsViewModel;
        this.viewManagerModel = viewManagerModel;
        this.uploadedRecipeState = uploadedRecipeState;
        uploadingViewModel.addPropertyChangeListener(this);
        uploadsViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(uploadedRecipeState.getUploadedRecipeName());
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

/*        recipeNameArea = new JTextArea(5,5);
        recipeNameArea.setText(uploadedRecipeState.getUploadedRecipeName());
        recipeNameArea.setEditable(false);*/

        recipeNameArea = new JLabel(uploadedRecipeState.getUploadedRecipeName());
        recipeIngredientsArea = new JLabel(uploadedRecipeState.getUploadedRecipeIngredients());
        recipeInstructionsArea = new JLabel(uploadedRecipeState.getUploadedRecipeInstructions());

 /*       recipeInstructionsArea = new JTextArea(10,20);
        recipeInstructionsArea.setText(uploadedRecipeState.getUploadedRecipeInstructions());
        recipeInstructionsArea.setEditable(false);*/

/*        recipeIngredientsArea = new JTextArea(10, 10);
        recipeIngredientsArea.setText(uploadedRecipeState.getUploadedRecipeIngredients());
        recipeIngredientsArea.setEditable(false);*/

        if (uploadedRecipeState.getUploadedRecipe() != null) {
            JLabel picLabel = new JLabel(new ImageIcon(uploadedRecipeState.getUploadedRecipeImage()));
            this.add(picLabel);
        }

        //potentially add a scroll pane

        JPanel buttons = new JPanel();
        back = new JButton(uploadingViewModel.BACK_BUTTON_LABEL);
        buttons.add(back);

        back.addActionListener(
                //takes user back to home page
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(back)){
                            UploadsState uploadsState = uploadsViewModel.getState();
                            uploadsViewModel.setState(uploadsState);
                            uploadsViewModel.firePropertyChanged();
                            viewManagerModel.setActiveView(uploadsViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        this.add(back);
        this.add(recipeNameArea);
        this.add(recipeIngredientsArea);
        this.add(recipeInstructionsArea);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO: implement
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //TODO: implement

    }
    public String getViewName() {return viewName;
    }
}
