import java.awt.*;

public class SwingUIContext implements UIContext {
    private Graphics graphics;

    public SwingUIContext(Graphics graphics) {
        this.graphics = graphics;
    }

    @Override
    public void drawLine(Point point1, Point point2) {
        graphics.drawLine(point1.x, point1.y, point2.x, point2.y);
    }

    @Override
    public void drawLabel(String text, Point location) {
        graphics.drawString(text, location.x, location.y);
    }

    @Override
    public void drawTriangle(Point p1, Point p2, Point p3) {
        graphics.drawLine(p1.x, p1.y, p2.x, p2.y);
        graphics.drawLine(p2.x, p2.y, p3.x, p3.y);
        graphics.drawLine(p3.x, p3.y, p1.x, p1.y);
    }

    @Override
    public void drawCircle(Point center, int radius) {
        graphics.drawOval(center.x - radius, center.y - radius, radius * 2, radius * 2);
    }
}
