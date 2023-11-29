package view;

import interface_adapter.uploading.UploadingController;
import interface_adapter.uploading.UploadingViewModel;

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

    final JTextArea recipeNameArea;

    final JTextArea recipeInstructionsArea;

    final JTextArea recipeIngredientsArea;

    final JButton back;

    public UploadedRecipeView(UploadingViewModel uploadingViewModel, String recipeName, String recipeIngredients, String recipeInstructions, Image recipeImage) {
        this.uploadingViewModel = uploadingViewModel;
        uploadingViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Uploaded Recipe");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        recipeNameArea = new JTextArea(5,5);
        recipeNameArea.setText(recipeName);
        recipeNameArea.setEditable(false);

        recipeInstructionsArea = new JTextArea(10,20);
        recipeInstructionsArea.setText(recipeInstructions);
        recipeInstructionsArea.setEditable(false);

        recipeIngredientsArea = new JTextArea(10, 10);
        recipeIngredientsArea.setText(recipeIngredients);
        recipeIngredientsArea.setEditable(false);

        JLabel picLabel = new JLabel(new ImageIcon(recipeImage));
        add(picLabel);

        //potentially add a scroll pane

        JPanel buttons = new JPanel();
        back = new JButton(uploadingViewModel.BACK_BUTTON_LABEL);
        buttons.add(back);

        back.addActionListener(
                //takes user back to home page
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //TODO: implement
                    }
                }
        );

        this.add(back);

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
