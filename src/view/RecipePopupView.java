package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.recipePopup.RecipePopupState;
import interface_adapter.recipePopup.RecipePopupViewModel;
import interface_adapter.logged_in.LoggedInViewModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class RecipePopupView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "recipePopup";

    //private final RecipePopupController recipePopupController;
    private final ViewManagerModel viewManagerModel;

    private final RecipePopupState recipePopupState;
    private final RecipePopupViewModel recipePopupViewModel;
    private final LoggedInViewModel loggedInViewModel;
    JLabel recName;
    JLabel image;
    JLabel recipeUrl;


    public RecipePopupView(ViewManagerModel viewManagerModel, RecipePopupState recipePopupState, RecipePopupViewModel recipePopupViewModel, LoggedInViewModel loggedInViewModel){
        //this.recipePopupController = recipePopupController;
        this.viewManagerModel = viewManagerModel;
        //NEED TO CHANGE
        this.recipePopupState = recipePopupState;
        this.recipePopupViewModel = recipePopupViewModel;
        this.loggedInViewModel = loggedInViewModel;

        this.recipePopupViewModel.addPropertyChangeListener(this);



        RecipePopupState currentPopupState = recipePopupViewModel.getState();
        //System.out.println(currentPopupState.getRecipeLabel());

        recipeUrl = new JLabel();
        //JLabel recipeName = new JLabel("hello");

        JPanel whole = new JPanel();
        whole.setLayout(new BorderLayout());

        recName = new JLabel();
        recName.setFont(new Font("Serif", Font.PLAIN, 25));
        whole.add(recName, BorderLayout.PAGE_START);

        JButton back = new JButton("Back");
        whole.add(back, BorderLayout.PAGE_END);

        back.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(back)){
                            LoggedInState loggedInState = loggedInViewModel.getState();
                            loggedInViewModel.setState(loggedInState);
                            loggedInViewModel.firePropertyChanged();
                            viewManagerModel.setActiveView(loggedInViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        JPanel middle = new JPanel();
        middle.setLayout(new BorderLayout());

        image = new JLabel();
        middle.add(image, BorderLayout.LINE_START);

        JPanel saveMake = new JPanel();
        saveMake.setLayout(new FlowLayout());
        JButton save = new JButton("Save");
        JButton make = new JButton("Make it");

        save.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(save)){
                            JFrame saved = new JFrame("Confirmation");
                            saved.setPreferredSize(new Dimension(500, 100));
                            JLabel message = new JLabel("Saved!");
                            saved.add(message);
                            saved.setVisible(true);

//                            saved.setPreferredSize(new Dimension(500, 100));
//                            JLabel message = new JLabel("Recipe has already been saved.");
//                            saved.add(message);
//                            saved.setVisible(true);

                        }
                    }
                }
        );

        make.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(make)){
                            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                                try {
                                    URI uri = new URI(recipeUrl.getText());
                                    Desktop.getDesktop().browse(uri);
                                } catch (IOException | URISyntaxException w) {
                                    w.printStackTrace();
                                }
                            } else {
                                System.out.println("Link cannot be opened.");
                            }

                        }
                    }
                }
        );

        saveMake.add(save);
        saveMake.add(make);
        middle.add(saveMake, BorderLayout.LINE_END);

        whole.add(middle, BorderLayout.CENTER);

        this.add(whole);
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
