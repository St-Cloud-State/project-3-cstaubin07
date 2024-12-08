import java.util.*;

public class UndoManager {
    private static Model model;
    private Stack<Command> history;
    private Stack<Command> redoStack;

    public UndoManager() {
        history = new Stack<>();
        redoStack = new Stack<>();
    }

    public static void setModel(Model model) {
        UndoManager.model = model;
    }

    public void executeCommand(Command command) {
        command.execute();
        history.push(command);
        redoStack.clear();
    }

    public void undo() {
        if (!history.empty()) {
            Command command = history.pop();
            if (command.undo()) {
                redoStack.push(command);
            }
        }
    }

    public void redo() {
        if (!redoStack.empty()) {
            Command command = redoStack.pop();
            if (command.redo()) {
                history.push(command);
            }
        }
    }
}
