package use_case.note;

public interface NoteInputBoundary {

    /**
     * Executes the refresh note use case.
     */
    void executeRefresh();

    /**
     * Executes the save note use case.
     * @param message the input data
     */
    void executeSave(String message);
}
