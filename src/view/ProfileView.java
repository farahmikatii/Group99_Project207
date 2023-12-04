package view;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.saved.SavedState;
import interface_adapter.saved.SavedViewModel;
import interface_adapter.uploading.UploadingController;
import interface_adapter.uploading.UploadingState;
import interface_adapter.uploading.UploadingViewModel;
import interface_adapter.uploads.UploadsState;
import interface_adapter.uploads.UploadsViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;
import java.util.List;


public class ProfileView extends JPanel implements ActionListener, PropertyChangeListener{


   public final String viewName = "profile";


    private final UploadingViewModel uploadingViewModel;

    private final UploadsViewModel uploadsViewModel;

    private final ProfileViewModel profileViewModel;

    private final ViewManagerModel viewManagerModel;

    private final SavedViewModel savedViewModel;
    private final LoggedInViewModel loggedInViewModel;

    private final UploadingController uploadingController;

    private final JButton saved;

    private final JButton uploads;

    private final JButton uploadNew;

    private final JButton back;

    public ProfileView(UploadingViewModel uploadingViewModel, ProfileViewModel profileViewModel, ViewManagerModel viewManagerModel, SavedViewModel savedViewModel, UploadsViewModel uploadsViewModel, LoggedInViewModel loggedInViewModel, UploadingController uploadingController) {

        this.uploadingViewModel = uploadingViewModel;
        this.uploadsViewModel = uploadsViewModel;
        this.profileViewModel = profileViewModel;
        this.viewManagerModel = viewManagerModel;
        this.savedViewModel = savedViewModel;
        this.loggedInViewModel = loggedInViewModel;
        this.uploadingController = uploadingController;
        uploadingViewModel.addPropertyChangeListener(this);
        profileViewModel.addPropertyChangeListener(this);

   /*     FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("/Users/farahmikati/IdeaProjects/Group99_Project207/user.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

        JLabel title = new JLabel(ProfileViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        saved = new JButton(ProfileViewModel.SAVED_BUTTON_LABEL);
        buttons.add(saved);
        uploads = new JButton(ProfileViewModel.UPLOAD_BUTTON_LABEL);
        buttons.add(uploads);
        uploadNew = new JButton(ProfileViewModel.UPLOAD_NEW_BUTTON_LABEL);
        buttons.add(uploadNew);
        back = new JButton(ProfileViewModel.BACK_BUTTON_LABEL);
        buttons.add(back);
        //TODO: make back button at the bottom of the page

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
                        if (e.getSource().equals(uploads)){
                            List<Map<String, Object>> recipesList = uploadingController.uploadedRecipes();
                            UploadsState uploadsState = uploadsViewModel.getState();
                            uploadsState.setUploadedrecipesList(recipesList);
                            uploadsViewModel.setState(uploadsState);
                            uploadsViewModel.firePropertyChanged();
                            viewManagerModel.setActiveView(uploadsViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }


                    }
                }
        );

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

        this.add(title);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO: implement
        System.out.println("Click" + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
//        ProfileState profileState = (ProfileState) evt.getNewValue();
//        setFields(profileState);

    }
//
//    private void setFields(ProfileState profileState){
//    }
}
