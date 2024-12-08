import javax.swing.*;
import java.awt.event.*;

public class OpenButton extends JButton implements ActionListener {
    private View view;
    private UndoManager undoManager;

    public OpenButton(UndoManager undoManager, View view) {
        super("Open");
        this.undoManager = undoManager;
        this.view = view;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String fileName = JOptionPane.showInputDialog(view, "Please type new file name:");
        
        if (fileName == null || fileName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Invalid file name. Operation canceled.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit if no valid file name is provided
        }

        view.setFileName(fileName);
        OpenCommand command = new OpenCommand(fileName);
        undoManager.executeCommand(command);
    }
}
