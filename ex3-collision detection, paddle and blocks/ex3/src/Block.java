import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * class Block - define block object, the Block has a color.
 *  and rectangle and counter hits.
 */
public class Block implements Collidable, Sprite {
    private Rectangle rect;
    private Color color;
    private int counterHit;
    /**
     * Constructor - define block with rectangle.
     * @param rect is the rectangle.
     */
    public Block(Rectangle rect) {
        this.rect = rect;
    }
    /**
     * Constructor - define block with rectangle and color, counter hits.
     * @param rect is the rectangle.
     * @param color is the color of the block.
     * @param counterHit is the conuter of hits.
     */
    public Block(Rectangle rect, Color color, int counterHit) {
        this.rect = rect;
        this.color = color;
        this.counterHit = counterHit;
    }
    /**
     * Constructor - define block with rectangle and color, counter hits.
     * @param rect is the rectangle.
     * @param color is the color of the block.
     */
    public Block(Rectangle rect, Color color) {
        this.rect = rect;
        this.color = color;
    }
    /**
     * the function return the collide block .
     * @return the collide block.
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }
    /**
     *  the function notify the object that we collided with it at collisionPoint with
     * a given velocity.
     *  @param collisionPoint is the point of the collide was.
     *  @param currentVelocity is the current velocity of the ball that collide.
     *  @return The return is the new velocity expected after the hit .
     *  (based on the force the object inflicted on us).
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        Velocity newVel = currentVelocity;
        List<Line> linesRect = new ArrayList<Line>();
        linesRect = rect.linesRectangle();

        if (linesRect.get(0).isPointInLine(collisionPoint)
                || linesRect.get(1).isPointInLine(collisionPoint)) {
            newVel = new Velocity(currentVelocity.getDX(), currentVelocity.getDY() * -1);
        } else if (linesRect.get(2).isPointInLine(collisionPoint)
                || linesRect.get(3).isPointInLine(collisionPoint)) {
            newVel = new Velocity(currentVelocity.getDX() * -1, currentVelocity.getDY());
        }
        if (counterHit != 3) {
            this.counterHit--;
        }
        return newVel;
    }
    /**
     * the function draw the sprite on screen.
     * @param surface is the DrawSurface we draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(), (int) rect.getWidth(),
                (int) rect.getHeight());
        surface.setColor(color.black);
        surface.drawRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(), (int) rect.getWidth(),
                (int) rect.getHeight());
        int xBlock = (int) (rect.getUpperRight().getX() + rect.getUpperLeft().getX()) / 2;
        int yBlock = (int) (rect.getUpperRight().getY() + rect.getLowRight().getY()) / 2;
        if (counterHit == 3) {
            surface.drawText(xBlock, yBlock, "", 14);
        } else if (counterHit <= 0) {
            surface.drawText(xBlock, yBlock, "X", 14);
        } else {
            String numOfHits = String.valueOf(counterHit);

            surface.drawText(xBlock, yBlock, numOfHits, 14);
        }
    }
    /**
     * the function  do nothing.
     */
    public void timePassed() {
        // *********
    }
    /**
     * the function add ball to the game.
     * @param game is a new game.
     */
    public void addToGame(Game game) {
        game.addSprite(this);
        game.addCollidable(this);
    }
}
