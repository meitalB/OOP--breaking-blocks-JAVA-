package level;

import java.util.LinkedList;
import java.util.List;

import geometricshape.Point;
import geometricshape.Rectangle;
import information.Velocity;
import object.Block;
import object.Sprite;

/**
 * class Level3- define level of the game object with some functions.
 */
public class Level3 implements LevelInformation {
    public static final int NUM_OF_BALLS = 2, PADDLE_SPEED = 4, PADDLE_WIDTH = 200, NUM_OF_BLOCK = 33;
    public static final String NAME_LEVEL = "Green 3";
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
    public Level3() {
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
        int ang = ANGLE;
        for (int i = 0; i < this.numberOfBalls; i++) {
            velocityOfLevel.add(Velocity.fromAngleAndSpeed(ang, SPEED));
            ang += 270;
        }
        return velocityOfLevel;
    }

    /**
     * return the speed of the paddle of this levels.
     *
     * @return paddleSpeed the speed width of this level.
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
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
        BackgroundLevel3 backgroundLevel3 = new BackgroundLevel3();
        return backgroundLevel3;
    }

    /**
     * The Blocks that make up this level, each block contain its size,
     * color and location.
     *
     * @return blockOfLevel the list of the blocks on the level.
     */
    public java.util.List<Block> blocks() {
        List<Block> blockOfLevel = new LinkedList<Block>();
        java.awt.Color[] color = new java.awt.Color[6];
        color[0] = java.awt.Color.GRAY;
        color[1] = java.awt.Color.RED;
        color[2] = java.awt.Color.YELLOW;
        color[3] = java.awt.Color.BLUE;
        color[4] = java.awt.Color.PINK;
        color[5] = java.awt.Color.GREEN;
        int sizeBlock = BLOCK_WIDTH;
        int levelBlock = 100;
        for (int i = 0; i < 6; i++) {
            Point newUpLeft = new Point(BOARD_WIDE - sizeBlock - BLOCK_LEN, levelBlock);
            for (int j = 0; j < 8 - i; j++) {
                // creating block.
                Rectangle rect = new Rectangle(newUpLeft, BLOCK_WIDTH, BLOCK_LEN);
                Block block = new Block(rect, color[i], 1);
                sizeBlock = sizeBlock + BLOCK_WIDTH;
                newUpLeft = new Point(BOARD_WIDE - sizeBlock - BLOCK_LEN, levelBlock);
                blockOfLevel.add(block);

            }
            sizeBlock = BLOCK_WIDTH;
            levelBlock += BLOCK_LEN;
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

    /**
     * List of Points of the balls.
     *
     * @return center - the list points of the balls.
     */
    public Point[] setPointBalls() {
        int xStartBall = 400;
        int yStartBall = 500;
        Point[] center = new Point[this.numberOfBalls()];

        // adding 2 balls to the game.
        for (int i = 0; i < this.numberOfBalls(); i++) {
            // System.out.println(this.counterBalls.getValue() + "balls");
            center[i] = new Point(xStartBall, yStartBall);

            // define the another ball point.
            //  xStartBall += 50;

        }
        return center;
    }
}
