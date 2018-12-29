import java.util.ArrayList;
import java.util.Random;

/**
 * http://www.cs.utexas.edu/~arman/
 *
 * A Monte Carlo simulation of my research paper "Triangle Inscribed-Triangle Picking".
 * This program was originally created in 2016 when I began research. Currently the results of this
 * simulation serve as an experimental verification of the mathematically proven results found in the paper.
 * <p>
 * https://arxiv.org/pdf/1804.11007.pdf
 * <p>
 *     
 * Created by Arman on 10/16/2016.
 */
public class Main {

    private Triangle baseTri;
    private double baseArea;
    private double totalArea;
    private long ITERATIONS = 1000000;

    public static void main(String args[]) {
        Main main = new Main();
        main.initialize();
        main.iterateTriangle(main.ITERATIONS, 1);
    }

    /**
     * Initialize base triangle to pick from.
     */
    public void initialize() {
        baseTri = new Triangle(new Point(9, 2), new Point(10, 45), new Point(30, 8));
        baseArea = baseTri.area();
    }

    /**
     * Randomly generates triangles inscribed in baseTri (1 vertex per edge). Accumulates the areas of these inscribed
     * triangles to determine the n-th moment of the area of randomly picked inscribed triangle.
     *
     * @param iterationsToDo - The sample size for Monte Carlo simulation.
     * @param moment         - The n-th moment to compute.
     */
    public void iterateTriangle(long iterationsToDo, int moment) {
        Random rand = new Random();

        double total = 0;
        long start = System.currentTimeMillis();

        // Create vectors for sides of baseTri:
        double BminusA_X = baseTri.getPoint(1).getX() - baseTri.getPoint(0).getX();
        double BminusA_Y = baseTri.getPoint(1).getY() - baseTri.getPoint(0).getY();

        double CminusB_X = baseTri.getPoint(2).getX() - baseTri.getPoint(1).getX();
        double CminusB_Y = baseTri.getPoint(2).getY() - baseTri.getPoint(1).getY();

        double AminusC_X = baseTri.getPoint(0).getX() - baseTri.getPoint(2).getX();
        double AminusC_Y = baseTri.getPoint(0).getY() - baseTri.getPoint(2).getY();

        // Uniformly and independently generate random points on 3 vectors.
        // accumulate the area of triangle created by 3 points:
        for (long i = 0; i < iterationsToDo; i++) {
            double r1 = rand.nextDouble();
            double r2 = rand.nextDouble();
            double r3 = rand.nextDouble();

            double xC0 = baseTri.getPoint(0).getX() + (r1 * (BminusA_X));
            double yC0 = baseTri.getPoint(0).getY() + (r1 * (BminusA_Y));
            Point C0 = new Point(xC0, yC0);

            double xA0 = baseTri.getPoint(1).getX() + (r2 * (CminusB_X));
            double yA0 = baseTri.getPoint(1).getY() + (r2 * (CminusB_Y));
            Point A0 = new Point(xA0, yA0);

            double xB0 = baseTri.getPoint(2).getX() + (r3 * (AminusC_X));
            double yB0 = baseTri.getPoint(2).getY() + (r3 * (AminusC_Y));
            Point B0 = new Point(xB0, yB0);

            //--------------

            double area000 = new Triangle(C0, A0, B0).area();

            total += (Math.pow(area000, moment));
        }

        //double val = (total / iterationsToDo) / (Math.pow(baseTri.area(), moment));
        //System.out.println("Difference: " + (.25 - val));
        //System.out.println("Log: " + Math.log(Math.abs(.25 - val)));
        double avgAreaOfInscribedTriangle = (total / iterationsToDo) / (Math.pow(baseTri.area(), moment));
        System.out.println("AREA: " + avgAreaOfInscribedTriangle);
        System.out.println("Elapsed time in seconds: " + (System.currentTimeMillis() - start) / 1000.0);
        //System.out.println("Average MC : " + averageMC / iterationsToDo);
    }

