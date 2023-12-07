package view;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import entity.CommonRecipe;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.recipePopup.RecipePopupState;
import interface_adapter.recipePopup.RecipePopupViewModel;
import interface_adapter.resultSearch.ResultState;
import interface_adapter.resultSearch.ResultViewModel;
import interface_adapter.saved.SavedState;
import interface_adapter.saved.SavedViewModel;
import interface_adapter.uploadedRecipe.UploadedRecipeState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.List;

public class RecipePopupView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "recipePopup";

    //private final RecipePopupController recipePopupController;
    private final ViewManagerModel viewManagerModel;

    private final RecipePopupViewModel recipePopupViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final ResultViewModel resultViewModel;
    private final SavedViewModel savedViewModel;
    JLabel recName;
    JLabel image;
    JLabel recipeUrl;
    JLabel ingredients;

    private CommonRecipe recipe;
    private String username;

    private List<CommonRecipe> savedList = new ArrayList<>();
    Map<String, String> savedMap = new HashMap<>();


    public RecipePopupView(ViewManagerModel viewManagerModel, RecipePopupViewModel recipePopupViewModel, LoggedInViewModel loggedInViewModel, ResultViewModel resultViewModel, SavedViewModel savedViewModel){
        //this.recipePopupController = recipePopupController;
        this.viewManagerModel = viewManagerModel;
        //NEED TO CHANGE
        this.recipePopupViewModel = recipePopupViewModel;
        this.loggedInViewModel = loggedInViewModel;
        this.resultViewModel = resultViewModel;
        this.savedViewModel = savedViewModel;

        this.recipePopupViewModel.addPropertyChangeListener(this);
        this.savedViewModel.addPropertyChangeListener(this);


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
        back.setBackground(new Color(254,232,210));
        whole.add(back, BorderLayout.PAGE_END);

        back.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(back)){
                            RecipePopupState currentState = recipePopupViewModel.getState();
                            String comingFrom = currentState.getComingFrom();
                            if (Objects.equals(comingFrom, "result")){
                                ResultState resultState = resultViewModel.getState();
                                resultViewModel.setState(resultState);
                                resultViewModel.firePropertyChanged();

                                viewManagerModel.setActiveView(resultViewModel.getViewName());
                                viewManagerModel.firePropertyChanged();
                            }
                            else if (Objects.equals(comingFrom, "loggedin")) {
                                LoggedInState loggedInState = loggedInViewModel.getState();
                                loggedInViewModel.setState(loggedInState);
                                loggedInViewModel.firePropertyChanged();
                                viewManagerModel.setActiveView(loggedInViewModel.getViewName());
                                viewManagerModel.firePropertyChanged();
                            }
                        }
                    }
                }
        );

        JPanel middle = new JPanel();
//        middle.setLayout(new BorderLayout());
        middle.setLayout(new FlowLayout());

        image = new JLabel();
        middle.add(image, BorderLayout.LINE_START);

        JPanel saveMake = new JPanel();
        saveMake.setLayout(new FlowLayout());
        JButton save = new JButton("Save");
        save.setBackground(new Color(254,232,210));
        JButton make = new JButton("Make it");
        make.setBackground(new Color(254,232,210));

        save.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(save)){
                            JFrame saved = new JFrame("Confirmation");
                            saved.setSize(275, 100);
                            JPanel little = new JPanel();
                            little.setLayout(new BorderLayout());
                            JLabel message = new JLabel("Saved!");
                            //message.setAlignmentX(CENTER_ALIGNMENT);
                            little.add(message, BorderLayout.CENTER);
                            saved.add(little);
                            saved.setVisible(true);
                            savedList.add(recipe);
                            savedMap.put(username,recipe.getRecipeName());
                            //BufferedWriter writer;
                            SavedState currentSavedState = savedViewModel.getState();
                            currentSavedState.setRecipe(recipe);
                            currentSavedState.setRecipeName(recipe);
                            currentSavedState.setUsername(username);
                            currentSavedState.setSavedMap(savedMap);
                            currentSavedState.setSavedList(savedList);
                            //currentSavedState.setSavedList(savedList);
                            savedViewModel.setState(currentSavedState);
                            savedViewModel.firePropertyChanged();
                            currentPopupState.setImageUrl(recipe);
                            currentPopupState.setRecipeUrl(recipe);
                            currentPopupState.setComingFrom("loggedin");

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

        JPanel info = new JPanel();
        info.setLayout(new BorderLayout());
        info.add(saveMake, BorderLayout.PAGE_START);

        ingredients = new JLabel();
        ingredients.setFont(new Font("Serif", Font.PLAIN, 14));

        info.add(ingredients, BorderLayout.CENTER);

        middle.add(info, BorderLayout.LINE_END);

        whole.add(middle, BorderLayout.CENTER);

        this.add(whole);

    }

    @Override
    public void actionPerformed(ActionEvent evt) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object newValue = evt.getNewValue();
        if (newValue  instanceof RecipePopupState) {
            RecipePopupState state = (RecipePopupState) evt.getNewValue();
            recName.setText(state.getRecipeLabel());
            ImageIcon saveRecipeImage = new ImageIcon(state.getImageUrl());
            image.setIcon(saveRecipeImage);
            recipeUrl.setText(state.getRecipeUrl());
            ingredients.setText(state.getIngredients());
            this.recipe = state.getRecipe();
            this.username = state.getUsername();




        }






    }
}
