package view;
import interface_adapter.ViewManagerModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.saved.SavedState;
import interface_adapter.saved.SavedViewModel;
import interface_adapter.uploading.UploadingState;
import interface_adapter.uploading.UploadingViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class ProfileView extends JPanel implements ActionListener, PropertyChangeListener{

    public final String viewName = "profile";

    private final UploadingViewModel uploadingViewModel;

    private final ViewManagerModel viewManagerModel;

    private final SavedViewModel savedViewModel;
    private final JButton saved;

    private final JButton uploads;

    private final JButton uploadNew;

    public ProfileView(UploadingViewModel uploadingViewModel, ViewManagerModel viewManagerModel, SavedViewModel savedViewModel) {

        this.uploadingViewModel = uploadingViewModel;
        this.viewManagerModel = viewManagerModel;
        this.savedViewModel = savedViewModel;
        uploadingViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(ProfileViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        saved = new JButton(ProfileViewModel.SAVED_BUTTON_LABEL);
        buttons.add(saved);
        uploads = new JButton(ProfileViewModel.UPLOAD_BUTTON_LABEL);
        buttons.add(uploads);
        uploadNew = new JButton(ProfileViewModel.UPLOAD_NEW_BUTTON_LABEL);
        buttons.add(uploadNew);

        uploadNew.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(uploadNew)){
                            UploadingState currentState = uploadingViewModel.getState();
                            uploadingViewModel.setState(currentState);
                            uploadingViewModel.firePropertyChanged();
                            viewManagerModel.setActiveView(uploadingViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }

        }
        );

        saved.addActionListener(
                // takes the user to its saved recipes
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(saved)){
                            SavedState currentState = savedViewModel.getState();
                            savedViewModel.setState(currentState);
                            savedViewModel.firePropertyChanged();
                            viewManagerModel.setActiveView(savedViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }

                    }
                }
        );

        uploads.addActionListener(
                // takes the user to its personal uploaded recipes
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {


                    }
                }
        );
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
