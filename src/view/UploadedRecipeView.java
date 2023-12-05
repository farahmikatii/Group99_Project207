package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.uploadedRecipe.UploadedRecipeState;
import interface_adapter.uploadedRecipe.UploadedRecipeViewModel;
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

    private final UploadedRecipeViewModel uploadedRecipeViewModel;

    JLabel recipeNameArea;

    final JLabel recipeInstructionsArea;

    final JLabel recipeIngredientsArea;

    final JLabel title;

    JLabel image;

    final JButton back;

    public UploadedRecipeView(ViewManagerModel viewManagerModel, UploadedRecipeState uploadedRecipeState, UploadingViewModel uploadingViewModel, UploadsViewModel uploadsViewModel, UploadedRecipeViewModel uploadedRecipeViewModel) {
        this.uploadingViewModel = uploadingViewModel;
        this.uploadsViewModel = uploadsViewModel;
        this.viewManagerModel = viewManagerModel;
        this.uploadedRecipeState = uploadedRecipeState;
        this.uploadedRecipeViewModel = uploadedRecipeViewModel;
        uploadingViewModel.addPropertyChangeListener(this);
        uploadsViewModel.addPropertyChangeListener(this);
        uploadedRecipeViewModel.addPropertyChangeListener(this);

        title = new JLabel();
        title.setFont(new Font("Serif", Font.PLAIN, 25));
        //title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel whole = new JPanel();
        //whole.setLayout(new BorderLayout());

        JPanel middle = new JPanel();
        //middle.setLayout(new BorderLayout());

        recipeNameArea = new JLabel(uploadedRecipeState.getUploadedRecipeName());
        recipeIngredientsArea = new JLabel("Ingredients: " + uploadedRecipeState.getUploadedRecipeIngredients());
        recipeInstructionsArea = new JLabel("Instructions: " + uploadedRecipeState.getUploadedRecipeInstructions());


        //whole.add(recipeNameArea);
        middle.add(recipeIngredientsArea, BorderLayout.CENTER);
        middle.add(recipeInstructionsArea, BorderLayout.CENTER);


        if (uploadedRecipeState.getUploadedRecipe() != null) {
            image = new JLabel();
            this.add(image);
            middle.add(image, BorderLayout.LINE_START);

        }
        //potentially add a scroll pane

        back = new JButton(uploadingViewModel.BACK_BUTTON_LABEL);
        whole.add(back, BorderLayout.PAGE_END);

        whole.add(title, BorderLayout.PAGE_START);

        back.addActionListener(
                //takes user back to home page
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(back)){
                            UploadsState uploadsState = uploadsViewModel.getState();
                            uploadsViewModel.setState(uploadsState);
                            //uploadsViewModel.firePropertyChanged();
                            viewManagerModel.setActiveView(uploadsViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        whole.add(middle, BorderLayout.CENTER);
        this.add(whole);
/*
        this.add(recipeNameArea);
        this.add(recipeIngredientsArea);
        this.add(recipeInstructionsArea);
*/
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO: implement
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object newValue = evt.getNewValue();
        if (newValue  instanceof  UploadedRecipeState){
            UploadedRecipeState newState = (UploadedRecipeState) newValue;
            System.out.println(newState.getUploadedRecipeName());
            title.setText(newState.getUploadedRecipeName());
            //recipeNameArea.setText(newState.getUploadedRecipeName());
           // ImageIcon recipeImage = new ImageIcon(newState.getUploadedRecipeImage());
            recipeIngredientsArea.setText("Ingredients: " + newState.getUploadedRecipeIngredients());
            recipeInstructionsArea.setText("Instructions: " + newState.getUploadedRecipeInstructions());
            //image.setIcon(recipeImage);
            //getting a null point exception error
        }

    }
    public String getViewName() {return viewName;
    }
}
