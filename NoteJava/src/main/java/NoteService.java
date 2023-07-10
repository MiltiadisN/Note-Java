import java.util.List;

// Interface for NotesService
public interface NoteService {

    // Method to retrieve all notes
    public List<Note> getNotes();

    // Method to retrieve a note by its ID
    public Note getNotebyID(int id);

    // Method to add a new note
    public Note addNote(String title, String text);

    // Method to edit an existing note
    public Note editNote(int id, String title, String text);

    // Method to delete a note by its ID
    public Note deleteNote(int id);
}