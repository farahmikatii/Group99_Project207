package interface_adapter.uploads;

import entity.UploadedRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UploadsState {

    public UploadsState(){}

    public List<Map<String, Object>> uploadedrecipesList = new ArrayList<>();

    public UploadsState(UploadsState copy){
        this.uploadedrecipesList = copy.uploadedrecipesList;
    }
// set recipes method, passes in List of maps from controller
//get recipes method

}
