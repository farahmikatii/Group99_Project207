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

        JPanel entire = new JPanel();
        entire.setLayout(new BoxLayout(entire, BoxLayout.PAGE_AXIS));

        title = new JLabel();
        title.setFont(new Font("Serif", Font.PLAIN, 25));
        //title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel titlePanel = new JPanel();
        titlePanel.add(title, BorderLayout.PAGE_START);

        JPanel whole = new JPanel();

        whole.setLayout(new BoxLayout(whole, BoxLayout.Y_AXIS));

        back = new JButton(uploadingViewModel.BACK_BUTTON_LABEL);
        back.setBackground(new Color(254,232,210));
        JPanel backPanel = new JPanel();
        backPanel.add(back, BorderLayout.PAGE_END);


        recipeNameArea = new JLabel(uploadedRecipeState.getUploadedRecipeName());
        recipeIngredientsArea = new JLabel("Ingredients: " + uploadedRecipeState.getUploadedRecipeIngredients());
        recipeInstructionsArea = new JLabel("Instructions: " + uploadedRecipeState.getUploadedRecipeInstructions());


        whole.add(recipeIngredientsArea);
        whole.add(recipeInstructionsArea, BorderLayout.CENTER);


        if (uploadedRecipeState.getUploadedRecipe() != null) {
            JPanel imagePanel = new JPanel();
            image = new JLabel();
            imagePanel.add(image, BorderLayout.LINE_START);
            this.add(imagePanel);
        }


        back.addActionListener(
                //takes user back to home page
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(back)){
                            UploadsState uploadsState = uploadsViewModel.getState();
                            uploadedRecipeState.setUploadedRecipeImage(null);
                            uploadedRecipeViewModel.firePropertyChanged();
                            uploadsViewModel.setState(uploadsState);
                            //uploadsViewModel.firePropertyChanged();
                            viewManagerModel.setActiveView(uploadsViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();


                        }

                    }
                }
        );

        entire.add(titlePanel);
        entire.add(whole);
        entire.add(backPanel);

        this.add(entire);

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
            recipeIngredientsArea.setText("Ingredients: " + newState.getUploadedRecipeIngredients());
            recipeInstructionsArea.setText("Instructions: " + newState.getUploadedRecipeInstructions());

            if (newState.getUploadedRecipeImage() != null){
                ImageIcon recipeImage = new ImageIcon(newState.getUploadedRecipeImage());
                image = new JLabel();
                image.setIcon(recipeImage);
                JPanel imagePanel = new JPanel();
                imagePanel.add(image, BorderLayout.LINE_START);
                this.add(imagePanel);
            }
        }

    }
    public String getViewName() {return viewName;
    }
}
