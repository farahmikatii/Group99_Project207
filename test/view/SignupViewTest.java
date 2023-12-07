package view;

import interface_adapter.signup.SignupState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SignupViewTest {


    @Mock
    private JOptionPane mockJOptionPane;

    // @InjectMocks
    // private PropertyChangeListener propertyChangeListener;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void actionPerformedTest() {
        SignupView mockSignupView = mock(SignupView.class);
        ActionEvent mockActionEvent = mock(ActionEvent.class);
        when(mockActionEvent.getActionCommand()).thenReturn("someCommand");

        mockSignupView.actionPerformed(mockActionEvent);
        verify(mockSignupView).actionPerformed(any(ActionEvent.class));

    }



/*    @Test
    void propertyChangeTest() {
        SignupState signupState = new SignupState();
        signupState.setUsernameError("Invalid username");
        PropertyChangeEvent event = new PropertyChangeEvent(this, "signupState", null, signupState);

        propertyChangeListener.propertyChange(event);

        verify(mockJOptionPane);
        JOptionPane.showMessageDialog((Component) propertyChangeListener, "Invalid username");
    }*/
}