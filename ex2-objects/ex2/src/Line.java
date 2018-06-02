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
	 * 
	 * @param start
	 *            is the line start value.
	 * @param end
	 *            is the line end value.
	 */
	public Line(Point start, Point end) {
		this.start = start;
		this.end = end;
	}

	/**
	 * Construct a line from x1,x2,y1,y2 values.
	 * 
	 * @param x1
	 *            is the x value of the line start.
	 * @param y1
	 *            is the y value of the line start.
	 * @param x2
	 *            is the x value of the line end.
	 * @param y2
	 *            is the y value of the line end.
	 */
	public Line(double x1, double y1, double x2, double y2) {
		this.start = new Point(x1, y1);
		this.end = new Point(x2, y2);
	}

	/**
	 * The method calculate the length of the line.
	 * 
	 * @return the length of the line.
	 */
	public double length() {
		return this.start.distance(this.end);
	}

	/**
	 * The method calculate the middle point of the line.
	 * 
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
	 * 
	 * @return the start point of the line.
	 */
	public Point start() {
		return this.start;
	}

	/**
	 * the method return the end point of the line.
	 * 
	 * @return the end point of the line.
	 */
	public Point end() {
		return this.end;
	}

	/**
	 * The method check if the lines are intersect.
	 * 
	 * @param other
	 *            is a line.
	 * @return true if the lines intersect, false otherwise.
	 */
	public boolean isIntersecting(Line other) {
		if (this.intersectionWith(other) == null) {
			return false;
		}
		return true;
	}

	/**
	 * The method check if the lines are parallel.
	 * 
	 * @param lineOne
	 *            is a line.
	 * @param lineTwo
	 *            is a line.
	 * @return true if the lines intersect, false otherwise.
	 */
	private boolean isParallel(Line lineOne, Line lineTwo) {
		if ((slope(lineOne)) == (slope(lineTwo))) {
			return true;
		}
		return false;
	}

	/**
	 * The method check the slope of the line.
	 * 
	 * @param l
	 *            is the line we check.
	 * @return the number of the slope.
	 */
	private double slope(Line l) {
		return ((l.end.getY() - l.start.getY()) / (l.end.getX() - l.start
				.getX()));
	}

	/**
	 * The function check if the lines are equals.
	 * 
	 * @param other
	 *            is line.
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
	 * 
	 * @param other
	 *            is line.
	 * @return Returns the intersection point if the lines intersect, and null
	 *         otherwise.
	 */
	public Point intersectionWith(Line other) {
		if (!isParallel(this, other)) {
			
			Point p;
			double mLine1 = this.slope(this);
			double mLine2 = other.slope(other);
			double bLine1 = findB(this, mLine1);
			double bLine2 = findB(other, mLine2);
			double maxThis, minThis, maxOther, minOther;
			if (this.start.getX() == this.end.getX()) {
				p= other.pointForvertical(this);
			}
			else if(other.start.getX() == other.start.getX()) {
				p= this.pointForvertical(other);
			} else {	
			double pointX = calculateX(bLine1, bLine2, mLine1, mLine2);
			double pointY = calculateY(pointX, bLine1, mLine1);
			p = new Point(pointX, pointY);
			}
			if(p==null) {
				return null;
			}
			if (this.start.getX() > this.end.getX()) {
				maxThis = this.start.getX();
				minThis = this.end.getX();
			} else {
				maxThis = this.end.getX();
				minThis = this.start.getX();
			}
			if (other.start.getX() > other.end.getX()) {
				maxOther = other.start.getX();
				minOther = other.end.getX();
			} else {
				maxOther = other.end.getX();
				minOther = other.start.getX();
			}
			if (p.getX() >= minThis && p.getX() <= maxThis
					&& p.getX() >= minOther && p.getX() <= maxOther) {
				return p;
			}
		}
		return null;
	}

	private Point pointForvertical(Line lineOne) {
		Point p;
		if ((this.start.getX() <= lineOne.start.getX())&& (lineOne.start.getX() <= this.end.getX())) {
			double pointX=lineOne.start.getX();
			double pointY = this.start.getY();
			p = new Point (pointX,pointY );
			
			return p;
				}
			return null;
		}
	/**
	 * the function return number b of y=mx+b equation.
	 * 
	 * @param lineOne
	 *            is line.
	 * @param m
	 *            is the slope.
	 * @return Returns the number of b.
	 */
	private double findB(Line lineOne, double m) {
		return (lineOne.start.getX() * (-m)) + lineOne.start.getY();
	}

	/**
	 * the function return number x of y=mx+b equation.
	 * 
	 * @param b1
	 *            is the b of first equation.
	 * @param b2
	 *            is the b of second equation.
	 * @param m1
	 *            is the slope of first equation.
	 * @param m2
	 *            is the slope of second equation.
	 * @return Returns the number of b.
	 */
	private double calculateX(double b1, double b2, double m1, double m2) {
			return (b1 - b2) / (m2 - m1);
	}

	/**
	 * the function return number y of point equation.
	 * 
	 * @param x
	 *            is the x of the point equation.
	 * @param b
	 *            is the b of equation y = mx+b.
	 * @param m
	 *            is the m of equation y = mx+b.
	 * @return Returns the number of b.
	 */
	private double calculateY(double x, double b, double m) {
		return ((m * x) + b);
	}
	
	public boolean isPointInLine(Point p) {
		if (p.distance(start) + p.distance(end) - start.distance(end) < 0.00000001) {
			return true;
		} else {
			return false;
		}
	}

	// If this line does not intersect with the rectangle, return null.
	// Otherwise, return the closest intersection point to the
	// start of the line.
	public Point closestIntersectionToStartOfLine(Rectangle rect) {
		int indexOfMinPoint = 0;
		double minDistance = 0;
		double tempDistance = 0;
		List<Point> listOfPoints = new ArrayList<Point>();
		listOfPoints = rect.intersectionPoints(this);
		if(listOfPoints.size() == 0) {
			System.out.println("null!!");
		}

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