import java.util.ArrayList;


public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    
 // Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }
    // Return a (possibly empty) List of intersection points
    // with the specified line.
    public java.util.List intersectionPoints(Line line) {
        
        ArrayList<Point> pointsList = new ArrayList<Point>();

        Line upperLine = buildLine(upperLeft, this.getUpperRight());
        Line rightLine = buildLine(this.getUpperRight(), this.getLowRight());
        Line lefttLine = buildLine(this.getLowLeft(), this.getLowRight());
        Line downtLine = buildLine(upperLeft, this.getLowLeft());
        
        buildList(upperLine, line, pointsList);
        buildList(rightLine, line, pointsList);
        buildList(lefttLine, line, pointsList);
        buildList(downtLine, line, pointsList);
        if (pointsList.size() == 0) {
        	System.out.println("null!!!");
        }
        return pointsList;
        
    }
    public java.util.List linesRectangle() {
        
        ArrayList<Line> lineList = new ArrayList<Line>();

        Line upperLine = buildLine(upperLeft, this.getUpperRight());
        Line rightLine = buildLine(this.getUpperRight(), this.getLowRight());
        Line lefttLine = buildLine(this.getLowLeft(), this.getLowRight());
        Line downtLine = buildLine(upperLeft, this.getLowLeft());
        
        lineList.add(upperLine);
        lineList.add(downtLine);
        lineList.add(lefttLine);
        lineList.add(rightLine);

        return lineList;
    }
    private void buildList(Line oneLine, Line other, ArrayList<Point> list) {
        Point checkIntersec = oneLine.intersectionWith(other);
        if (checkIntersec != null) {
            list.add(checkIntersec);
        }
    }

    private Line buildLine(Point pointOne, Point pointTwo) {
       Line l1 = new Line(pointOne, pointTwo);
       return l1;
    }
    // Return the width and height of the rectangle
    public double getWidth() {
        return this.width;
    }
    public double getHeight() {
     return this.height;
    }

    // Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    public Point getUpperRight() {
        Point upperRight = new Point(upperLeft.getX() + this.width, upperLeft.getY());
        return upperRight;
    }
    public Point getLowRight() {
        Point lowRight = new Point(upperLeft.getX() + this.width, upperLeft.getY() + this.height);
        return lowRight;
    }
    public Point getLowLeft() {
        Point lowLeft = new Point(upperLeft.getX(), upperLeft.getY() + this.height);
        return lowLeft;
    }
}
