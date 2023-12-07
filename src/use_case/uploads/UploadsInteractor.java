package use_case.uploads;

public class UploadsInteractor implements UploadsInputBoundary {
    public final UploadsDataAccessInterface uploadsDataAccessInterface;
    public final UploadsOutputBoundary uploadsOutputBoundary;
    public UploadsInteractor(UploadsDataAccessInterface uploadsDataAccessInterface, UploadsOutputBoundary uploadsOutputBoundary){
        this.uploadsDataAccessInterface = uploadsDataAccessInterface;
        this.uploadsOutputBoundary = uploadsOutputBoundary;
    }
}
