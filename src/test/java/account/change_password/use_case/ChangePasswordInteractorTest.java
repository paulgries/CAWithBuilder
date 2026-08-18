package account.change_password.use_case;

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

/**
 * Test cases for the Change Password Interactor.
 */
@ExtendWith(MockitoExtension.class)
public class ChangePasswordInteractorTest {

    @Mock
    private ChangePasswordUserDataAccessInterface userDataAccessObject;
    @Mock
    private ChangePasswordOutputBoundary userPresenter;

    private ChangePasswordInteractor changePasswordInteractor;

    /**
     * Sets up the interactor with the mocked data access and presenter.
     */
    @BeforeEach
    void setUp() {
        changePasswordInteractor =
                new ChangePasswordInteractor(userDataAccessObject, userPresenter, new CommonUserFactory());
    }

    /**
     * Tests that the interactor changes the password and shows the success view.
     */
    @Test
    void changePasswordSuccess_ChangesPasswordAndShowsSuccessView() {
        final ChangePasswordInputData inputData = new ChangePasswordInputData("newPassword", "Paul");
        changePasswordInteractor.execute(inputData);

        final ArgumentCaptor<User> changedUserCaptor = ArgumentCaptor.forClass(User.class);
        verify(userDataAccessObject).changePassword(changedUserCaptor.capture());
        assertEquals("Paul", changedUserCaptor.getValue().getName());
        assertEquals("newPassword", changedUserCaptor.getValue().getPassword());

        final ArgumentCaptor<ChangePasswordOutputData> outputDataCaptor =
                ArgumentCaptor.forClass(ChangePasswordOutputData.class);
        verify(userPresenter).prepareSuccessView(outputDataCaptor.capture());
        final ChangePasswordOutputData outputData = outputDataCaptor.getValue();
        assertEquals("Paul", outputData.getUsername());
        assertFalse(outputData.isUseCaseFailed());
        verify(userPresenter, never()).prepareFailView(any());
    }
}
