package management;

import java.awt.Color;

import geometricshape.Rectangle;
import biuoop.DrawSurface;
import object.Sprite;

/**
 * Class LivesIndicator extends Indicator implements Sprite.
 * LivesIndicator indicate after the number of the lives.
 */
public class LivesIndicator implements Sprite {
    private Counter countLive;
    private Rectangle rect;

    /**
     * Construct a LivesIndicator according to the parameters of
     * Rectangle of the block,and the number of lives.
     *
     * @param rect      is the Rectangle of the block.
     * @param countLive is the number of lives to draw on the block.
     */
    public LivesIndicator(Rectangle rect, Counter countLive) {
        this.countLive = countLive;
        this.rect = rect;
    }

    /**
     * the function is called whenever add the numbers of lives.
     *
     * @param num is the live counter.
     */
    public void increaseLives(int num) {
        this.countLive.increase(num);
    }

    /**
     * the function is called whenever decrease the numbers of lives.
     *
     * @param num is the live counter.
     */
    public void decreaseLives(int num) {
        this.countLive.decrease(num);
    }

    /**
     * The function draws a LivesIndicator block on the surface.
     *
     * @param surface is the surface to be draw on.
     */
    public void drawOn(DrawSurface surface) {
        // fill the block.
        surface.setColor(Color.red);
        // define the middle of the block by x and y.
        int xBlock = (int) (rect.getUpperRight().getX() + rect.getUpperLeft().getX()) / 6;
        int yBlock = (int) (rect.getUpperRight().getY() + rect.getLowRight().getY());
        surface.drawText(xBlock, yBlock, "Lives:" + this.countLive.getValue(), 14);
    }

    /**
     * this function notify the sprite that time has passed.
     *
     * @param dt is the dt value.
     */
    @Override
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
