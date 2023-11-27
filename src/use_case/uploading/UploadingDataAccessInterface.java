package use_case.uploading;

import entity.UploadedRecipe;

public interface UploadingDataAccessInterface {
    void saveUploadedRecipe(UploadedRecipe uploadedRecipe);
}
