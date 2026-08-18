package authentication.login.use_case;

import user.CommonUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test cases for the Login Interactor.
 */
@ExtendWith(MockitoExtension.class)
public class LoginInteractorTest {

    @Mock
    private LoginUserDataAccessInterface userDataAccessObject;
    @Mock
    private LoginOutputBoundary loginPresenter;

    private LoginInteractor loginInteractor;

    /**
     * Sets up the interactor with the mocked dependencies.
     */
    @BeforeEach
    void setUp() {
        loginInteractor = new LoginInteractor(userDataAccessObject, loginPresenter);
    }

    /**
     * Tests that the interactor reports a failure when the account does not exist.
     */
    @Test
    void loginAccountDoesNotExist_ShowsFailureView() {
        when(userDataAccessObject.existsByName("Paul")).thenReturn(false);

        final LoginInputData inputData = new LoginInputData("Paul", "password");
        loginInteractor.execute(inputData);

        verify(loginPresenter).prepareFailView("Paul: Account does not exist.");
        verify(loginPresenter, never()).prepareSuccessView(any());
    }

    /**
     * Tests that the interactor reports a failure when the password is incorrect.
     */
    @Test
    void loginIncorrectPassword_ShowsFailureView() {
        when(userDataAccessObject.existsByName("Paul")).thenReturn(true);
        when(userDataAccessObject.get("Paul")).thenReturn(new CommonUser("Paul", "correct"));

        final LoginInputData inputData = new LoginInputData("Paul", "wrong");
        loginInteractor.execute(inputData);

        verify(loginPresenter).prepareFailView("Incorrect password for \"Paul\".");
        verify(loginPresenter, never()).prepareSuccessView(any());
    }

    /**
     * Tests that the interactor shows the success view when the login succeeds.
     */
    @Test
    void loginSuccess_ShowsSuccessView() {
        when(userDataAccessObject.existsByName("Paul")).thenReturn(true);
        when(userDataAccessObject.get("Paul")).thenReturn(new CommonUser("Paul", "password"));

        final LoginInputData inputData = new LoginInputData("Paul", "password");
        loginInteractor.execute(inputData);

        final ArgumentCaptor<LoginOutputData> outputDataCaptor =
                ArgumentCaptor.forClass(LoginOutputData.class);
        verify(loginPresenter).prepareSuccessView(outputDataCaptor.capture());
        final LoginOutputData outputData = outputDataCaptor.getValue();
        assertEquals("Paul", outputData.getUsername());
        verify(loginPresenter, never()).prepareFailView(any());
    }
}
