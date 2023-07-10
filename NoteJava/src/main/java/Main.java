import javax.swing.*;

public class Main {
    public static void main(String args[]){

        NoteImpl notes = new NoteImpl();
        /*
        notes.getNotebyID(1);
        notes.getNotes();
        notes.deleteNote(23);
        */

        // Creating and displaying the GUI
        SwingUtilities.invokeLater(() -> {
            GUI gui = new GUI();
            gui.setVisible(true);
        });




    }

}
