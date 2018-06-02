/**
 * class Point - the class define point with x, y values and functions on point.
 */
 public class Point {
     private double x;
     private double y;
    /**
     * contractor - a point with x and y values.
     * @param x -  x coordinate.
     * @param y -  y coordinate.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * The function  calculate the distance of this point to the other point.
     * @param other - other point.
     * @return the distance of this point to the other point.
     */
    public double distance(Point other) {
        // check if exist point.
        if (other == null) {
            return 1;
        }
        double distanceX = other.getX() - this.x;
        double distanceY = other.getY() - this.y;
        // make the number positive.
        distanceX = Math.abs(distanceX);
        distanceY = Math.abs(distanceY);
        // calculate the distance between the points.
        return Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
    }
    /**
     * The function check if the points are equals.
     * @param other - other point.
     * @return - return true is the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        if (other.getX() != this.x) {
            return false;
        }
        if (other.getY() != this.y) {
            return false;
        }
            return true;
    }
    /**
     * the function return x value of the point.
     * @return the x value of this point.
     */
    public double getX() {
        return this.x;
    }
    /**
     * the function return y value of the point.
     * @return the y value of this point.
     */
    public double getY() {
        return this.y;
    }
    public void setX(double x) {
        this.x =x ;
     }
    public void setY(double y) {
       this.y =y ;
    }
    
}