import java.util.*;

public class UndoManager {
    private static Model model;
    private Stack<Command> history; // Stores the history of executed commands
    private Stack<Command> redoStack; // Stores the redo stack
    private Command currentCommand; // Tracks the currently active command

    public UndoManager() {
        history = new Stack<>();
        redoStack = new Stack<>();
    }

    public static void setModel(Model model) {
        UndoManager.model = model;
    }

    public void beginCommand(Command command) {
        // Begin executing a new command
        if (currentCommand != null) {
            if (currentCommand.end()) {
                history.push(currentCommand); // Add completed command to history
            }
        }
        currentCommand = command;
        redoStack.clear(); // Clear the redo stack when a new command begins
        command.execute(); // Execute the command
    }

    public void endCommand(Command command) {
        // End the current command
        command.end();
        history.push(command); // Add the command to history
        currentCommand = null;
        model.setChanged(); // Notify the model that a change occurred
    }

    public void cancelCommand() {
        // Cancel the current command
        currentCommand = null;
        model.setChanged(); // Notify the model
    }

    public void undo() {
        // Undo the most recent command
        if (!history.empty()) {
            Command command = history.pop(); // Get the last command
            if (command.undo()) {
                redoStack.push(command); // Add it to the redo stack
            }
        }
    }

    public void redo() {
        // Redo the most recently undone command
        if (!redoStack.empty()) {
            Command command = redoStack.pop(); // Get the last undone command
            if (command.redo()) {
                history.push(command); // Add it back to the history
            }
        }
    }
}
