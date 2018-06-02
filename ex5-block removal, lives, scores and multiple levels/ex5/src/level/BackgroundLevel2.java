package level;

import biuoop.DrawSurface;
import management.GameLevel;
import object.Sprite;

import java.awt.Color;

/**
 * class level.BackgroundLevel2 - draw a background object.
 */
public class BackgroundLevel2 implements Sprite {
    /**
     * implements of object.Sprite- the function draws a background on the surface.
     *
     * @param d is the surface to be draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0, 0, 800, 600);

        int x = 0;
        d.setColor(Color.orange);
        for (int i = 0; i < 100; i++) {
            d.drawLine(150, 150, x, 300);
            x += 8;
        }
        d.setColor(Color.yellow);
        d.drawCircle(120, 150, 70);
        d.fillCircle(120, 150, 70);
        d.setColor(Color.pink);
        d.drawCircle(120, 150, 60);
        d.fillCircle(120, 150, 60);
        d.setColor(Color.orange);
        d.drawCircle(120, 150, 50);
        d.fillCircle(120, 150, 50);

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
