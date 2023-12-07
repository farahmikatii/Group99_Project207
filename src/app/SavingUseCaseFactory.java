package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.saved.SavedController;
import interface_adapter.saved.SavedPresenter;
import interface_adapter.saved.SavedViewModel;
import use_case.saved.SavedDataAccessInterface;
import use_case.saved.SavedInputBoundary;
import use_case.saved.SavedInteractor;
import use_case.saved.SavedOutputBoundary;
import view.SavedView;

import javax.swing.*;
import java.io.IOException;

public class SavingUseCaseFactory {

//    public static SavedView create(
//            ViewManagerModel viewManagerModel, SavedViewModel savedViewModel, ProfileViewModel profileViewModel) {
//        try {
//            SavedController savedController = createUserSavedUseCase(viewManagerModel, savedViewModel, profileViewModel);
//            return new SavedView(savedViewModel, viewManagerModel, profileViewModel, savedController);
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Could not open.");
//        }
//        return null;
//    }
//
//    private static SavedController createUserSavedUseCase(ViewManagerModel viewManagerModel, SavedViewModel savedViewModel,
//                                                          ProfileViewModel profileViewModel) throws IOException {
//        SavedOutputBoundary savedOutputBoundary = new SavedPresenter(viewManagerModel, savedViewModel, profileViewModel);
//
//        SavingUseCaseFactory savingUseCaseFactory = new SavingUseCaseFactory();
//
//        SavedDataAccessInterface savedDataAccessInterface = new SavedDataAccessInterface() {
//        };
//
//        SavedInputBoundary savedInteractor = new SavedInteractor(savedDataAccessInterface, savedOutputBoundary, savingUseCaseFactory);
//
//        return new SavedController(savedInteractor);
//
//    }
}
