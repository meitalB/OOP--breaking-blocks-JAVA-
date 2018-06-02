package level;
import biuoop.DrawSurface;
import management.GameLevel;
import object.Sprite;

import java.awt.Color;
/**
 * class level.BackgroundLevel1 - draw a background object.
 */
public class BackgroundLevel1 implements Sprite {
    /**
     * implements of object.Sprite- the function draws a background on the surface.
     *
     * @param d is the surface to be draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.red);
        d.drawCircle(400, 300, 80);
        d.setColor(Color.yellow);
        d.drawCircle(400, 300, 110);
        d.setColor(Color.red);
        d.drawCircle(400, 300, 140);
        d.drawLine(250, 300, 350, 300);
        d.drawLine(450, 300, 550, 300);
        d.drawLine(400, 150, 400, 250);
        d.drawLine(400, 350, 400, 450);
    }
    /**
     * this function notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
    }
    /**
     * the function add this sprite draw to the game.
     *
     * @param game is a new game.
     */
    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
    /**
     * the function remove this sprite draw to the game.
     *
     * @param game is a new game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}
