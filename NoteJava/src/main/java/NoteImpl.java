import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Implementation class for NotesService
public class NoteImpl implements NoteService {
    String connectionUrl = "jdbc:mysql://localhost:3306/notes_db_v1?serverTimezone=UTC";

    @Override
    public List<Note> getNotes() {
        String sqlSelectAllNotes = "SELECT * FROM notes";
        List<Note> notes = new ArrayList<>();

        try {
            // Establishing a database connection
            Connection conn = DriverManager.getConnection(connectionUrl, "user", "user123");
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllNotes);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Fetching note data from the result set
                Integer id = rs.getInt("id");
                String title = rs.getString("title");
                String text = rs.getString("text");
                // Creating a new Note object and adding it to the list
                Note noteData = new Note(id, title, text);
                notes.add(noteData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notes;
    }

    @Override
    public Note getNotebyID(int id) {
        Note n = null;
        String sqlSelectFindId = "SELECT * FROM notes WHERE id=" + id;
        try {
            // Establishing a database connection
            Connection conn = DriverManager.getConnection(connectionUrl, "user", "user123");
            PreparedStatement ps = conn.prepareStatement(sqlSelectFindId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Fetching note data from the result set
                String title = rs.getString("title");
                String text = rs.getString("text");
                // Creating a new Note object with the fetched data
                n = new Note(id, title, text);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public Note addNote(String title, String text) {
        String sqlAddNote = "INSERT INTO notes (title, text) VALUES (?,?)";
        try {
            // Establishing a database connection
            Connection conn = DriverManager.getConnection(connectionUrl, "user", "user123");
            PreparedStatement ps = conn.prepareStatement(sqlAddNote, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, title);
            ps.setString(2, text);
            ps.executeUpdate();

            // Retrieving the auto-generated ID of the newly added note
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                // Creating and returning a new Note object with the generated ID
                return new Note(id, title, text);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Note editNote(int id, String title, String text) {
        String sqlEditNote = "UPDATE notes SET title = ?, text = ? WHERE ID = ?";

        try {
            // Establishing a database connection
            Connection conn = DriverManager.getConnection(connectionUrl, "user", "user123");
            PreparedStatement ps = conn.prepareStatement(sqlEditNote);
            ps.setString(1, title);
            ps.setString(2, text);
            ps.setString(3, String.valueOf(id));
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                // If the update was successful, return a new Note object with the updated data
                return new Note(id, title, text);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Note deleteNote(int id) {
        String sqlDeleteNoteWithID = "DELETE from notes WHERE id= ?";
        try {
            // Establishing a database connection
            Connection conn = DriverManager.getConnection(connectionUrl, "user", "user123");
            PreparedStatement ps = conn.prepareStatement(sqlDeleteNoteWithID);
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                // If the deletion was successful, return the deleted note by its ID
                return getNotebyID(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}