package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.recipePopup.RecipePopupController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RecipePopupView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "recipePopup";

    //private final RecipePopupController recipePopupController;
    private final ViewManagerModel viewManagerModel;


    public RecipePopupView(ViewManagerModel viewManagerModel){
        //this.recipePopupController = recipePopupController;
        this.viewManagerModel = viewManagerModel;

        JLabel title = new JLabel("Recipe Flow");
        this.add(title);
    }






    @Override
    public void actionPerformed(ActionEvent evt) {
        //RecipePopupState state = (RecipePopupState) evt.getNewValue();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
