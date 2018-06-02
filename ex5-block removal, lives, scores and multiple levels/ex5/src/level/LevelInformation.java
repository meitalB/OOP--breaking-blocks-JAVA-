package level;

import information.Velocity;
import geometricshape.Point;

import java.util.List;

import object.Sprite;
import object.Block;

/**
 * interface LevelInformation - is an interface that count the number
 * of balls, male list of velocity balls, paddle information, name of the level,
 * background of the level, list of blocks and number of balls points and blocks.
 */
public interface LevelInformation {
    /**
     * counter the number of balls of this levels.
     *
     * @return the number of balls.
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     *
     * @return the list of velocity balls.
     */
    List<Velocity> initialBallVelocities();

    /**
     * return the speed of the paddle of this levels.
     *
     * @return the paddle speed of this level.
     */
    int paddleSpeed();

    /**
     * return the width of the paddle of this levels.
     *
     * @return the paddle width of this level.
     */
    int paddleWidth();

    /**
     * return the name of the level.
     * the level name will be displayed at the top of the screen.
     *
     * @return the name of the level.
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     *
     * @return the name of the level.
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contain its size,
     * color and location.
     *
     * @return the list of the blocks on the level.
     */
    List<Block> blocks();

    /**
     * Number of levels that should be removed before the level
     * is considered to be "cleared".
     * color and location.
     *
     * @return the number of blocks in the level.
     */
    int numberOfBlocksToRemove();

    /**
     * List of Points of the balls.
     *
     * @return list of points - the list points of the balls.
     */
    Point[] setPointBalls();
}