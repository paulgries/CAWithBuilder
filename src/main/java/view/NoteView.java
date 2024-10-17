package view;

import interface_adapter.note.NoteController;
import interface_adapter.note.NoteState;
import interface_adapter.note.NoteViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for when the user is logging into the program.
 */
public class NoteView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "note";
    private final NoteViewModel noteViewModel;

    private final JLabel noteName = new JLabel("note for jonathan_calver2");
    private final JTextArea noteInputField = new JTextArea();

    private final JButton saveButton = new JButton("Save");
    private final JButton refreshButton = new JButton("Refresh");
    private final NoteController noteController;

    public NoteView(NoteViewModel noteViewModel, NoteController controller) {

        noteName.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.noteController = controller;
        this.noteViewModel = noteViewModel;
        this.noteViewModel.addPropertyChangeListener(this);

        final JPanel buttons = new JPanel();
        buttons.add(saveButton);
        buttons.add(refreshButton);

        saveButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(saveButton)) {
                            noteController.execute(noteInputField.getText());

                        }
                    }
                }
        );

        refreshButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(refreshButton)) {
                            noteController.execute(null);

                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(noteName);
        this.add(noteInputField);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final NoteState state = (NoteState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(NoteState state) {
        noteInputField.setText(state.getNote());
    }

    public String getViewName() {
        return viewName;
    }
}

