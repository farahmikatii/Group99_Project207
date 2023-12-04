package interface_adapter.uploads;

import use_case.uploads.UploadsInputBoundary;

public class UploadsController {

    final UploadsInputBoundary uploadsInputBoundary;

    public UploadsController(UploadsInputBoundary uploadsInputBoundary) {
        this.uploadsInputBoundary = uploadsInputBoundary;
    }

    public void updateView(){
        //call method in uploadsview to update recipes?


    }
}
