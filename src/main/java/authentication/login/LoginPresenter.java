package authentication.login;

import framework.ViewManagerModel;
import account.change_password.ChangePasswordState;
import account.change_password.ChangePasswordViewModel;
import authentication.login.use_case.LoginOutputBoundary;
import authentication.login.use_case.LoginOutputData;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final ChangePasswordViewModel changePasswordViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          ChangePasswordViewModel changePasswordViewModel,
                          LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.changePasswordViewModel = changePasswordViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.

        final ChangePasswordState loggedInState = changePasswordViewModel.getState();
        loggedInState.setUsername(response.getUsername());
        this.changePasswordViewModel.setState(loggedInState);
        this.changePasswordViewModel.firePropertyChanged();

        this.viewManagerModel.setState(changePasswordViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChanged();
    }
}
