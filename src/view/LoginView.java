package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;


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
    private final ViewManagerModel viewManagerModel;

    final JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();

    final JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel passwordErrorField = new JLabel();

    private final LoginController loginController;

    private final SignupViewModel signupViewModel;

    private final JButton login;
    private final JButton signup;

    public LoginView(LoginController controller, LoginViewModel loginViewModel, SignupViewModel signupViewModel, ViewManagerModel viewManagerModel) {

        this.loginController = controller;
        this.loginViewModel = loginViewModel;
        this.signupViewModel = signupViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel.addPropertyChangeListener(this);
        this.signupViewModel.addPropertyChangeListener(this);

        JPanel entire = new JPanel();
        entire.setLayout(new BorderLayout());
        ImageIcon nothing = new ImageIcon("./src/app_pictures/Empty.png");
        JLabel empty = new JLabel(nothing);
        entire.add(empty, BorderLayout.PAGE_START);


        JPanel whole = new JPanel();
        whole.setLayout(new FlowLayout());

        ImageIcon recipeFlow = new ImageIcon("./src/app_pictures/fullTitle.png");
        JLabel pic = new JLabel(recipeFlow);
        //whole.add(pic);

        JPanel left = new JPanel();
        left.setPreferredSize(new Dimension(500, 375));

        JLabel title = new JLabel("Login");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Serif", Font.PLAIN, 25));

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Username"), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), passwordInputField);

        JPanel buttons = new JPanel();
        login = new JButton(LoginViewModel.LOGIN_BUTTON_LABEL);
        login.setBackground(new Color(254,232,210));
        buttons.add(login);
        signup = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
        signup.setBackground(new Color(254,232,210));
        buttons.add(signup);

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
                            loginController.setUsername(
                                    currentState.getUsername(),
                                    currentState.getPassword()
                            );

                        }
                    }
                }
        );

        signup.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(signup)) {
                            SignupState currentSignupState = signupViewModel.getState();
                            signupViewModel.setState(currentSignupState);
                            signupViewModel.firePropertyChanged();
                            viewManagerModel.setActiveView(signupViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
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
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

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
                });

        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.setBackground(new Color(254,216,177));

        left.add(title);

        left.add(title);
        left.add(usernameInfo);
        left.add(usernameErrorField);
        left.add(passwordInfo);
        left.add(passwordErrorField);
        left.add(buttons);

        whole.add(left);
        whole.add(pic);

        entire.add(whole, BorderLayout.CENTER);
        this.add(entire);




//        this.add(title);
//        this.add(usernameInfo);
//        this.add(usernameErrorField);
//        this.add(passwordInfo);
//        this.add(passwordErrorField);
//        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        try {
            LoginState state = (LoginState) evt.getNewValue();
            setFields(state);
        }
        catch(ClassCastException e){
            SignupState state = (SignupState) evt.getNewValue();
            if (state.getUsernameError() != null){
                JOptionPane.showMessageDialog(this, state.getUsernameError());
            }
        }
    }

    private void setFields(LoginState state){
        usernameInputField.setText(state.getUsername());
    }
}
