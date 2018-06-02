package level;

import biuoop.DrawSurface;
import management.GameLevel;
import object.Sprite;

import java.awt.Color;

/**
 * class level.BackgroundLevel3 - draw a background object.
 */
public class BackgroundLevel3 implements Sprite {
    /**
     * implements of object.Sprite- the function draws a background on the surface.
     *
     * @param d is the surface to be draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {

        d.setColor(Color.BLUE);
        d.fillRectangle(0, 0, 800, 600);

        d.setColor(Color.lightGray);
        d.fillRectangle(115, 200, 10, 150);
        d.setColor(Color.darkGray);
        d.fillRectangle(100, 350, 40, 50);
        d.setColor(Color.white);
        d.fillRectangle(50, 400, 150, 200);

        d.setColor(Color.darkGray);
        d.fillRectangle(50, 400, 10, 200);
        d.fillRectangle(75, 400, 10, 200);
        d.fillRectangle(100, 400, 10, 200);
        d.fillRectangle(125, 400, 10, 200);
        d.fillRectangle(150, 400, 10, 200);
        d.fillRectangle(175, 400, 10, 200);
        d.fillRectangle(200, 400, 10, 200);
        d.fillRectangle(50, 550, 160, 600);


        d.setColor(Color.red);
        d.drawCircle(120, 200, 12);
        d.fillCircle(120, 200, 12);
        d.setColor(Color.yellow);
        d.drawCircle(120, 200, 8);
        d.fillCircle(120, 200, 8);
        d.setColor(Color.blue);
        d.drawCircle(120, 200, 5);
        d.fillCircle(120, 200, 5);
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
