package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.recipePopup.RecipePopupState;
import interface_adapter.recipePopup.RecipePopupViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RecipePopupView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "recipePopup";

    //private final RecipePopupController recipePopupController;
    private final ViewManagerModel viewManagerModel;

    private final RecipePopupState recipePopupState;
    private final RecipePopupViewModel recipePopupViewModel;
    JLabel recName;
    JLabel image;
    JLabel recipeUrl;


    public RecipePopupView(ViewManagerModel viewManagerModel, RecipePopupState recipePopupState, RecipePopupViewModel recipePopupViewModel){
        //this.recipePopupController = recipePopupController;
        this.viewManagerModel = viewManagerModel;
        //NEED TO CHANGE
        this.recipePopupState = recipePopupState;
        this.recipePopupViewModel = recipePopupViewModel;

        this.recipePopupViewModel.addPropertyChangeListener(this);



        RecipePopupState currentPopupState = recipePopupViewModel.getState();
        //System.out.println(currentPopupState.getRecipeLabel());
        recName = new JLabel();
        image = new JLabel();
        recipeUrl = new JLabel();
        //JLabel recipeName = new JLabel("hello");

        //ImageIcon saveRecipeImage = new ImageIcon(currentPopupState.getImageUrl());
        //JLabel image = new JLabel(saveRecipeImage);
        JLabel title = new JLabel("Recipe Flow");
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS
        ));

        this.add(title);
        //this.add(recipeName);
        this.add(recName);
        this.add(image);
        this.add(recipeUrl);

    }

    @Override
    public void actionPerformed(ActionEvent evt) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        RecipePopupState state = (RecipePopupState) evt.getNewValue();
        recName.setText(state.getRecipeLabel());
        ImageIcon saveRecipeImage = new ImageIcon(state.getImageUrl());
        image.setIcon(saveRecipeImage);
        recipeUrl.setText(state.getRecipeUrl());


    }
}
