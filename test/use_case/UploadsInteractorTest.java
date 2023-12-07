package use_case;

import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.uploading.UploadingDataAccessInterface;
import use_case.uploads.UploadsDataAccessInterface;
import use_case.uploads.UploadsInputData;
import use_case.uploads.UploadsInteractor;
import use_case.uploads.UploadsOutputBoundary;

import java.io.IOException;

public class UploadsInteractorTest {

    @Test
    void successTest() throws IOException {
        UserFactory userFactory = new CommonUserFactory();
        UploadsDataAccessInterface uploadsDataAccessInterface = new FileUserDataAccessObject("test/user1.csv", userFactory);

        UploadsOutputBoundary uploadsOutputBoundary = new UploadsOutputBoundary() {
            @Override
            public boolean equals(Object obj) {
                return super.equals(obj);
            }
        };

        UploadsInteractor uploadsInteractor = new UploadsInteractor(uploadsDataAccessInterface, uploadsOutputBoundary);
        Assertions.assertNotNull(uploadsInteractor);
        Assertions.assertNotNull(uploadsInteractor.uploadsDataAccessInterface);
        Assertions.assertNotNull(uploadsInteractor.uploadsOutputBoundary);

    }
}
