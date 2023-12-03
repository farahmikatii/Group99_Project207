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

    JButton select;

    JButton back;

    private SearchController searchController;

    public SearchView(SearchController searchController,SearchViewModel searchViewModel, ViewManagerModel viewManagerModel,
                      LoggedInViewModel loggedInViewModel) throws Exception {
        this.searchViewModel = searchViewModel;
        this.searchController = searchController;
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;

        this.searchViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Search");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel searchInfo = new LabelTextPanel(
                new JLabel("Search Here"), searchInputField);

        JPanel searchButton = new JPanel();
        JPanel backButton = new JPanel();
        JPanel selectButton = new JPanel();
        back = new JButton(SearchViewModel.BACK_BUTTON_LABEL);
        backButton.add(back);
        search = new JButton(SearchViewModel.SEARCH_BUTTON_LABEL);
        searchButton.add(search);
        select = new JButton(SearchViewModel.SELECT_BUTTON_LABEL);
        selectButton.add(select);

        JPanel dropDown = new JPanel();
        JLabel mealType = new JLabel("Select a meal type");
        mealType.setAlignmentX(Component.CENTER_ALIGNMENT);
        dropDown.add(mealType);

        String[] choices = { "breakfast", "brunch", "lunch/dinner", "snack",
                "teatime" };

        final JComboBox<String> mealTypeOptions = new JComboBox<>(choices);
        mealTypeOptions.setMaximumSize(mealTypeOptions.getPreferredSize());
        mealTypeOptions.setAlignmentX(Component.CENTER_ALIGNMENT);
        dropDown.add(mealTypeOptions);

        JPanel dropDown2 = new JPanel();
        JLabel dishType = new JLabel("Select a dish type");
        dishType.setAlignmentX(Component.CENTER_ALIGNMENT);
        dropDown2.add(dishType);

        String[] dishes = { "alcohol cocktail", "biscuits and cookies", "bread", "cereals", "condiments and sauces",
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

        String[] cuisines = { "american", "asian", "british", "caribbean", "central europe", "chinese", "eastern europe",
                "french", "greek", "indian", "italian", "japanese", "korean", "kosher", "mediterranean", "mexican",
                "middle eastern", "nordic", "south american", "south east asian", "world"};

        final JComboBox<String> cuisineTypeOptions = new JComboBox<>(cuisines);
        cuisineTypeOptions.setMaximumSize(cuisineTypeOptions.getPreferredSize());
        cuisineTypeOptions.setAlignmentX(Component.CENTER_ALIGNMENT);
        dropDown3.add(cuisineTypeOptions);

        JPanel additional = new JPanel();
        JLabel healthLabels = new JLabel("Select any additional filters");
        healthLabels.setAlignmentX(Component.LEFT_ALIGNMENT);
        additional.add(healthLabels);

        JLabel alcoholFree = new JLabel("Alcohol-Free");
        additional.add(alcoholFree);
        JButton select1 = new JButton(SearchViewModel.SELECT1_BUTTON_LABEL); //add actionlistener for select1
        additional.add(select1);

        JLabel dairyFree = new JLabel("Dairy-Free");
        additional.add(dairyFree);
        JButton select2 = new JButton(SearchViewModel.SELECT2_BUTTON_LABEL); //add actionlistener for select2
        additional.add(select2);

        JLabel eggFree = new JLabel("Egg-Free");
        additional.add(eggFree);
        JButton select3 = new JButton(SearchViewModel.SELECT3_BUTTON_LABEL); //add actionlistener for select3
        additional.add(select3);

        JLabel peanutFree = new JLabel("Peanut-Free");
        additional.add(peanutFree);
        JButton select4 = new JButton(SearchViewModel.SELECT4_BUTTON_LABEL); //add actionlistener for select4
        additional.add(select4);

        JLabel shellfishFree = new JLabel("Shellfish-Free");
        additional.add(shellfishFree);
        JButton select5 = new JButton(SearchViewModel.SELECT5_BUTTON_LABEL); //add actionlistener for select5
        additional.add(select5);

        JLabel vegan = new JLabel("Vegan");
        additional.add(vegan);
        JButton select6 = new JButton(SearchViewModel.SELECT6_BUTTON_LABEL); //add actionlistener for select6
        additional.add(select6);


        search.addActionListener(
                // Creates an anonymous subclass of ActionListener and instantiates it
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(search)) {
                            SearchState currentState = searchViewModel.getState();

                            //searchController.execute(
                                    // in here we need to execute what happens in controller
                                    // gather which filters they have chosen
                                    // make dictionary with the filter where the filter name is the key and if it is
                                    //chosen the value if not value is null
                            //);
                        }
                    }
                }
        );

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
                        //currentState.setUsername(text); change to saving this to search within the api in SearchState
                        searchViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        //this.add(filters); see if this is needed for if the filters label is used somewhere else
        this.add(searchInfo);
        this.add(searchButton);
        this.add(backButton);
        this.add(selectButton);
        this.add(dropDown);
        this.add(dropDown2);
        this.add(dropDown3);
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
