import javax.swing.*;
import java.awt.*;
import java.util.*;

public class View extends JFrame {
    private UIContext uiContext;
    private DrawingPanel drawingPanel;
    private JPanel buttonPanel;
    private JButton triangleButton;
    private UndoManager undoManager;
    private Model model;
    private String fileName;

    // Singleton instance
    private static View instance;

    public static View getInstance() {
        return instance;
    }

    // Constructor
    public View() {
        super("Drawing Program");
        instance = this; // Assign singleton instance

        drawingPanel = new DrawingPanel();
        buttonPanel = new JPanel();

        Container contentPane = getContentPane();
        contentPane.add(buttonPanel, BorderLayout.NORTH);
        contentPane.add(drawingPanel, BorderLayout.CENTER);

        // Initialize and add buttons
        triangleButton = new TriangleButton(undoManager, this, drawingPanel);
        buttonPanel.add(triangleButton);

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Setters for UndoManager and Model
    public void setUndoManager(UndoManager manager) {
        this.undoManager = manager;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    // File name methods
    public void setFileName(String fileName) {
        this.fileName = fileName;
        setTitle("Drawing Program - " + fileName);
    }

    public String getFileName() {
        return fileName;
    }

    public Model getModel() {
        return model;
    }
    
    // Refreshes the drawing panel
    public void refresh() {
        drawingPanel.repaint();
    }

    // Draw methods
    public void drawPoint(Point p) {
        Graphics g = drawingPanel.getGraphics();
        g.fillOval(p.x - 3, p.y - 3, 6, 6);
    }

    public void drawLine(Point p1, Point p2) {
        Graphics g = drawingPanel.getGraphics();
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
    }

    public void drawTriangle(Triangle triangle) {
        Graphics g = drawingPanel.getGraphics();
        Point[] vertices = triangle.getVertices();
        g.drawLine(vertices[0].x, vertices[0].y, vertices[1].x, vertices[1].y);
        g.drawLine(vertices[1].x, vertices[1].y, vertices[2].x, vertices[2].y);
        g.drawLine(vertices[2].x, vertices[2].y, vertices[0].x, vertices[0].y);
    }

    // Maps points (placeholder for advanced mapping logic)
    public static Point mapPoint(Point point) {
        return point;
    }

    // Inner class for the drawing panel
    private class DrawingPanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (model != null) {
                Enumeration<Item> enumeration = model.getItems();
                while (enumeration.hasMoreElements()) {
                    ((Item) enumeration.nextElement()).render(uiContext);
                }
            }
        }
    }
}
