import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class View extends JFrame {
    private UIContext uiContext;
    private JPanel drawingPanel;
    private JPanel buttonPanel;
    private JButton triangleButton; // New button for triangles
    private static UndoManager undoManager;
    private static Model model;

    public static void setModel(Model model) {
        View.model = model;
    }

    public static Model getModel() {
        return model;
    }

    public View() {
        super("Drawing Program");
        drawingPanel = new DrawingPanel();
        buttonPanel = new JPanel();
        Container contentpane = getContentPane();
        contentpane.add(buttonPanel, "North");
        contentpane.add(drawingPanel);

        triangleButton = new TriangleButton(undoManager, this, drawingPanel);
        buttonPanel.add(triangleButton);

        this.setSize(600, 400);
    }

    public void refresh() {
        drawingPanel.repaint();
    }

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

    private class DrawingPanel extends JPanel {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Enumeration enumeration = model.getItems();
            while (enumeration.hasMoreElements()) {
                ((Item) enumeration.nextElement()).render(uiContext);
            }
        }
    }
}
