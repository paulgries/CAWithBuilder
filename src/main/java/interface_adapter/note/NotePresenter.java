package interface_adapter.note;

import use_case.note.NoteOutputBoundary;

public class NotePresenter implements NoteOutputBoundary {

    private final NoteViewModel noteViewModel;

    public NotePresenter(NoteViewModel noteViewModel) {
        this.noteViewModel = noteViewModel;
    }

    /**
     * Prepares the success view for the Note related Use Cases.
     *
     * @param note the output data
     */
    @Override
    public void prepareSuccessView(String note) {
        noteViewModel.getState().setNote(note);
        noteViewModel.firePropertyChanged();
    }

    /**
     * Prepares the failure view for the Note related Use Cases.
     *
     * @param errorMessage the explanation of the failure
     */
    @Override
    public void prepareFailView(String errorMessage) {

    }
}
