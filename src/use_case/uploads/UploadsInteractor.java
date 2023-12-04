package use_case.uploads;

public class UploadsInteractor implements UploadsInputBoundary {
    final UploadsDataAccessInterface uploadsDataAccessInterface;
    final UploadsOutputBoundary uploadsOutputBoundary;
    public UploadsInteractor(UploadsDataAccessInterface uploadsDataAccessInterface, UploadsOutputBoundary uploadsOutputBoundary){
        this.uploadsDataAccessInterface = uploadsDataAccessInterface;
        this.uploadsOutputBoundary = uploadsOutputBoundary;
    }
}
