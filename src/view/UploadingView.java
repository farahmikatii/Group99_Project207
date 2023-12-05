package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.uploading.UploadingController;
import interface_adapter.uploading.UploadingState;
import interface_adapter.uploading.UploadingViewModel;
import interface_adapter.uploads.UploadsViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UploadingView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Uploading New Recipe";

    private final JTextField recipenameInputField = new JTextField(15);

    private final JTextArea ingredientsInputField = new JTextArea(20, 20);

    private final JTextArea instructionsInputField = new JTextArea(50, 50);

    private final UploadingController uploadingController;

    private final UploadingViewModel uploadingViewModel;

    private final ProfileViewModel profileViewModel;

    private final ViewManagerModel viewManagerModel;

    private final UploadsViewModel uploadsViewModel;

    private final JButton upload;

    private final JButton back;

    private final JButton uploadRecipePhoto;

    public UploadingView(UploadingController uploadingController, UploadingViewModel uploadingViewModel, ProfileViewModel profileViewModel, ViewManagerModel viewManagerModel, UploadsViewModel uploadsViewModel) {

        this.uploadingController = uploadingController;
        this.uploadingViewModel = uploadingViewModel;
        this.profileViewModel = profileViewModel;
        this.viewManagerModel = viewManagerModel;
        this.uploadsViewModel = uploadsViewModel;
        uploadingViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(UploadingViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel imageLabel = new JLabel();

        LabelTextPanel recipeTitle = new LabelTextPanel(
                new JLabel(UploadingViewModel.RECIPE_LABEL), recipenameInputField);
        LabelTextAreaPanel ingredientsInfo = new LabelTextAreaPanel(
                new JLabel(UploadingViewModel.INGREDIENTS_LABEL), ingredientsInputField);
        LabelTextAreaPanel instructionsInfo = new LabelTextAreaPanel(
                new JLabel(UploadingViewModel.INSTRUCTIONS_LABEL), instructionsInputField);


        JPanel buttons = new JPanel();
        upload = new JButton(UploadingViewModel.UPLOAD_BUTTON_LABEL);
        buttons.add(upload);
        back = new JButton(UploadingViewModel.BACK_BUTTON_LABEL);
        buttons.add(back);
        uploadRecipePhoto = new JButton(UploadingViewModel.RECIPE_PHOTO_LABEL);
        buttons.add(uploadRecipePhoto);


        uploadRecipePhoto.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(uploadRecipePhoto)) {
                            JFileChooser fileChooser = new JFileChooser();
                            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                            int result = fileChooser.showOpenDialog(getParent());
                            if (result == JFileChooser.APPROVE_OPTION) {
                                try {
                                    File file = fileChooser.getSelectedFile();
                                    BufferedImage picture = ImageIO.read(file);

                                    /*imageLabel.setIcon(new ImageIcon(picture));
                                    add(imageLabel);*/

                                    UploadingState currentState = uploadingViewModel.getState();
                                    currentState.setRecipeImage(picture);

                                } catch (IOException ioe) {
                                    ioe.printStackTrace();
                                    JOptionPane.showMessageDialog(null, "ERROR");
                                }
                            }
                        }
                    }
                }

        );

        upload.addActionListener(
                //after a user clicks the upload button, they are taken back to the profile view
                // also adds the uploaded recipe as a data access object
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(upload)) {
                            UploadingState currentState = uploadingViewModel.getState();

                            uploadingController.execute(
                                    currentState.getRecipe(),
                                    currentState.getIngredients(),
                                    currentState.getInstructions(),
                                    currentState.getRecipeImage());

                        }

                        uploadsViewModel.firePropertyChanged();

                        recipenameInputField.setText("");
                        ingredientsInputField.setText("");
                        instructionsInputField.setText("");

                    }
                }
        );

        back.addActionListener(
                // takes user back to the profile view
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(back)) {
                            ProfileState currentState = profileViewModel.getState();
                            profileViewModel.setState(currentState);
                            profileViewModel.firePropertyChanged();
                            viewManagerModel.setActiveView(profileViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                        recipenameInputField.setText("");
                        ingredientsInputField.setText("");
                        instructionsInputField.setText("");
                    }
                }
        );

        recipenameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        UploadingState currentState = uploadingViewModel.getState();
                        String text = recipenameInputField.getText() + e.getKeyChar();
                        currentState.setRecipe(text);
                        uploadingViewModel.setState(currentState);

                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        ingredientsInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        UploadingState currentState = uploadingViewModel.getState();
                        String text = ingredientsInputField.getText() + e.getKeyChar();
                        currentState.setIngredients(text);
                        uploadingViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        instructionsInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        UploadingState currentState = uploadingViewModel.getState();
                        String text = instructionsInputField.getText() + e.getKeyChar();
                        currentState.setInstructions(text);
                        uploadingViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(recipeTitle);
        this.add(ingredientsInfo);
        this.add(instructionsInfo);
        this.add(buttons);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    class LabelTextAreaPanel extends JPanel {
        public LabelTextAreaPanel(JLabel label, JTextArea textArea) {
            setLayout(new BorderLayout());
            add(label, BorderLayout.NORTH);
            add(new JScrollPane(textArea), BorderLayout.CENTER);
        }

    }
}