    // ...
    // Random experiments:
    public void iterate() {
        Random rand = new Random();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1; i++) {
            double r1 = rand.nextDouble();
            double r2 = rand.nextDouble();
            double r3 = rand.nextDouble();
            double i1 = 1 - r1;
            double i2 = 1 - r2;
            double i3 = 1 - r3;

            double xC0 = baseTri.getPoint(0).getX() + (r1 * (baseTri.getPoint(1).getX() - baseTri.getPoint(0).getX()));
            double yC0 = baseTri.getPoint(0).getY() + (r1 * (baseTri.getPoint(1).getY() - baseTri.getPoint(0).getY()));
            Point C0 = new Point(xC0, yC0);

            double xA0 = baseTri.getPoint(1).getX() + (r2 * (baseTri.getPoint(2).getX() - baseTri.getPoint(1).getX()));
            double yA0 = baseTri.getPoint(1).getY() + (r2 * (baseTri.getPoint(2).getY() - baseTri.getPoint(1).getY()));
            Point A0 = new Point(xA0, yA0);

            double xB0 = baseTri.getPoint(2).getX() + (r3 * (baseTri.getPoint(0).getX() - baseTri.getPoint(2).getX()));
            double yB0 = baseTri.getPoint(2).getY() + (r3 * (baseTri.getPoint(0).getY() - baseTri.getPoint(2).getY()));
            Point B0 = new Point(xB0, yB0);

            //--------------

            double xC1 = baseTri.getPoint(0).getX() + (i1 * (baseTri.getPoint(1).getX() - baseTri.getPoint(0).getX()));
            double yC1 = baseTri.getPoint(0).getY() + (i1 * (baseTri.getPoint(1).getY() - baseTri.getPoint(0).getY()));
            Point C1 = new Point(xC1, yC1);

            double xA1 = baseTri.getPoint(1).getX() + (i2 * (baseTri.getPoint(2).getX() - baseTri.getPoint(1).getX()));
            double yA1 = baseTri.getPoint(1).getY() + (i2 * (baseTri.getPoint(2).getY() - baseTri.getPoint(1).getY()));
            Point A1 = new Point(xA1, yA1);

            double xB1 = baseTri.getPoint(2).getX() + (i3 * (baseTri.getPoint(0).getX() - baseTri.getPoint(2).getX()));
            double yB1 = baseTri.getPoint(2).getY() + (i3 * (baseTri.getPoint(0).getY() - baseTri.getPoint(2).getY()));
            Point B1 = new Point(xB1, yB1);

            double area000 = new Triangle(C0, A0, B0).area();
            double area001 = new Triangle(C0, A0, B1).area();
            double area010 = new Triangle(C0, A1, B0).area();
            double area011 = new Triangle(C0, A1, B1).area();
            double area100 = new Triangle(C1, A0, B0).area();
            double area101 = new Triangle(C1, A0, B1).area();
            double area110 = new Triangle(C1, A1, B0).area();
            double area111 = new Triangle(C1, A1, B1).area();

            double totalArea = area000 + area001 + area010 + area011 + area100 + area101 + area110 + area111;
            System.out.println((totalArea / 8.0) / baseArea);
        }
        System.out.println("Area: " + (totalArea / ITERATIONS) / baseArea);
        System.out.println("Elapsed time: " + (System.currentTimeMillis() - start) / 1000.0);
    }

    public void iterate3Squares() {
        Random rand = new Random();
        ArrayList<Double> areas = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            Point p1 = new Point(rand.nextDouble(), rand.nextDouble());
            Point p2 = new Point(rand.nextDouble(), rand.nextDouble() + 5);
            Point p3 = new Point(rand.nextDouble() + 5, rand.nextDouble());

            Triangle tri = new Triangle(p1, p2, p3);
            areas.add(Math.pow(tri.area(), 2));
        }
        double areasTotal = 0;
        for (int i = 0; i < areas.size(); i++) {
            areasTotal += areas.get(i);
        }
        System.out.println((areasTotal / areas.size()) / Math.pow(12.5, 2));
    }
}
