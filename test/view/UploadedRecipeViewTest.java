package view;

import entity.CommonUploadedRecipeFactory;
import entity.UploadedRecipe;
import entity.UploadedRecipeFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.uploadedRecipe.UploadedRecipeState;
import interface_adapter.uploadedRecipe.UploadedRecipeViewModel;
import interface_adapter.uploading.UploadingViewModel;
import interface_adapter.uploads.UploadsViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UploadedRecipeViewTest {

    private UploadedRecipeView uploadedRecipeView;
    private UploadingViewModel uploadingViewModelMock;
    private UploadsViewModel uploadsViewModelMock;

    private UploadedRecipeViewModel uploadedRecipeViewModelMock;

    @Test
    void actionPerformedTest() {
        UploadedRecipeView mockUploadedRecipeView = mock(UploadedRecipeView.class);
        ActionEvent mockActionEvent = mock(ActionEvent.class);

        mockUploadedRecipeView.actionPerformed(mockActionEvent);
        verify(mockUploadedRecipeView).actionPerformed(any(ActionEvent.class));

    }

    @BeforeEach
    void setUp() {
        uploadingViewModelMock = mock(UploadingViewModel.class);
        uploadsViewModelMock = mock(UploadsViewModel.class);
        uploadedRecipeViewModelMock = mock(UploadedRecipeViewModel.class);
        ViewManagerModel viewManagerModelMock = mock(ViewManagerModel.class);

        MockitoAnnotations.openMocks(this);

        uploadedRecipeView = new UploadedRecipeView(viewManagerModelMock,
                new UploadedRecipeState(), uploadingViewModelMock, uploadsViewModelMock, uploadedRecipeViewModelMock);
    }

    @Test
    void propertyChangeUITest() {

        UploadedRecipeState newState = new UploadedRecipeState();
        newState.setUploadedRecipeName("New Recipe");
        newState.setUploadedRecipeIngredients("New Ingredients");
        newState.setUploadedRecipeInstructions("New Instructions");

        // Act
        uploadedRecipeView.propertyChange(new PropertyChangeEvent(this, "uploadedRecipeState", null, newState));

        // Assert
        assertEquals(newState.getUploadedRecipeName(), uploadedRecipeView.title.getText());
        assertEquals("Ingredients: " + newState.getUploadedRecipeIngredients(), uploadedRecipeView.recipeIngredientsArea.getText());
        assertEquals("Instructions: " + newState.getUploadedRecipeInstructions(), uploadedRecipeView.recipeInstructionsArea.getText());
    }

    @Test
    void getViewName() {
        UploadedRecipeView uploadedRecipeView = new UploadedRecipeView(
                new ViewManagerModel(), new UploadedRecipeState(), new UploadingViewModel(),
                new UploadsViewModel(), new UploadedRecipeViewModel()
        );

        String result = uploadedRecipeView.getViewName();

        assertEquals("Uploaded Recipe", result);

    }
}