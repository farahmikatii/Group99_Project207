package view;

import org.junit.Test;

import java.awt.event.ActionEvent;

import static org.mockito.Mockito.*;

public class ProfileViewTest {

    @Test
    public void actionPerformedTest(){
        ProfileView mockProfileView = mock(ProfileView.class);
        ActionEvent mockActionEvent = mock(ActionEvent.class);
        when(mockActionEvent.getActionCommand()).thenReturn("someCommand");

        mockProfileView.actionPerformed(mockActionEvent);
        verify(mockProfileView).actionPerformed(any(ActionEvent.class));
    }

}
