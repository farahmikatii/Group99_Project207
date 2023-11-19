package view;



import interface_adapter.profile_page.ProfileViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ProfileView extends JPanel implements ActionListener{

   // public final String viewName

    private final JButton saved;

    private final JButton upload;

    private final JButton upload_new;

    public ProfileView() {

        JLabel title = new JLabel(ProfileViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        saved = new JButton(ProfileViewModel.SAVED_BUTTON_LABEL);
        buttons.add(saved);
        upload = new JButton(ProfileViewModel.UPLOAD_BUTTON_LABEL);
        buttons.add(upload);
        upload_new = new JButton(ProfileViewModel.UPLOAD_NEW_BUTTON_LABEL);
        buttons.add(upload_new);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
