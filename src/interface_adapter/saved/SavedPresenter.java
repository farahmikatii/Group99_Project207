package interface_adapter.saved;

import interface_adapter.profile.ProfileViewModel;
import use_case.saved.SavedOutputBoundary;
import interface_adapter.ViewManagerModel;
import interface_adapter.saved.SavedViewModel;
import use_case.saved.SavedOutputData;

public class SavedPresenter implements SavedOutputBoundary {

    private final SavedViewModel savedViewModel;

    private final ProfileViewModel profileViewModel;

    private ViewManagerModel viewManagerModel;

    public SavedPresenter(ViewManagerModel viewManagerModel, SavedViewModel savedViewModel,
                          ProfileViewModel profileViewModel){
        this.viewManagerModel = viewManagerModel;
        this.savedViewModel = savedViewModel;
        this.profileViewModel = profileViewModel;
    }


    @Override
    public void prepareSuccessView(SavedOutputData savedOutputData){
        // On success, switch to the Saved View

        SavedState savedState = savedViewModel.getState();
        viewManagerModel.setActiveView(savedViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error){
        SavedState savedState = savedViewModel.getState();

        //savedState.setRecipeName(error);

        savedViewModel.firePropertyChanged();
    }


}
