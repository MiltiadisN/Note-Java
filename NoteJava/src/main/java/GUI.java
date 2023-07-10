import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// GUI class
public class GUI extends JFrame implements ActionListener{

        private NoteService notesService;

        private JButton getAllNotesButton;
        private JButton getNoteByIdButton;
        private JButton addNoteButton;
        private JButton editNoteButton;
        private JButton deleteNoteButton;

        private JButton showNoteButton;

        private JList<String> notesList;
        private DefaultListModel<String> notesListModel;


        public GUI(){
            setTitle("Notes Application");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(400, 300);
            setLocationRelativeTo(null);

            notesService = new NoteImpl();

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(5, 5, 5, 5);

            // Creating buttons
            getAllNotesButton = new JButton("Get All Notes");
            getAllNotesButton.addActionListener(this);
            getAllNotesButton.setPreferredSize(new Dimension(120, 30));
            gbc.gridx = 0;
            gbc.gridy = 0;
            mainPanel.add(getAllNotesButton, gbc);

            getNoteByIdButton = new JButton("Get Note by ID");
            getNoteByIdButton.addActionListener(this);
            getNoteByIdButton.setPreferredSize(new Dimension(120, 30));
            gbc.gridx = 0;
            gbc.gridy = 1;
            mainPanel.add(getNoteByIdButton, gbc);

            addNoteButton = new JButton("Add Note");
            addNoteButton.addActionListener(this);
            addNoteButton.setPreferredSize(new Dimension(120, 30));
            gbc.gridx = 0;
            gbc.gridy = 2;
            mainPanel.add(addNoteButton, gbc);

            editNoteButton = new JButton("Edit Note");
            editNoteButton.addActionListener(this);
            editNoteButton.setPreferredSize(new Dimension(120, 30));
            gbc.gridx = 0;
            gbc.gridy = 3;
            mainPanel.add(editNoteButton, gbc);

            deleteNoteButton = new JButton("Delete Note");
            deleteNoteButton.addActionListener(this);
            deleteNoteButton.setPreferredSize(new Dimension(120, 30));
            gbc.gridx = 0;
            gbc.gridy = 4;
            mainPanel.add(deleteNoteButton, gbc);

            showNoteButton = new JButton("Show Note");
            showNoteButton.addActionListener(this);
            showNoteButton.setPreferredSize(new Dimension(120, 30));
            gbc.gridx = 0;
            gbc.gridy = 5;
            mainPanel.add(showNoteButton, gbc);
            // Creating the notes list
            notesListModel = new DefaultListModel<>();
            notesList = new JList<>(notesListModel);
            JScrollPane scrollPane = new JScrollPane(notesList);
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.gridheight = 6;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.BOTH;
            mainPanel.add(scrollPane, gbc);

            add(mainPanel);

        }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == getAllNotesButton) {
            // Handle "Get All Notes" button action
            updateNotesList();
        } else if (e.getSource() == getNoteByIdButton) {
            // Handle "Get Note by ID" button action
            String id = JOptionPane.showInputDialog(this, "Enter Note ID:");
            if (id != null && !id.isEmpty()) {
                int noteId = Integer.parseInt(id);
                Note note = notesService.getNotebyID(noteId);
                if (note != null) {
                    notesListModel.clear();
                    notesListModel.addElement(note.toString());
                } else {
                    JOptionPane.showMessageDialog(this, "Note not found.");
                }
            }
        } else if (e.getSource() == addNoteButton) {
            // Handle "Add Note" button action
            String title = JOptionPane.showInputDialog(this, "Enter Note Title:");
            String text = JOptionPane.showInputDialog(this, "Enter Note Text:");
            if (title != null && !title.isEmpty() && text != null && !text.isEmpty()) {
                notesService.addNote(title, text);
                updateNotesList();
            } else {
                JOptionPane.showMessageDialog(this, "Note title or text cannot be empty.");
            }
        } else if (e.getSource() == editNoteButton) {
            // Handle "Edit Note" button action
            String id = JOptionPane.showInputDialog(this, "Enter Note ID:");
            if (id != null && !id.isEmpty()) {
                int noteId = Integer.parseInt(id);
                Note existingNote = notesService.getNotebyID(noteId);
                if (existingNote != null) {
                    String title = JOptionPane.showInputDialog(this, "Enter Note Title:");
                    String text = JOptionPane.showInputDialog(this, "Enter Note Text:");
                    if (title != null && !title.isEmpty() && text != null && !text.isEmpty()) {
                        notesService.editNote(noteId, title, text);
                        updateNotesList();
                    } else {
                        JOptionPane.showMessageDialog(this, "Note title or text cannot be empty.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Note not found.");
                }
            }
        } else if (e.getSource() == deleteNoteButton) {
            // Handle "Delete Note" button action
            String id = JOptionPane.showInputDialog(this, "Enter Note ID:");
            if (id != null && !id.isEmpty()) {
                int noteId = Integer.parseInt(id);
                Note existingNote = notesService.getNotebyID(noteId);
                if (existingNote != null) {
                    notesService.deleteNote(noteId);
                    updateNotesList();
                } else {
                    JOptionPane.showMessageDialog(this, "Note not found.");
                }
            }
        }
        else if (e.getSource() == showNoteButton) {
            // Check if an item is selected in the notesList
            if (notesList.isSelectionEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select a note to view.");
            } else {
                // Extract the ID from the selected value in the notesList and trim any leading/trailing whitespace
                String selectedValue = notesList.getSelectedValue();
                String id = selectedValue.split(":")[1].split(",")[0].trim();

                int noteId = Integer.parseInt(id);
                Note note = notesService.getNotebyID(noteId);
                JOptionPane.showMessageDialog(this, note.getText());
            }
        }


    }

    private void updateNotesList() {

        // Update the notes list displayed in the GUI
        List<Note> notes = notesService.getNotes();

        notesListModel.clear();

        for (Note note : notes) {
            notesListModel.addElement(note.toString());
        }
    }
}
