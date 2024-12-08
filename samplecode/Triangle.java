import java.awt.*;

public class Triangle extends Item {
    private Point p1, p2, p3;

    public Triangle(Point p1, Point p2, Point p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public Point[] getVertices() {
        return new Point[]{p1, p2, p3};
    }

    @Override
    public void render(UIContext context) {
        Graphics g = context.getGraphics();
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
        g.drawLine(p2.x, p2.y, p3.x, p3.y);
        g.drawLine(p3.x, p3.y, p1.x, p1.y);
    }
    
    @Override
    public boolean includes(Point point) {
    // Basic inclusion check logic (placeholder, refine as needed)
        return false;
    }


    @Override
    public void translate(int dx, int dy) {
        p1.translate(dx, dy);
        p2.translate(dx, dy);
        p3.translate(dx, dy);
    }
    
}
