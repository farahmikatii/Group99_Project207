package interface_adapter.uploads;

import entity.UploadedRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UploadsState {
    public UploadsState(UploadsState copy){
        uploadedrecipesList = copy.uploadedrecipesList;
    }
    public UploadsState(){}

    private List<Map<String, Object>> uploadedrecipesList = new ArrayList<>();

    public List<Map<String, Object>> getUploadedrecipesList(){
        return uploadedrecipesList;
    }

    public void setUploadedrecipesList(List<Map<String, Object>> uploadedrecipesList){
        this.uploadedrecipesList = uploadedrecipesList;
    }

// set recipes method, passes in List of maps from controller
//get recipes method

}
