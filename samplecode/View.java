import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class View extends JFrame {
    private UIContext uiContext;
    private JPanel drawingPanel;
    private JPanel buttonPanel;
    private JButton triangleButton; // Button for triangles
    private UndoManager undoManager;
    private Model model;

    // Setters for UndoManager and Model
    public void setUndoManager(UndoManager manager) {
        this.undoManager = manager;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    // Constructor
    public View() {
        super("Drawing Program");
        drawingPanel = new DrawingPanel();
        buttonPanel = new JPanel();
        Container contentpane = getContentPane();
        contentpane.add(buttonPanel, "North");
        contentpane.add(drawingPanel);

        // Initialize and add the triangle button
        triangleButton = new TriangleButton(undoManager, this, drawingPanel);
        buttonPanel.add(triangleButton);

        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Refreshes the drawing panel
    public void refresh() {
        drawingPanel.repaint();
    }

    // Draws a single point
    public void drawPoint(Point p) {
        Graphics g = drawingPanel.getGraphics();
        g.fillOval(p.x - 3, p.y - 3, 6, 6);
    }

    // Draws a line between two points
    public void drawLine(Point p1, Point p2) {
        Graphics g = drawingPanel.getGraphics();
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
    }

    // Draws a triangle
    public void drawTriangle(Triangle triangle) {
        Graphics g = drawingPanel.getGraphics();
        Point[] vertices = triangle.getVertices();
        g.drawLine(vertices[0].x, vertices[0].y, vertices[1].x, vertices[1].y);
        g.drawLine(vertices[1].x, vertices[1].y, vertices[2].x, vertices[2].y);
        g.drawLine(vertices[2].x, vertices[2].y, vertices[0].x, vertices[0].y);
    }

    // Maps points (basic passthrough, can be expanded if needed)
    public static Point mapPoint(Point point) {
        return point;
    }

    // Inner class for the drawing panel
    private class DrawingPanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Enumeration<Item> enumeration = model.getItems();
            while (enumeration.hasMoreElements()) {
                ((Item) enumeration.nextElement()).render(uiContext);
            }
        }
    }
}
