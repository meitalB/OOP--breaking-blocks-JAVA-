import biuoop.DrawSurface;
/**
 * class Ball - define ball object, the ball has a center and radius with some functions.
 */
public class Ball {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity v;
    private Point bounderUpLeft;
    private Point bounderDownRight;
    private GameEnvironment gameEnv;
    public static final int BOARD_WIDE = 200;
    public static final int BOARD_LENGHT = 200;
    /**
     * Constructor - define ball with point (center), radius and color.
     * @param center is the point center value.
     * @param r is the radius value.
     * @param color is the color value.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }
    /**
     * Constructor - define ball with x and y center, radius, color.
     * @param x is the x value of the point center.
     * @param y is the y value of the point center.
     * @param r is the radius value.
     * @param color is the color value.
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
    }  
    /**
     * Constructor - define ball with x and y center, radius, color.
     * @param x is the x value of the point center.
     * @param y is the y value of the point center.
     * @param r is the radius value.
     * @param color is the color value.
     * @param bounderUpLeft is the up left bound.
     * @param bounderDownRight is the down right bound.
     */
    public Ball(int x, int y, int r, java.awt.Color color, Point bounderUpLeft,
            Point bounderDownRight) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.bounderUpLeft = bounderUpLeft;
        this.bounderDownRight = bounderDownRight;
        }
    public Ball(int x, int y, int r, java.awt.Color color, Point bounderUpLeft,
            Point bounderDownRight, GameEnvironment gameEnv) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.bounderUpLeft = bounderUpLeft;
        this.bounderDownRight = bounderDownRight;
        this.gameEnv = gameEnv;
        }   
    public Ball(Point center, int r, java.awt.Color color, Point bounderUpLeft,
            Point bounderDownRight, GameEnvironment gameEnv) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.bounderUpLeft = bounderUpLeft;
        this.bounderDownRight = bounderDownRight;
        this.gameEnv = gameEnv;
        }
    
    
    
    /**
     * The function returns the x value of the ball center.
     * @return x value of the ball center.
     */
    public int getX() {
        return (int) this.center.getX();
    }
    /**
     * The function returns the y value of the ball center.
     * @return y value of the ball center.
     */
    public int getY() {
        return (int) this.center.getY();
    }
    /**
     * The function returns the size of the radius ball.
     * @return the radius value of the radius ball.
     */
    public int getSize() {
        return this.r;
    }
    /**
     * The function returns the color ball.
     * @return the color ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }
    /**
     * The function draw the ball on the given DrawSurface.
     * @param surface - the surface of the ball.
     */
    public void drawOn(DrawSurface surface) {
        int x = (int) this.center.getX();
        int y = (int) this.center.getY();
        surface.fillCircle(x, y, this.r);
        surface.setColor(this.color);
    }
    /**
     * The function define a new velocity according to the other Velocity values.
     * @param otherV is a velocity parameter.
     */
    public void setVelocity(Velocity otherV) {
        this.v = new Velocity(otherV.getDX(), otherV.getDY());
    }
    /**
     * The function define a new velocity according to the x, y values.
     * @param dx is the x value.
     * @param dy is the y value.
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }
    /**
     * The function returns the current velocity.
     * @return this velocity.
     */
    public Velocity getVelocity() {
        return this.v;
    }
    /**
     * The function move the ball one step in the board according to the velocity.
     * the function change the velocity if the ball going outside the board.
     */
    public void moveOneStep() {
        Point p1 = this.getVelocity().applyToPoint(this.center);
        int mikomXmin = (int) p1.getX() - (int) this.r;
        int mikomXmax = (int) p1.getX() + (int) this.r;
        if ((mikomXmin < this.bounderUpLeft.getX())
                || (mikomXmax > this.bounderDownRight.getX())) {
           this.v = new Velocity(this.v.getDX() * -1, this.v.getDY());
        }
        int mikomYmin = (int) p1.getY() - (int) this.r;
        int mikomYmax = (int) p1.getY() + (int) this.r;
        if ((mikomYmin < this.bounderUpLeft.getY()
               || (mikomYmax  > this.bounderDownRight.getY()))) {
           this.v = new Velocity(this.v.getDX(), this.v.getDY() * -1);
        }
    	
    	Line trajectory = new Line(this.center, this.v.applyToPoint(this.center));
    	// checking intersection
    	CollisionInfo collInfo = this.gameEnv.getClosestCollision(trajectory);
        // intersection- calling hit
    	if(collInfo != null) {
        	this.v = collInfo.collisionObject().hit(collInfo.collisionPoint(), this.v);
        	this.center = this.getVelocity().almostApplyToPoint(collInfo.collisionPoint(), collInfo);
    	}
    	// no intersection
        this.center = this.getVelocity().applyToPoint(this.center);

    }
	public void setGameEnvironment(GameEnvironment gb) {
        this.gameEnv = gb;

	}
}