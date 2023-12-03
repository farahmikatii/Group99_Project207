package view;

import entity.CommonRecipe;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.recipePopup.RecipePopupState;
import interface_adapter.recipePopup.RecipePopupViewModel;
import interface_adapter.resultSearch.ResultState;
import interface_adapter.resultSearch.ResultViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class ResultsView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Filtered Recipes";
    private final ResultViewModel resultViewModel;
    private final RecipePopupViewModel recipePopupViewModel;
    private final LoggedInViewModel loggedInViewModel;

    private final ViewManagerModel viewManagerModel;

    final JButton back;
    JButton recipeImage;

    public ResultsView(ResultViewModel resultViewModel, RecipePopupViewModel recipePopupViewModel, ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel){
        this.resultViewModel = resultViewModel;
        this.viewManagerModel = viewManagerModel;
        this.recipePopupViewModel = recipePopupViewModel;
        this.loggedInViewModel = loggedInViewModel;

        resultViewModel.addPropertyChangeListener(this);
        viewManagerModel.addPropertyChangeListener(this);
        recipePopupViewModel.addPropertyChangeListener(this);
        loggedInViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Recipe Flow");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        back = new JButton(resultViewModel.BACK_BUTTON_LABEL);
        buttons.add(back);

        JPanel recipes = new JPanel();
        JScrollPane scroll = new JScrollPane(recipes);

        recipes.setLayout(new GridLayout(0,4,5,5));

        ResultState currentState = resultViewModel.getState();
        List<CommonRecipe> recipesList = currentState.getRecipesList();
        for (CommonRecipe recipe : recipesList){
            ImageIcon saveRecipeImage = new ImageIcon(recipe.getImage());
            recipeImage = new JButton(recipe.getRecipeName(), saveRecipeImage);
            //setting position of label of recipe
            recipeImage.setVerticalTextPosition(SwingConstants.TOP);
            recipeImage.setHorizontalTextPosition(SwingConstants.CENTER);
            recipeImage.addActionListener(
                    // This creates an anonymous subclass of ActionListener and instantiates it.
                    new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource() instanceof JButton sourceButton) {
                            if (evt.getSource().equals(sourceButton)) {
                                RecipePopupState currentPopupState = recipePopupViewModel.getState();
                                System.out.println(currentPopupState);

                                recipePopupViewModel.setState(currentPopupState);
                                System.out.println(recipePopupViewModel.getState());

                                recipePopupViewModel.firePropertyChanged();

                                viewManagerModel.setActiveView(recipePopupViewModel.getViewName());
                                System.out.println(viewManagerModel.getActiveView());
                                viewManagerModel.firePropertyChanged();

                            }


                        }
                    }
                }
            );
            //recipeImage.setPreferredSize(new Dimension(10, 10));
            recipes.add(recipeImage);
        }

        back.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(back)) {
                            LoggedInState currentLoggedInState = loggedInViewModel.getState();
                            loggedInViewModel.setState(currentLoggedInState);
                            loggedInViewModel.firePropertyChanged();
                            viewManagerModel.setActiveView(loggedInViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(buttons);
        this.add(scroll);
    }

    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        try {
            ResultState state = (ResultState) evt.getNewValue();
            System.out.println(state.getRecipesList());
        }
        catch(ClassCastException e){
            LoggedInState state = (LoggedInState) evt.getNewValue();
            if (state.getUsername() != null){
                JOptionPane.showMessageDialog(this, state.getUsernameError());
            }
        }
    }
    //this will be the page that the user is taken to when they click search on the SearchView
    //this will present the filtered results of their search and have a button to go back to the
    //searchview so they can search again
}
