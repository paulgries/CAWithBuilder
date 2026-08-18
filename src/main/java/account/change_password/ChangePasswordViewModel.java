package account.change_password;
import framework.ViewModel;



/**
 * The View Model for the Logged In View.
 */
public class ChangePasswordViewModel extends ViewModel<ChangePasswordState> {

    public ChangePasswordViewModel() {
        super("logged in");
        setState(new ChangePasswordState());
    }

}
