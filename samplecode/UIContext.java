import java.awt.*;

public interface UIContext {
    void drawLine(Point point1, Point point2);
    void drawLabel(String text, Point location);
    void drawTriangle(Point p1, Point p2, Point p3);
    void drawCircle(Point center, int radius);
}
