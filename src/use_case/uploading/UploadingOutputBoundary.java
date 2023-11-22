package use_case.uploading;

public interface UploadingOutputBoundary {

    void prepareSuccessView(UploadingOutputData recipe);

    void prepareFailView(String error);
}
