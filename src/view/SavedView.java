package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.saved.SavedState;
import interface_adapter.saved.SavedViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SavedView extends JPanel implements ActionListener,PropertyChangeListener {
    public final String viewName = "Saved Recipes";

    private final SavedViewModel savedViewModel;
    private final ViewManagerModel viewManagerModel;

    final JButton back;

    public SavedView(SavedViewModel savedViewModel, ViewManagerModel viewManagerModel) {
        this.savedViewModel = savedViewModel;
        this.viewManagerModel = viewManagerModel;
        this.savedViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(savedViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        back = new JButton(savedViewModel.BACK_BUTTON_LABEL);
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
                          SavedState currentState = savedViewModel.getState();
                          savedViewModel.setState(currentState);
                          savedViewModel.firePropertyChanged();
                          viewManagerModel.setActiveView(savedViewModel.getViewName());
                          viewManagerModel.firePropertyChanged();
                      }
                  }
              }
      );

      //TODO: show saved recipes here (in same presentation as results)
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
