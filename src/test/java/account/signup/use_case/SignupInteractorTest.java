package account.signup.use_case;

import user.CommonUserFactory;
import user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test cases for the Signup Interactor.
 */
@ExtendWith(MockitoExtension.class)
public class SignupInteractorTest {

    @Mock
    private SignupUserDataAccessInterface userDataAccessObject;
    @Mock
    private SignupOutputBoundary userPresenter;

    private SignupInteractor signupInteractor;

    /**
     * Sets up the interactor with the mocked data access and presenter.
     */
    @BeforeEach
    void setUp() {
        signupInteractor = new SignupInteractor(userDataAccessObject, userPresenter, new CommonUserFactory());
    }

    /**
     * Tests that the interactor reports a failure when the username already exists.
     */
    @Test
    void signupUserAlreadyExists_ShowsFailureView() {
        when(userDataAccessObject.existsByName("Paul")).thenReturn(true);

        final SignupInputData inputData = new SignupInputData("Paul", "password", "password");
        signupInteractor.execute(inputData);

        verify(userPresenter).prepareFailView("User already exists.");
        verify(userPresenter, never()).prepareSuccessView(any());
        verify(userDataAccessObject, never()).save(any());
    }

    /**
     * Tests that the interactor reports a failure when the passwords do not match.
     */
    @Test
    void signupPasswordsDoNotMatch_ShowsFailureView() {
        when(userDataAccessObject.existsByName("Paul")).thenReturn(false);

        final SignupInputData inputData = new SignupInputData("Paul", "password", "different");
        signupInteractor.execute(inputData);

        verify(userPresenter).prepareFailView("Passwords don't match.");
        verify(userPresenter, never()).prepareSuccessView(any());
        verify(userDataAccessObject, never()).save(any());
    }

    /**
     * Tests that the interactor creates and saves a user and shows the success view.
     */
    @Test
    void signupSuccess_CreatesUserAndShowsSuccessView() {
        when(userDataAccessObject.existsByName("Paul")).thenReturn(false);

        final SignupInputData inputData = new SignupInputData("Paul", "password", "password");
        signupInteractor.execute(inputData);

        final ArgumentCaptor<User> savedUserCaptor = ArgumentCaptor.forClass(User.class);
        verify(userDataAccessObject).save(savedUserCaptor.capture());
        assertEquals("Paul", savedUserCaptor.getValue().getName());
        assertEquals("password", savedUserCaptor.getValue().getPassword());

        final ArgumentCaptor<SignupOutputData> outputDataCaptor =
                ArgumentCaptor.forClass(SignupOutputData.class);
        verify(userPresenter).prepareSuccessView(outputDataCaptor.capture());
        final SignupOutputData outputData = outputDataCaptor.getValue();
        assertEquals("Paul", outputData.getUsername());
        assertFalse(outputData.isUseCaseFailed());
        verify(userPresenter, never()).prepareFailView(any());
    }

    /**
     * Tests that switchToLoginView delegates to the presenter.
     */
    @Test
    void switchToLoginView_DelegatesToPresenter() {
        signupInteractor.switchToLoginView();

        verify(userPresenter).switchToLoginView();
    }
}
