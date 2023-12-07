package interface_adapter.uploading;

import interface_adapter.saved.SavedController;
import org.junit.Test;
import use_case.saved.SavedInputBoundary;
import use_case.saved.SavedInputData;
import use_case.uploading.UploadingInputBoundary;
import use_case.uploading.UploadingInputData;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UploadingControllerTest {
    @Test
    public void testExecute() {
        // Arrange
        UploadingInputBoundary mockUploadingInteractor = mock(UploadingInputBoundary.class);
        UploadingController uploadingController = new UploadingController(mockUploadingInteractor);

        String name = "Test Recipe";
        String ingridents = "food";
        String instructions = "Bake";
        Image image = null;

        // Act
        uploadingController.execute(name, ingridents, instructions, image);

        // Assert
        verify(mockUploadingInteractor, times(1)).execute(any(UploadingInputData.class));
    }

}