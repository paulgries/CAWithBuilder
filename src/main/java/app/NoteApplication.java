package app;

import data_access.DBNoteDataAccessObject;
import interface_adapter.note.NoteController;
import interface_adapter.note.NotePresenter;
import interface_adapter.note.NoteViewModel;
import use_case.note.NoteDataAccessInterface;
import use_case.note.NoteInputBoundary;
import use_case.note.NoteInteractor;
import use_case.note.NoteOutputBoundary;
import view.NoteView;

import javax.swing.*;

/**
 * An application where we can view and add to a note stored by a user.
 */
public class NoteApplication {

    public static final int HEIGHT = 300;
    public static final int WIDTH = 400;

    // TODO
    //     integrate with the whole system
    //     clean up the Checkstyle
    //     add test and break into logical pieces
    //     store password somewhere and tidy

    public static void main(String[] args) {
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Note Application");
        frame.setSize(HEIGHT, WIDTH);

        // create the viewmodel
        final NoteViewModel noteViewModel = new NoteViewModel();

        // create the data access
        final NoteDataAccessInterface noteDataAccess = new DBNoteDataAccessObject();

        // create the output boundary (injecting the view model)
        final NoteOutputBoundary noteOutputBoundary = new NotePresenter(noteViewModel);

        // create the input boundary (injecting the data access and output boundary)
        final NoteInputBoundary noteInteractor = new NoteInteractor(noteDataAccess,
                noteOutputBoundary);

        // create the controller (injecting the interactor)
        final NoteController controller = new NoteController(noteInteractor);

        // create the view (injecting the view model and controller)
        final JPanel panel = new NoteView(noteViewModel, controller);

        frame.add(panel);

        // refresh so that the note is visible when we start the program!
        noteInteractor.executeRefresh();

        frame.setVisible(true);
    }
}
