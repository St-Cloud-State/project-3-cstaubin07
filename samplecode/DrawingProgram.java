public class DrawingProgram {
  public static void main(String[] args) {
      // Create instances of Model and UndoManager
      Model model = new Model();
      UndoManager undoManager = new UndoManager();

      // Set up relationships between components
      undoManager.setModel(model); // Link UndoManager to Model
      View view = new View(); // Create the View instance
      view.setUndoManager(undoManager); // Link UndoManager to View
      view.setModel(model); // Link Model to View
      model.setView(view); // Link View to Model (optional)

      // Set shared resources for Command
      Command.setUndoManager(undoManager);
      Command.setModel(model);

      // Show the GUI
      view.setVisible(true);
  }
}
