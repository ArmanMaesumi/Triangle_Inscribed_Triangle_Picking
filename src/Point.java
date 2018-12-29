import java.util.List;

/**
 * Created by Arman on 5/7/2016.
 */
public class Point {

    private double x;

    private double y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }
    public Point(Point p){
        this.x = p.getX();
        this.y = p.getY();
    }

    public double simpleDis(Point p){
        return (Math.pow(getX() - p.getX(),2)+Math.pow(getY()-p.getY(),2));
    }

    public double distance(Point p){
        return (Math.sqrt(
                Math.pow(getX()-p.getX(),2)
                        +Math.pow(getY()-p.getY(),2)
        ));
    }

    public boolean contains(List<Point> p){
        for(Point c : p){
            if (c.getX() == x && c.getY() == y){
                return true;
            }
        }
        return false;
    }

    public boolean equals(Point p){
        return x == p.getX() && y == p.getY();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void set(double x, double y){
        this.x = x;
        this.y = y;
    }

    public String toString(){
        return "("+x+", "+y+")";
    }
}
