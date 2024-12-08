import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TriangleButton extends JButton {
    private UndoManager undoManager;
    private View view;
    private JPanel drawingPanel;
    private Point p1, p2, p3;
    private int clickCount;

    public TriangleButton(UndoManager undoManager, View view, JPanel drawingPanel) {
        super("Draw Triangle");
        this.undoManager = undoManager;
        this.view = view;
        this.drawingPanel = drawingPanel;
        this.clickCount = 0;

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enableTriangleDrawing();
            }
        });
    }

    private void enableTriangleDrawing() {
        MouseAdapter triangleMouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseClick(e);
            }
        };
        drawingPanel.addMouseListener(triangleMouseAdapter);
    }

    private void handleMouseClick(MouseEvent e) {
        Point clickedPoint = e.getPoint();

        if (clickCount == 0) {
            p1 = clickedPoint;
            view.drawPoint(p1);
            clickCount++;
        } else if (clickCount == 1) {
            p2 = clickedPoint;
            view.drawLine(p1, p2);
            clickCount++;
        } else if (clickCount == 2) {
            p3 = clickedPoint;
            Triangle triangle = new Triangle(p1, p2, p3);
            Model model = View.getModel();
            model.addItem(triangle); // Add the triangle to the model
            view.drawTriangle(triangle);

            // Add the command to the UndoManager
            TriangleCommand command = new TriangleCommand(triangle, model);
            undoManager.executeCommand(command);

            // Reset for the next triangle
            clickCount = 0;
        }
    }
}
