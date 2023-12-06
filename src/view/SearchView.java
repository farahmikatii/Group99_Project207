package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;
import interface_adapter.search.SearchController;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SearchView extends JPanel implements ActionListener, PropertyChangeListener {
    //need to have text box at the top that allows users to type in what they want
    //need to have titles for each type of filters and a title called filters
    //underneath each title need to have a list of buttons they can click
    public final String viewName = "search";

    private SearchViewModel searchViewModel;

    private ViewManagerModel viewManagerModel;

    private final LoggedInViewModel loggedInViewModel;

    final JTextField searchInputField = new JTextField(15);

    JLabel filters;

    JButton search;


    JButton back;

    private SearchController searchController;

    public SearchView(SearchController searchController,SearchViewModel searchViewModel, ViewManagerModel viewManagerModel,
                      LoggedInViewModel loggedInViewModel) throws Exception {
        this.searchViewModel = searchViewModel;
        this.searchController = searchController;
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.searchViewModel.addPropertyChangeListener(this);

        ImageIcon titleImage = new ImageIcon("./src/app_pictures/title_logo.png");
        JLabel title = new JLabel(titleImage);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel whole = new JPanel();
        whole.setLayout(new BoxLayout(whole, BoxLayout.Y_AXIS));
        whole.setSize(new Dimension(300, 500));

        JLabel searchTitle = new JLabel("Search");
        searchTitle.setFont(new Font("Serif", Font.PLAIN, 25));

        LabelTextPanel searchInfo = new LabelTextPanel(
                new JLabel("Search Here"), searchInputField);

        JPanel buttons = new JPanel();
        back = new JButton(SearchViewModel.BACK_BUTTON_LABEL);
        back.setBackground(new Color(254,232,210));
        buttons.add(back);
        search = new JButton(SearchViewModel.SEARCH_BUTTON_LABEL);
        search.setBackground(new Color(254,232,210));
        buttons.add(search);

        JPanel dropDown = new JPanel();
        JLabel mealType = new JLabel("Select a meal type");
        mealType.setAlignmentX(Component.CENTER_ALIGNMENT);
        dropDown.add(mealType);

        String[] choices = {"", "breakfast", "brunch", "lunch/dinner", "snack",
                "teatime"};

        final JComboBox<String> mealTypeOptions = new JComboBox<>(choices);
        mealTypeOptions.setMaximumSize(mealTypeOptions.getPreferredSize());
        mealTypeOptions.setAlignmentX(Component.CENTER_ALIGNMENT);
        dropDown.add(mealTypeOptions);

        JPanel dropDown2 = new JPanel();
        JLabel dishType = new JLabel("Select a dish type");
        dishType.setAlignmentX(Component.CENTER_ALIGNMENT);
        dropDown2.add(dishType);

        String[] dishes = {"", "alcohol cocktail", "biscuits and cookies", "bread", "cereals", "condiments and sauces",
                "desserts", "drinks", "egg", "ice cream and custard", "main course", "pancake", "pasta", "pastry",
                "pies and tarts", "pizza", "preps", "preserve", "salad", "sandwiches", "seafood", "side dish", "soup",
                "special occasions", "starter", "sweets"};

        final JComboBox<String> dishTypeOptions = new JComboBox<>(dishes);
        dishTypeOptions.setMaximumSize(dishTypeOptions.getPreferredSize());
        dishTypeOptions.setAlignmentX(Component.CENTER_ALIGNMENT);
        dropDown2.add(dishTypeOptions);

        JPanel dropDown3 = new JPanel();
        JLabel cuisineType = new JLabel("Select a cuisine type");
        cuisineType.setAlignmentX(Component.CENTER_ALIGNMENT);
        dropDown3.add(cuisineType);

        String[] cuisines = {"", "american", "asian", "british", "caribbean", "central europe", "chinese", "eastern europe",
                "french", "greek", "indian", "italian", "japanese", "korean", "kosher", "mediterranean", "mexican",
                "middle eastern", "nordic", "south american", "south east asian", "world"};

        final JComboBox<String> cuisineTypeOptions = new JComboBox<>(cuisines);
        cuisineTypeOptions.setMaximumSize(cuisineTypeOptions.getPreferredSize());
        cuisineTypeOptions.setAlignmentX(Component.CENTER_ALIGNMENT);
        dropDown3.add(cuisineTypeOptions);

        JPanel dropDown4 = new JPanel();
        JLabel dietLabels = new JLabel("Select a diet restriction");
        dietLabels.setAlignmentX(Component.CENTER_ALIGNMENT);
        dropDown4.add(dietLabels);

        String[] diets = {"", "balanced", "high-fiber", "high-protein", "low-carb", "low-fat", "low-sodium"};

        final JComboBox<String> dietLabelOptions = new JComboBox<>(diets);
        dietLabelOptions.setMaximumSize(dietLabelOptions.getPreferredSize());
        dietLabelOptions.setAlignmentX(Component.CENTER_ALIGNMENT);
        dropDown4.add(dietLabelOptions);

        JPanel dropDown5 = new JPanel();
        JLabel healthLabels = new JLabel("Select a health restriction");
        healthLabels.setAlignmentX(Component.CENTER_ALIGNMENT);
        dropDown5.add(healthLabels);

        String[] healths = {"", "alcohol-free", "crustacean-free", "dairy-free", "egg-free", "gluten-free", "keto-friendly",
                "kosher", "peanut-free", "sesame-free", "shellfish-free", "tree-nut-free", "vegan"};

        final JComboBox<String> healthLabelOptions = new JComboBox<>(healths);
        healthLabelOptions.setMaximumSize(healthLabelOptions.getPreferredSize());
        healthLabelOptions.setAlignmentX(Component.CENTER_ALIGNMENT);
        dropDown5.add(healthLabelOptions);

        back.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(back)) {
                            LoggedInState currentLoginState = loggedInViewModel.getState();
                            System.out.println(currentLoginState);
                            loggedInViewModel.setState(currentLoginState);
                            System.out.println(loggedInViewModel.getState());
                            loggedInViewModel.firePropertyChanged();
                            viewManagerModel.setActiveView(loggedInViewModel
                                    .getViewName());
                            System.out.println(viewManagerModel.getActiveView());
                            viewManagerModel.firePropertyChanged();

                        }
                    }
                }
        );


        searchInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SearchState currentState = searchViewModel.getState();
                        String text = searchInputField.getText() + e.getKeyChar();
                        currentState.setQuery(text);
                        //currentState.setUsername(text); change to saving this to search within the api in SearchState
                        searchViewModel.setState(currentState);
                        //like signup
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        mealTypeOptions.addActionListener(
                // Creates an anonymous subclass of ActionListener and instantiates it
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        SearchState currentState = searchViewModel.getState();
                        JComboBox<String> meals = (JComboBox) evt.getSource();
                        String mealSelected = (String) meals.getSelectedItem();
                        currentState.setMealType(mealSelected);
                    }
                }
        );

        dishTypeOptions.addActionListener(
                // Creates an anonymous subclass of ActionListener and instantiates it
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        SearchState currentState = searchViewModel.getState();
                        JComboBox<String> cb = (JComboBox) evt.getSource();
                        String dishSelected = (String) cb.getSelectedItem();
                        currentState.setDishType(dishSelected);
                    }
                }
        );

        cuisineTypeOptions.addActionListener(
                // Creates an anonymous subclass of ActionListener and instantiates it
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        SearchState currentState = searchViewModel.getState();
                        JComboBox<String> cuisines = (JComboBox) evt.getSource();
                        String cuisineSelected = (String) cuisines.getSelectedItem();
                        currentState.setCuisine(cuisineSelected);
                    }
                }
        );

        dietLabelOptions.addActionListener(
                // Creates an anonymous subclass of ActionListener and instantiates it
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        SearchState currentState = searchViewModel.getState();
                        JComboBox<String> diets = (JComboBox) evt.getSource();
                        String dietLabelSelected = (String) diets.getSelectedItem();
                        currentState.setDietLabel(dietLabelSelected);
                    }
                }
        );

        healthLabelOptions.addActionListener(
                // Creates an anonymous subclass of ActionListener and instantiates it
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        SearchState currentState = searchViewModel.getState();
                        JComboBox<String> healths = (JComboBox) evt.getSource();
                        String healthLabelSelected = (String) healths.getSelectedItem();
                        currentState.setHealthLabel(healthLabelSelected);
                    }
                }
        );

        search.addActionListener(
                // Creates an anonymous subclass of ActionListener and instantiates it
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(search)) {
                            SearchState currentState = searchViewModel.getState();

                            searchController.execute(currentState.getQuery(), currentState.getDietLabel(), currentState.getHealthLabel(), currentState.getCuisine(), currentState.getMealType(), currentState.getDishType());

                            //searchController.execute(
                            //if currentState.getDietLabelEmpty(){

                        }

                        //)

                    }
                    //}
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        whole.add(searchTitle);
        //this.add(filters); see if this is needed for if the filters label is used somewhere else
        whole.add(searchInfo);
        whole.add(dropDown);
        whole.add(dropDown2);
        whole.add(dropDown3);
        whole.add(dropDown4);
        whole.add(dropDown5);
        whole.add(buttons);
        this.add(title);
        this.add(whole);
    }

    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SearchState state = (SearchState) evt.getNewValue();
        //setFields(state);
        //need to include what happens here
    }
}
