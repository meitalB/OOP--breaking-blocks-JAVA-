package object;

import geometricshape.Line;
import geometricshape.Point;
import information.Velocity;
import information.CollisionInfo;

import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import management.GameEnvironment;
import management.GameLevel;
import management.HitListener;
import management.HitNotifier;

/**
 * class object.Ball - define ball object, the ball has a center and radius with some functions.
 */
public class Ball implements Sprite, HitNotifier {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity v;
    private GameEnvironment gameEnv;
    private List<HitListener> hitListeners;
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
        this.hitListeners = new ArrayList<HitListener>();

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
        this.hitListeners = new ArrayList<HitListener>();

    }
    /**
     * Constructor - define ball with x and y center, radius, color.
     * @param x is the x value of the point center.
     * @param y is the y value of the point center.
     * @param r is the radius value.
     * @param color is the color value.
     * @param gameEnv is the game environment of the ball.
     */
    public Ball(int x, int y, int r, java.awt.Color color, GameEnvironment gameEnv) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.gameEnv = gameEnv;
        this.hitListeners = new ArrayList<HitListener>();

        }
    /**
     * Constructor - define ball with x and y center, radius, color.
     * @param center is the point center value.
     * @param r is the radius value.
     * @param color is the color value.
     * @param gameEnv is the game environment of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gameEnv) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.gameEnv = gameEnv;
        this.hitListeners = new ArrayList<HitListener>();

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
        surface.setColor(this.color);
        surface.fillCircle(x, y, this.r);
        surface.setColor(color.black);
        surface.drawCircle(x, y, this.r);
    }
    /**
     * The function define a new velocity according to the other information.Velocity values.
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
        Line trajectory = new Line(this.center, p1);
        //checking intersection
        CollisionInfo collInfo = this.gameEnv.getClosestCollision(trajectory);
        // intersection- calling hit
        if (collInfo != null) {
            this.v = collInfo.collisionObject().hit(this, collInfo.collisionPoint(), this.v);
            this.center = this.getVelocity().almostApplyToPoint(collInfo.collisionPoint(), collInfo);
        } else {
        // no intersection
        this.center = this.getVelocity().applyToPoint(this.center);
        }
    }
    /**
     * The function set the game environment.
     * @param gb is game environment of the ball.
     */
    public void setGameEnvironment(GameEnvironment gb) {
        this.gameEnv = gb;
    }
    /**
     * the function make the ball to move one step.
     */
    public void timePassed() {
        this.moveOneStep();
    }
    /**
     * the function add the ball to game.
     * @param game is a new game.
     */
    public void addToGame(GameLevel game) {
     game.addSprite(this);
    }
    /**
     * the function remove the ball to game.
     * @param game is a new game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
    /**
     * the function add the Listener.
     * @param hl is a new HitListener.
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
    /**
     * the function remove the Listener.
     * @param  hl is a remove from HitListener.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
    /**
     * the function update all the object in the listener.
     * @param hitter is a the ball.
     */
    public void notifyHit(Block hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(hitter, this);
        }
    }
}