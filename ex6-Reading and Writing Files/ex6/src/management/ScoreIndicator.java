package management;

import java.awt.Color;

import geometricshape.Rectangle;
import biuoop.DrawSurface;
import object.Sprite;

/**
 * Class ScoreIndicator extends Indicator implements Sprite.
 * in charge of displaying the current score.
 * ScoreIndicator will hold a reference to the scores counter, and
 * will be added to the game as a sprite positioned at the top of the screen.
 */
public class ScoreIndicator implements Sprite {
    private Rectangle rect;
    private Counter countScore;

    /**
     * Construct a ScoreIndicator according to parameters that recieved.
     *
     * @param rect       Rectangle of the block,and the number of lives.
     * @param countScore is the score counter to draw into the block on the screen.
     */
    public ScoreIndicator(Rectangle rect, Counter countScore) {
        this.rect = rect;
        this.countScore = countScore;

    }

    /**
     * The function draws the block score on the surface.
     *
     * @param surface is the surface to be draw on it.
     */
    public void drawOn(DrawSurface surface) {
        // fill the block.
        surface.setColor(Color.red);
        // define the middle of the block by x and y.
        int xBlock = (int) (rect.getUpperRight().getX() + rect.getUpperLeft().getX()) / 2;
        int yBlock = (int) (rect.getUpperRight().getY() + rect.getLowRight().getY());
        surface.drawText(xBlock, yBlock, "score:" + this.countScore.getValue(), 14);

    }

    /**
     * this function notify the sprite that time has passed.
     *
     * @param dt is the dt value.
     */
    public void timePassed(double dt) {
    }

    /**
     * this function add the sprite to the game.
     *
     * @param game is the current game.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

}
