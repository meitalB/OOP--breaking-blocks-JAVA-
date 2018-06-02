import java.util.ArrayList;
import java.util.List;
/**
 * Line. The program define Line.
 */
public class Line {
    private Point start;
    private Point end;
    // constructors
    /**
     * Construct a line from start and end values.
     * @param start is the line start value.
     * @param end is the line end value.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
    /**
     * Construct a line from x1,x2,y1,y2 values.
     * @param x1 is the x value of the line start.
     * @param y1 is the y value of the line start.
     * @param x2 is the x value of the line end.
     * @param y2 is the y value of the line end.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }
    /**
     * The method calculate the length of the line.
     * @return the length of the line.
     */
    public double length() {
        return this.start.distance(this.end);
    }
    /**
     * The method calculate the middle point of the line.
     * @return the middle point of the line.
     */
    public Point middle() {
        if (start.equals(end)) {
            return start;
        }
        Point midd = new Point(((end.getX() + start.getX()) / 2),
                ((end.getY() + start.getY()) / 2));
        return midd;
    }
    /**
     * the method return the start point of the line.
     * @return the start point of the line.
     */
    public Point start() {
        return this.start;
    }
    /**
     * the method return the end point of the line.
     * @return the end point of the line.
     */
    public Point end() {
        return this.end;
    }
    /**
     * The method check if the lines are intersect.
     * @param other is a line.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        if (this.intersectionWith(other) == null) {
            return false;
        }
        return true;
    }
    /**
     * The function check if the lines are equals.
     * @param other is line.
     * @return true is the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        if ((this.start.equals(other.start)) && (this.end.equals(other.end))) {
            return true;
        }
        return false;
    }
    /**
     * the function return intersection point if the lines intersect.
     * @param other is line.
     * @return Returns the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        double line1A, otherA, line1B, otherB, line1C, otherC, det;
        //sets the distance between the points.
        line1A = this.end.getY() - this.start.getY();
        line1B = this.start.getX() - this.end.getX();
        //multiple the x, y parameters of point with those distance values.
        line1C = line1A * this.start.getX() + line1B * this.start.getY();
        //sets the distance between the points.
        otherA = other.end.getY() - other.start.getY();
        otherB = other.start.getX() - other.end.getX();
        //multiple the x, y parameters of point with those distance values.
        otherC = otherA * other.start.getX() + otherB * other.start.getY();
        det = line1A * otherB - otherA * line1B;
                if (det == 0) {
                    //Lines are parallel.
                    return null;
                    //there is an intersection. calculate it.
                } else {
                    double x = (otherB * line1C - line1B * otherC) / det;
                    double y = (line1A * otherC - otherA * line1C) / det;
                    double line1MaxX, line1MaxY, line1MinY, line1MinX;
                    double otherMaxX, otherMaxY, otherMinX, otherMinY;
                    //check if the x of  intersection point is in the surface.
                    if (this.start.getX() > this.end.getX()) {
                        line1MaxX = this.start.getX();
                        line1MinX = this.end.getX();
                    } else {
                        line1MinX = this.start.getX();
                        line1MaxX = this.end.getX();
                    }
                    if (other.start.getX() > other.end.getX()) {
                        otherMaxX = other.start.getX();
                        otherMinX = other.end.getX();
                    } else {
                        otherMinX = other.start.getX();
                        otherMaxX = other.end.getX();
                    }
                    //check if the y of intersection point is in the surface.
                    if (this.start.getY() > this.end.getY()) {
                        line1MaxY = this.start.getY();
                        line1MinY = this.end.getY();
                    } else {
                        line1MinY = this.start.getY();
                        line1MaxY = this.end.getY();
                    }
                    if (other.start.getY() > other.end.getY()) {
                        otherMaxY = other.start.getY();
                        otherMinY = other.end.getY();
                    } else {
                        otherMinY = other.start.getY();
                        otherMaxY = other.end.getY();
                    }
                    //check if in the boundary of lines
                    if ((checkIntersection(x, line1MinX, line1MaxX))
                            && (checkIntersection(x, otherMinX, otherMaxX))
                            && (checkIntersection(y, line1MinY, line1MaxY))
                            && (checkIntersection(y, otherMinY, otherMaxY))) {
                        return new Point(x, y);
                    } else {
                        return null;
                        }
        }
    }
    /**
     * the function  checks if the value intersection in lines.
     * @param value is the parameter to be check.
     * @param min is the mininum value.
     * @param max is the maximum value.
     * @return true if the given value in the boundary, else return false.
     */
    public boolean checkIntersection(double value, double min, double max) {
        if (min == max) {
            return true;
            }
        if (value >= min && value <= max) {
            return true;
            }
        return false;
    }
    /**
     * the function  checks if point is in lines.
     * @param p the point we receive.
     * @return true if the given point in the line, else return false.
     */
    public boolean isPointInLine(Point p) {
        if ((p.distance(start) + p.distance(end) - start.distance(end)) < 0.0000001) {
            return true;
        }
            return false;
    }
    /**
     * the function  checks if If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
     * @param rect the rectangle we receive.
     * @return the closest intersection point' if there is no return null.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        int indexOfMinPoint = 0;
        double minDistance = this.end().distance(this.start());
        double tempDistance = 0;
        List<Point> listOfPoints = new ArrayList<Point>();
        listOfPoints = rect.intersectionPoints(this);
        if (listOfPoints.size() != 0) {
            for (int i = 0; i < listOfPoints.size(); i++) {
                tempDistance = listOfPoints.get(i).distance(this.start);
                if (tempDistance < minDistance) {
                    minDistance = tempDistance;
                    indexOfMinPoint = i;
                }
            }
            return listOfPoints.get(indexOfMinPoint);
        } else {
            return null;
        }
    }
}