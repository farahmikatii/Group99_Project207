package use_case.uploading;

import entity.UploadedRecipe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UploadingDataAccessInterface {
    void saveUploadedRecipe(UploadedRecipe uploadedRecipe);

    List<Map<String, Object>> readUploadedRecipesFromCSV();
}
