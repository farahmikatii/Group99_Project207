package view;

import interface_adapter.ViewManagerModel;

import interface_adapter.saved.SavedState;
import interface_adapter.uploads.UploadsState;
import interface_adapter.uploads.UploadsViewModel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UploadsView extends JPanel implements ActionListener, PropertyChangeListener{

    public final String viewName = "Uploaded Recipes";

    private final UploadsViewModel uploadsViewModel;

    private final ViewManagerModel viewManagerModel;

    final JButton back;

    public UploadsView(UploadsViewModel uploadsViewModel, ViewManagerModel viewManagerModel) {
        this.uploadsViewModel = uploadsViewModel;
        this.viewManagerModel = viewManagerModel;
        this.uploadsViewModel.addPropertyChangeListener(this);

       setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(this);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane);

        JLabel title = new JLabel(uploadsViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        back = new JButton(uploadsViewModel.BACK_BUTTON_LABEL);
        buttons.add(back);

        back.addActionListener(this);

        this.add(title);
        this.add(buttons);

        back.addActionListener(
                // takes back to profile page
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(back)){
                            UploadsState currentState = uploadsViewModel.getState();
                            uploadsViewModel.setState(currentState);
                            uploadsViewModel.firePropertyChanged();
                            viewManagerModel.setActiveView(uploadsViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        // create a scrolling view of the recipes that have been uploaded and are stored in the csv file
        // read from csv file to convert those recipes
        // when you click on recipe, it should take you to a new page that displays all the recipe info



    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO: implement
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //TODO: implement
    }
}
