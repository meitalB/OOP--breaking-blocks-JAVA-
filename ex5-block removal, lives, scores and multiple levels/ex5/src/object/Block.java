package object;


import information.Velocity;
import biuoop.DrawSurface;
import management.GameLevel;
import management.HitListener;
import management.HitNotifier;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import geometricshape.Line;
import geometricshape.Point;
import geometricshape.Rectangle;
/**
 * class object.Block - define block object, the object.Block has a color.
 *  and rectangle and counter hits.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private List<HitListener> hitListeners;
    private Rectangle rect;
    private Color color;
    private int counterHit;
    private Rectangle deathRegion;
    public static final int BOARD_LENGHT = 600;
    /**
     * Constructor - define block with rectangle.
     *
     * @param rect is the rectangle.
     */
    public Block(Rectangle rect) {
        this.rect = rect;
        this.hitListeners = new ArrayList<HitListener>();
    }
    /**
     * Constructor - define block with rectangle and color, counter hits.
     *
     * @param rect is the rectangle.
     * @param color is the color of the block.
     * @param counterHit is the counter of hits.
     */
    public Block(Rectangle rect, Color color, int counterHit) {
        this.rect = rect;
        this.color = color;
        this.counterHit = counterHit;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Constructor - define block with rectangle and color, counter hits.
     * @param rect  is the rectangle.
     * @param color is the color of the block.
     */
    public Block(Rectangle rect, Color color) {
        this.rect = rect;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }
    /**
     * the function return the collide block .
     *
     * @return the collide block.
     */
    public Rectangle getCollisionRectangle() {
       return this.rect;
    }
    /**
     * the function notify the object that we collided with it at collisionPoint with
     * a given velocity.
     *
     * @param collisionPoint  is the point of the collide was.
     * @param hitter  is the current ball.
     * @param currentVelocity is the current velocity of the ball that collide.
     * @return The return is the new velocity expected after the hit .
     * (based on the force the object inflicted on us).
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVel = currentVelocity;
        List<Line> linesRect = new ArrayList<Line>();
        // define the list of four lines.
        linesRect = rect.linesRectangle();
        // upper line.
        if (linesRect.get(0).isPointInLine(collisionPoint)) {
            newVel = new Velocity(currentVelocity.getDX(), currentVelocity.getDY() * -1);
          //lower line.
        } else if (linesRect.get(1).isPointInLine(collisionPoint)) {
            if (this.getCollisionRectangle().getLowLeft().getY() == BOARD_LENGHT) {
            hitter.notifyHit(this);
            this.notifyHit(hitter);
            } else {
                newVel = new Velocity(currentVelocity.getDX(), currentVelocity.getDY() * -1);
            }
            // left line and right line.
        } else if (linesRect.get(2).isPointInLine(collisionPoint)
                || linesRect.get(3).isPointInLine(collisionPoint)) {
            newVel = new Velocity(currentVelocity.getDX() * -1, currentVelocity.getDY());
        }
        // update the hit counter of blocks.
        if (counterHit != 3) {
            this.counterHit--;
        }
        this.notifyHit(hitter);
        return newVel;
    }

    /**
     * the function draw the sprite on screen.
     *
     * @param surface is the DrawSurface we draw on.
     */
    public void drawOn(DrawSurface surface) {
        // fill the block.
        surface.setColor(this.color);
        surface.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
        // draw the block frame.
        surface.setColor(color.black);
        surface.drawRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
        // define the middle of the block by x and y.
        int xBlock = (int) (rect.getUpperRight().getX() + rect.getUpperLeft().getX()) / 2;
        int yBlock = (int) (rect.getUpperRight().getY() + rect.getLowRight().getY()) / 2;
        // write nothing.

    }

    /**
     * the function  do nothing.
     */
    public void timePassed() {
        // no operation ass3.
    }

    /**
     * the function add ball to the game.
     * @param game is a new game.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }
    /**
     * the function remove ball to the game.
     * @param game is a new game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
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
     * the function get the counter hits.
     * @return  the counter hits.
     */
    public int getHitsPoint() {
        return this.counterHit;
    }
    /**
     * the function update all the object in the listener.
     * @param hitter is a the ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
