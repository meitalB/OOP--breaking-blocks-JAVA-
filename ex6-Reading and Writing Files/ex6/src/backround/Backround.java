package backround;

import biuoop.DrawSurface;
import designlevel.LevelInformation;
import management.GameLevel;
import object.Sprite;

/**
 * The class create background and implements from sprite.
 * the class has some functions.
 */
public class Backround implements Sprite {
    private LevelInformation levelInformation;

    /**
     * Constructor - define background with levelInformation.
     * @param levelInformation is the level information.
     */
    public Backround(LevelInformation levelInformation) {
        this.levelInformation = levelInformation;

    }

    /**
     * the function draw the sprite on screen.
     * @param d is the DrawSurface we draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        this.levelInformation.drawBackground(d);
    }

    /**
     * the function update the time passed of the game.
     * @param dt is a time according frames.
     */
    @Override
    public void timePassed(double dt) {
    }

    /**
     * the function add this paddle to the game.
     * @param game is a new game.
     */
    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * the function remove this paddlefrom  the game.
     *
     * @param game is a new game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}
