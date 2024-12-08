import javax.swing.*;
import java.awt.event.*;

public class SaveButton extends JButton implements ActionListener {
    private View view;
    private UndoManager undoManager;

    public SaveButton(UndoManager undoManager, View view) {
        super("Save");
        this.undoManager = undoManager;
        this.view = view;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String fileName = view.getFileName();

        if (fileName == null || fileName.trim().isEmpty()) {
            fileName = JOptionPane.showInputDialog(view, "Please specify file name:");
            
            if (fileName == null || fileName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(view, "Invalid file name. Operation canceled.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Exit if no valid file name is provided
            }

            view.setFileName(fileName);
        }

        SaveCommand command = new SaveCommand(fileName);
        undoManager.executeCommand(command);
    }
}
