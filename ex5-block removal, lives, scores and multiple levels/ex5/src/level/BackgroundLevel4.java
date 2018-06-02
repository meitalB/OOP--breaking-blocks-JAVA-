package level;

import biuoop.DrawSurface;
import management.GameLevel;
import object.Sprite;

import java.awt.Color;

/**
 * class level.BackgroundLevel4 - draw a background object.
 */
public class BackgroundLevel4 implements Sprite {
    /**
     * implements of object.Sprite- the function draws a background on the surface.
     *
     * @param d is the surface to be draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {

        d.setColor(Color.blue);
        d.fillRectangle(0, 0, 800, 600);

        int x = 0;
        d.setColor(Color.white);
        for (int i = 0; i < 11; i++) {
            d.drawLine(120 + x, 400, 80 + x, 600);

            x += 8;
        }
        d.setColor(Color.darkGray);
        d.fillCircle(120, 400, 25);
        d.fillCircle(150, 410, 27);
        d.setColor(Color.GRAY);
        d.fillCircle(155, 370, 29);
        d.setColor(Color.GRAY);
        d.fillCircle(180, 390, 25);
        d.setColor(Color.lightGray);
        d.fillCircle(210, 390, 32);
        d.fillCircle(190, 420, 20);

        int y = 0;
        d.setColor(Color.white);
        for (int i = 0; i < 11; i++) {
            d.drawLine(580 + y, 500, 500 + y, 600);

            y += 8;
        }
        d.setColor(Color.darkGray);
        d.fillCircle(580, 500, 25);
        d.fillCircle(600, 470, 20);
        d.setColor(Color.GRAY);
        d.fillCircle(610, 510, 20);
        d.fillCircle(635, 480, 32);
        d.setColor(Color.lightGray);
        d.fillCircle(650, 500, 30);
        d.fillCircle(670, 480, 25);
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