# Note-Java    
This is a simple Java application that allows you to manage notes using a graphical user interface (GUI). The application provides basic functionality such as retrieving all notes, retrieving a note by its ID, adding a new note, editing an existing note, and deleting a note. The GUI is built using the Swing library.      
  
# Dependencies      
- MySQL (version 8.0.27)      
- SDK: OpenJDK version 17.0.2        
       
# Installation and Usage   

Clone or download the project files.   

Open the project in your preferred Java IDE.    

Build and run the Main class located in the main package.  

The GUI will appear, showing a list of notes on the right side.   

Use the buttons on the left side of the GUI to perform various actions: 

**Get All Notes**: Click this button to retrieve all notes from the database and display them in the list.    

**Get Note by ID**: Enter a note ID and click this button to retrieve a specific note by its ID and display it in the list.     

**Add Note**: Enter a title and text for a new note, then click this button to add the note to the database and update the list.  

**Edit Note**: Enter a note ID, a new title, and new text for an existing note, then click this button to update the note in the database and update the list.    
**Delete Note**: Enter a note ID, then click this button to delete the note from the database and update the list.    

**Show Note**: Select a note from the list, then click this button to display the full text of the selected note in a message dialog.    
    
# Database Configuration   
This application uses a MySQL database to store the notes. Make sure you have a MySQL server running and adjust the database connection URL in the NoteImpl class. Also, update the username and password if necessary. 

Open the `notes_db_v1` file provided in the resources directory.      

Execute the SQL script in the `notes_db_v1` file to create the necessary shema and table for the application.     
You can do this by either copying and running each query in MySQL Workbench or using the "Open SQL Script"       
option in MySQL Workbench and selecting the `notes_db_v1` file.        
  
