package account.change_password;

import framework.ViewManagerModel;
import account.change_password.use_case.ChangePasswordOutputBoundary;
import account.change_password.use_case.ChangePasswordOutputData;

/**
 * The Presenter for the Change Password Use Case.
 */
public class ChangePasswordPresenter implements ChangePasswordOutputBoundary {

    private final ChangePasswordViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;

    public ChangePasswordPresenter(ViewManagerModel viewManagerModel,
                             ChangePasswordViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void prepareSuccessView(ChangePasswordOutputData outputData) {
        // TODO update the viewmodel!
        loggedInViewModel.firePropertyChanged("password");

    }

    @Override
    public void prepareFailView(String error) {
        // TODO update the viewmodel!
    }
}
