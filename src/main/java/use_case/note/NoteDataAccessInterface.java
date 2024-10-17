package use_case.note;

import entity.User;

public interface NoteDataAccessInterface {

    public String saveNote(User user, String message);

    public String loadNote(User user);

}
