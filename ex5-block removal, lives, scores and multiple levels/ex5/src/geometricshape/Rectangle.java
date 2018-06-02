package geometricshape;

import java.util.ArrayList;
/**
 * class geometricshape.Rectangle - define rectangle with upperLeft point, width and height.
 * the class has some operation on the rectangle.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    /**
    * constructor - construct the rectangle.
    * @param upperLeft - the upperLeft point of the rectangle.
    * @param width - the width of the rectangle.
    * @param height - the height of the rectangle.
    */
    // Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }
    /**
     * construct a rectangle with location and width/height.
     * @param x - upperLeft x point.
     * @param y - upperLeft y point.
     * @param width - the width of the rectangle.
     * @param height - height of the rectangle.
     */
    public Rectangle(double x, double y, double width, double height) {
        this.upperLeft = new Point(x, y);
        this.width = width;
        this.height = height;
    }
    /**
     * the function checks if there are intersection points between the
     *  current line and the rectangle's lines.
     * @param line is line that we check the intersection with the rectangle.
     * @return a (possibly empty) list of intersection points with the specified line.
     */
    public java.util.List intersectionPoints(Line line) {

        ArrayList<Point> pointsList = new ArrayList<Point>();
        // create points of the rectangle.
        Line upperLine = buildLine(upperLeft, this.getUpperRight());
        Line rightLine = buildLine(this.getUpperRight(), this.getLowRight());
        Line lefttLine = buildLine(this.getLowLeft(), upperLeft);
        Line downtLine = buildLine(this.getLowLeft(), this.getLowRight());
        // checking the intersection between the points and the line.
        buildList(line, upperLine, pointsList);
        buildList(line, rightLine, pointsList);
        buildList(line, lefttLine, pointsList);
        buildList(line, downtLine, pointsList);

        return pointsList;
    }
    /**
     * the function calculate the lines of the rectangle.
     * @return list of intersection points.
     */
    public java.util.List linesRectangle() {
        ArrayList<Line> lineList = new ArrayList<Line>();

        Line upperLine = buildLine(upperLeft, this.getUpperRight());
        Line rightLine = buildLine(this.getUpperRight(), this.getLowRight());
        Line lefttLine = buildLine(this.getLowLeft(), upperLeft);
        Line downtLine = buildLine(this.getLowLeft(), this.getLowRight());
        // add line to the list.
        lineList.add(upperLine);
        lineList.add(downtLine);
        lineList.add(lefttLine);
        lineList.add(rightLine);

        return lineList;
    }

    /**
     * the function build a list of intersection points.
     * @param oneLine - one line to check intersection.
     * @param other - tow line to check intersection.
     * @param list - list of points intersection.
     */
    private void buildList(Line oneLine, Line other, ArrayList<Point> list) {
        Point checkIntersec = oneLine.intersectionWith(other);
        if (checkIntersec != null) {
            int i = 0;
            boolean bool = true;
            while (i < list.size()) {
                if (checkIntersec.equals(list.get(i))) {
                    bool = false;
                }
                i++;
            }
            if (bool) {
                list.add(checkIntersec);
            }
        }
    }

    /**
     * the function builds line.
     * @param pointOne - one point to start line.
     * @param pointTwo - another point to end line.
     * @return list of intersection points.
     */
    private Line buildLine(Point pointOne, Point pointTwo) {
        Line l1 = new Line(pointOne, pointTwo);
        return l1;
    }
    // Return the width and height of the rectangle
    /**
     * the function returns the rectangle's width.
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * the function returns the rectangle's height.
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }
    // Returns the upper-left point of the rectangle.
    /**
     * the function returns the upper left point of the rectangle.
     * @return the upper left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    /**
     * the function returns the upper right point of the rectangle.
     * @return the upper right point of the rectangle.
     */
    public Point getUpperRight() {
        Point upperRight = new Point(upperLeft.getX() + this.width, upperLeft.getY());
        return upperRight;
    }
    /**
     * the function returns the lower right point of the rectangle.
     * @return the lower right point of the rectangle.
     */
    public Point getLowRight() {
        Point lowRight = new Point(upperLeft.getX() + this.width, upperLeft.getY() + this.height);
        return lowRight;
    }
    /**
     * the function returns the lower left point of the rectangle.
     * @return the lower left point of the rectangle.
     */
    public Point getLowLeft() {
        Point lowLeft = new Point(upperLeft.getX(), upperLeft.getY() + this.height);
        return lowLeft;
    }
}