package interface_adapter.uploading;

import use_case.uploading.UploadingInputBoundary;
import use_case.uploading.UploadingInputData;

import java.awt.*;

public class UploadingController {

    final UploadingInputBoundary uploadingInputBoundary;

    public UploadingController(UploadingInputBoundary uploadingInputBoundary){
        this.uploadingInputBoundary = uploadingInputBoundary;
    }

    public void execute(String name, String ingredients, String instructions, Image recipeImage){
        UploadingInputData uploadingInputData = new UploadingInputData(
                name, ingredients, instructions, recipeImage);

        uploadingInputBoundary.execute(uploadingInputData);
    }
}
