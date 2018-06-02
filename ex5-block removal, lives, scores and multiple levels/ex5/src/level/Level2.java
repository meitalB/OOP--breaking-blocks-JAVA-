package level;

import java.awt.Color;

import java.util.LinkedList;
import java.util.List;

import geometricshape.Point;
import geometricshape.Rectangle;
import information.Velocity;
import object.Block;
import object.Sprite;

/**
 * class Level2- define level of the game object with some functions.
 */
public class Level2 implements LevelInformation {
    public static final int NUM_OF_BALLS = 6, PADDLE_SPEED = 5, PADDLE_WIDTH = 200, NUM_OF_BLOCK = 14;
    public static final String NAME_LEVEL = "Wide Easy";
    public static final int ANGLE = 45;
    public static final int SPEED = 5;
    public static final int BOARD_WIDE = 800;
    public static final int BOARD_LENGHT = 600;
    public static final int BLOCK_LEN = 20;
    public static final int BLOCK_WIDTH = 60;

    private int numberOfBalls;
    private int paddleSpeed;
    private int paddleWidth;
    private int numberOfBlocksToRemove;
    private String levelName;

    /**
     * construct a level of the game according to numberOfBalls,
     * paddleSpeed, paddleWidth, numberOfBlocksToRemove and levelName.
     */
    public Level2() {
        this.numberOfBalls = NUM_OF_BALLS;
        this.paddleSpeed = PADDLE_SPEED;
        this.paddleWidth = PADDLE_WIDTH;
        this.numberOfBlocksToRemove = NUM_OF_BLOCK;
        this.levelName = NAME_LEVEL;
    }

    /**
     * counter the number of balls of this levels.
     *
     * @return numberOfBalls the number of balls.
     */
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    /**
     * The initial velocity of each ball.
     *
     * @return velocityOfLevel the list of velocity balls.
     */
    public java.util.List<Velocity> initialBallVelocities() {
        List<Velocity> velocityOfLevel = new LinkedList<Velocity>();
        int ang = 250;
        for (int i = 0; i < this.numberOfBalls; i++) {
            velocityOfLevel.add(Velocity.fromAngleAndSpeed(ang, 5));
            ang += 29;
        }
        return velocityOfLevel;
    }

    /**
     * return the speed of the paddle of this levels.
     *
     * @return paddleSpeed the paddle speed of this level.
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * List of Points of the balls.
     *
     * @return center - the list points of the balls.
     */
    public Point[] setPointBalls() {
        int xStartBall = 300;
        int yStartBall = 350;
        Point[] center = new Point[this.numberOfBalls()];

        // adding 2 balls to the game.
        for (int i = 0; i < this.numberOfBalls(); i++) {
            // System.out.println(this.counterBalls.getValue() + "balls");
            center[i] = new Point(xStartBall, yStartBall);

            // define the another ball point.
            xStartBall += 50;
            yStartBall += 20;
        }

        return center;
    }

    /**
     * return the width of the paddle of this levels.
     *
     * @return paddleWidth the paddle width of this level.
     */
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * return the name of the level.
     * the level name will be displayed at the top of the screen.
     *
     * @return levelName the name of the level.
     */
    public String levelName() {
        return this.levelName;
    }


    /**
     * Returns a sprite with the background of the level.
     *
     * @return backgroundLevel1 the name of the level.
     */
    public Sprite getBackground() {
        BackgroundLevel2 backgroundLevel2 = new BackgroundLevel2();
        return backgroundLevel2;
    }

    /**
     * The Blocks that make up this level, each block contain its size,
     * color and location.
     *
     * @return blockOfLevel the list of the blocks on the level.
     */
    public java.util.List<Block> blocks() {
        List<Block> blockOfLevel = new LinkedList<Block>();

        // creating block.


        java.awt.Color[] color = new java.awt.Color[7];
        color[0] = Color.GRAY;
        color[1] = Color.RED;
        color[2] = Color.YELLOW;
        color[3] = Color.BLUE;
        color[4] = Color.PINK;
        color[5] = Color.GREEN;
        color[6] = Color.orange;
        int sizeBlock = 760 / 14;
        int levelBlock = 100;
        int j = 0;
        int x = 20;
        Point newUpLeft = new Point(x, 300);

        for (int i = 0; i < 14; i++) {

            // creating block.
            Rectangle rect = new Rectangle(newUpLeft, sizeBlock, 20);
            Block block = new Block(rect, color[j], 2);
            // adding block to the game.
            x += sizeBlock;
            newUpLeft = new Point(x, 300);

            if (i % 2 != 0) {
                j += 1;
            }

            blockOfLevel.add(block);
        }
        return blockOfLevel;
    }

    /**
     * Number of levels that should be removed before the level
     * is considered to be "cleared".
     * color and location.
     *
     * @return numberOfBlocksToRemove - the number of blocks in the level.
     */
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }

}
