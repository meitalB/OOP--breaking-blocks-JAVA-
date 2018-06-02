package designlevel;

import geometricshape.Rectangle;
import biuoop.DrawSurface;
import management.GameLevel;
import object.Sprite;

import java.awt.Color;

/**
 * LevelName is the class of adding the name level to the game.
 */
public class LevelName implements Sprite {
    private String levelname;
    private Rectangle rect;

    /**
     * Construct a new name of the level.
     *
     * @param rect      is the rectangle to print the name on it.
     * @param levelName is the name to print the name on it.
     */
    public LevelName(Rectangle rect, String levelName) {
        this.levelname = levelName;
        this.rect = rect;
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
        int xBlock = (int) ((rect.getUpperRight().getX() + rect.getUpperLeft().getX()) / 1.5);
        int yBlock = (int) (rect.getUpperRight().getY() + rect.getLowRight().getY());
        surface.drawText(xBlock, yBlock, "Name level:" + this.levelname, 14);
    }

    /**
     * this function notify the sprite that time has passed.
     * @param dt is for the frame drawing
     */
    @Override
    public void timePassed(double dt) {
    }

    /**
     * the function adds this sprite draw to the game.
     *
     * @param game is a new game.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * the function removes this sprite draw to the game.
     *
     * @param game is a new game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}
