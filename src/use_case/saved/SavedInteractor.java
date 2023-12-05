package use_case.saved;

import app.SavingUseCaseFactory;

public class SavedInteractor implements SavedInputBoundary {

    final SavedDataAccessInterface savedDataAccessInterface;

    final SavedOutputBoundary savedPresenter;

    final SavingUseCaseFactory savingUseCaseFactory;

    public SavedInteractor(SavedDataAccessInterface savedDataAccessInterface, SavedOutputBoundary savedPresenter,
                            SavingUseCaseFactory savingUseCaseFactory){
        this.savedDataAccessInterface = savedDataAccessInterface;
        this.savedPresenter = savedPresenter;
        this.savingUseCaseFactory = savingUseCaseFactory;
    }

    @Override
    public void execute(SavedInputData savedInputData){

    }
}
