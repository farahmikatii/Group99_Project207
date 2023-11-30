package view;

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

    private final SearchViewModel searchViewModel;

    final JTextField searchInputField = new JTextField(15);

    JLabel filters;

    final JButton search;

    final JButton select;

    final JButton back;

    private final SearchController searchController;

    public SearchView(SearchController searchController,SearchViewModel searchViewModel) throws Exception {
        this.searchViewModel = searchViewModel;
        this.searchController = searchController;
        this.searchViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Search");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel searchInfo = new LabelTextPanel(
                new JLabel("Username"), searchInputField);

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

        search.addActionListener(
                // Creates an anonymous subclass of ActionListener and instantiates it
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(search)) {
                            SearchState currentState = searchViewModel.getState();

                            //searchController.execute(
                                    // in here we need to execute what happens in controller
                                    // gather which filters they have chosen
                                    // make dictionary with the filter
                            //);
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
        this.add(searchInfo);
        this.add(searchButton);
        this.add(backButton);
        this.add(selectButton);
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
