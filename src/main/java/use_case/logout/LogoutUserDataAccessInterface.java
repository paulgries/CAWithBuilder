package use_case.logout;

/**
 * DAO for the Logout Use Case.
 */
public interface LogoutUserDataAccessInterface {
    String getCurrentUsername();
    void setCurrentUsername(String username);
}
