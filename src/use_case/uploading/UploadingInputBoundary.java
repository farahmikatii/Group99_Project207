package use_case.uploading;

import java.util.*;

public interface UploadingInputBoundary {
    void execute(use_case.uploading.UploadingInputData uploadingInputData);

    List<Map<String, Object>> uploadedRecipes();

    void executeRecipeView(UploadingInputData uploadingInputData);
}
