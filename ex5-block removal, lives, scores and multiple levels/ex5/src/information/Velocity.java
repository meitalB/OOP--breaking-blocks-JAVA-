package information;
import geometricshape.Line;
import geometricshape.Point;
import java.util.ArrayList;
import java.util.List;
/**
 * class information.Velocity specifies the change in position on the `x` and the `y` axes.
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
    /**
     * Constructor - construct velocity with point.
     * @param pointOther - point with x and y values.
     */
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
    /**
     * The function take a point with position (x,y) and return a new point
     * with very close position.
     * @param p - add from other point dx dy values to this velocity.
     * @param collInfo - getting the object collision info.
     * @return a new point with a new value.
     */
    public Point almostApplyToPoint(Point p, CollisionInfo collInfo) {
         Point newP = null;
         List<Line> linesRect = new ArrayList<Line>();
         linesRect = collInfo.collisionObject().getCollisionRectangle().linesRectangle();
         // upper line of the rectangle.
         if (linesRect.get(0).isPointInLine(p)) {
             newP = new Point(p.getX() , p.getY() - 1);
             // lower line of the rectangle.
         } else if (linesRect.get(1).isPointInLine(p)) {
             newP = new Point(p.getX() , p.getY() + 1);
             // left line of the rectangle.
         } else if (linesRect.get(2).isPointInLine(p)) {
             newP = new Point(p.getX() - 1, p.getY());
             // right line of the rectangle.
         } else if (linesRect.get(3).isPointInLine(p)) {
             newP = new Point(p.getX() + 1, p.getY());
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