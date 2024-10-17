package use_case.note;

import entity.CommonUserFactory;
import entity.User;

public class NoteInteractor implements NoteInputBoundary {

    private final NoteDataAccessInterface noteDataAccessInterface;
    private final NoteOutputBoundary noteOutputBoundary;
    private final User user = new CommonUserFactory().create("jonathan_calver2", "abc123");

    public NoteInteractor(NoteDataAccessInterface noteDataAccessInterface,
                          NoteOutputBoundary noteOutputBoundary) {
        this.noteDataAccessInterface = noteDataAccessInterface;
        this.noteOutputBoundary = noteOutputBoundary;
    }

    /**
     * Executes the refresh note use case.
     *
     */
    @Override
    public void executeRefresh() {
        final String note = noteDataAccessInterface.loadNote(user);
        noteOutputBoundary.prepareSuccessView(note);
    }

    /**
     * Executes the save note use case.
     *
     * @param note the input data
     */
    @Override
    public void executeSave(String note) {
        final String updatedNote = noteDataAccessInterface.saveNote(user, note);
        noteOutputBoundary.prepareSuccessView(updatedNote);
    }
}
