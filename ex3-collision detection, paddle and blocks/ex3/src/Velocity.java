import java.util.ArrayList;
import java.util.List;

/**
 * class Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;
    /**
     * Constructor - construct velocity with x and y values.
     * @param dx - x parameter.
     * @param dy - y parameter.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    public Velocity(Point pointOther) {
        this.dx = pointOther.getX();
        this.dy = pointOther.getY();
    }
     /**
      * The function take a point with position (x,y) and return a new point
      * with position (x+dx, y+dy).
      * @param p - add from other point dx dy values to this velocity.
      * @return a new point with a new value of point p "+" dx and dy values.
      */
    public Point applyToPoint(Point p) {
        Point newP = new Point(p.getX() + this.dx, p.getY() + this.dy);
        return newP;
    }
    public Point almostApplyToPoint(Point p, CollisionInfo collInfo) {
         Point newP = null;
         List<Line> linesRect = new ArrayList<Line>();
         linesRect = collInfo.collisionObject().getCollisionRectangle().linesRectangle();
         if (linesRect.get(0).isPointInLine(p)) {
             System.out.println("top line");
             newP = new Point(p.getX() , p.getY() -1);
         } else if (linesRect.get(1).isPointInLine(p)) {
             System.out.println("bottom line");
           
             newP = new Point(p.getX() , p.getY() +1);
         } else if (linesRect.get(2).isPointInLine(p)) {
             System.out.println("left line");
             newP = new Point(p.getX() -1, p.getY());
         } else if (linesRect.get(3).isPointInLine(p)) {
             System.out.println("right line");
             newP = new Point(p.getX() +1, p.getY());
         }

        return newP;
    }
    /**
     * the function return x value of this point.
     * @return x value of this point.
     */
    public double getDX() {
        return this.dx;
    }
    /**
     * the function return y value of the point.
     * @return y value of the point.
     */
    public double getDY() {
        return this.dy;
    }
    /**
     * The function define a new velocity according to an angel and speed
     * values.
     * @param angle is the angle that changes to radian.
     * @param speed is the new speed of the velocity.
     * @return a new velocity according to the new speed and specific radian.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double rad = Math.toRadians(angle);
        double dx = speed * Math.sin(rad);
        double dy = -1 * speed * Math.cos(rad);
        return new Velocity(dx, dy);
    }
}