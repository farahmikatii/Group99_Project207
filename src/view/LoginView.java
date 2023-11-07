package view;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "login";

    private final LoginViewModel loginViewModel;
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final LoginController loginController;

    private final JButton login;
    private final JButton cancel;

    public LoginView(LoginController controller, LoginViewModel loginViewModel) {

        this.loginController = controller;
        this.loginViewModel = loginViewModel;
        loginViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(LoginViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(LoginViewModel.USERNAME_LABEL), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(LoginViewModel.PASSWORD_LABEL), passwordInputField);

        JPanel buttons = new JPanel();
        login = new JButton(LoginViewModel.LOGIN_BUTTON_LABEL);
        buttons.add(login);
        cancel = new JButton(LoginViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        login.addActionListener(
                // Creates an anonymous subclass of ActionListener and instantiates it
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(login)) {
                            LoginState currentState = loginViewModel.getState();

                            loginController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword()
                            );
                        }
                    }
                }
        );

        usernameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        LoginState currentState = loginViewModel.getState();
                        String text = usernameInputField.getText() + e.getKeyChar();
                        currentState.setUsername(text);
                        loginViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        passwordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        LoginState currentState = loginViewModel.getState();
                        currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
                        loginViewModel.setState(currentState);
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
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoginState state = (LoginState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
    }
}
