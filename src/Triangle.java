import java.util.List;

/**
 * Created by Arman on 5/9/2016.
 */
public class Triangle {

    // Coordinates for this triangle.
    private Point[] points;


    /**
     * Constructs a Triangle object from 3 Point objects.
     * @param a - Point 0
     * @param b - Point 1
     * @param c - Point 2
     */
    public Triangle(Point a, Point b, Point c) {
        this.points = new Point[]{a, b, c};
    }


    /**
     * pre - 0 <= i <= 2
     * @param i - Index of desired coordinate
     * @return - The coordinate corresponding to the ith point in this Triangle.
     */
    public Point getPoint(int i) {
        if (i < 0 || i > 2)
            return null;
        return points[i];
    }


    /**
     * @return The area of this Triangle.
     */
    public double area() {
        Point a = points[0];
        Point b = points[1];
        Point c = points[2];
        return Math.abs((a.getX() * (b.getY() - c.getY()) + b.getX() * (c.getY() - a.getY()) + c.getX() * (a.getY() - b.getY())) / 2);
    }


    /**
     * Determines if Point p is in this Triangle
     *
     * @param p - An x-y coordinate.
     * @return If p is in this Triangle.
     */
    public boolean contains(Point p) {
        Point p1 = points[0];
        Point p2 = points[1];
        Point p3 = points[2];
        float alpha = (float) (((p2.getY() - p3.getY()) * (p.getX() - p3.getX()) + (p3.getX() - p2.getX()) * (p.getY() - p3.getY())) /
                ((p2.getY() - p3.getY()) * (p1.getX() - p3.getX()) + (p3.getX() - p2.getX()) * (p1.getY() - p3.getY())));
        float beta = (float) (((p3.getY() - p1.getY()) * (p.getX() - p3.getX()) + (p1.getX() - p3.getX()) * (p.getY() - p3.getY())) /
                ((p2.getY() - p3.getY()) * (p1.getX() - p3.getX()) + (p3.getX() - p2.getX()) * (p1.getY() - p3.getY())));
        float gamma = 1.0f - alpha - beta;
        return alpha > 0 && beta > 0 && gamma > 0;
    }
}
