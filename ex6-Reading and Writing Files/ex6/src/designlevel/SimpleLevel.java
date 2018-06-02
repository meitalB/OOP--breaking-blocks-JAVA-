package designlevel;

import java.awt.Color;
import java.awt.Image;
import java.util.LinkedList;
import java.util.List;

import biuoop.DrawSurface;
import geometricshape.Point;
import information.Velocity;
import backround.Backround;
import backround.ColorsParser;
import object.Block;
import object.Sprite;

/**
 * class ColorsParser  implements LevelInformation
 * create a new level by the values.
 */
public class SimpleLevel implements LevelInformation {

    private int paddleSpeed;
    private int paddleWidth;
    private int numberOfBlocks;
    private int numberOfBalls;
    private List<String> stringVelocities;
    private String levelName;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;
    private String backgroundString;
    private ColorsParser background;
    private List<Velocity> ballVelocities;
    private Color color;
    private Image image;

    /**
     * constructor to a SimpleBlock with null values.
     *
     * @param paddleSpeed      define the name of paddle Speed.
     * @param paddleWidth      define the paddle width.
     * @param numberOfBlocks   define the number of level.
     * @param stringVelocities define the string Velocities.
     * @param levelName        define  level Name.
     * @param blocks           define the blocks.
     * @param background       define the background.
     */
    public SimpleLevel(int paddleSpeed, int paddleWidth, int numberOfBlocks,
                       List<String> stringVelocities, String levelName,
                       List<Block> blocks, String background) {
        this.paddleSpeed = paddleSpeed;
        this.paddleWidth = paddleWidth;
        this.numberOfBlocks = numberOfBlocks;
        this.numberOfBalls = 0;
        this.levelName = levelName;
        this.ballVelocities = new LinkedList<Velocity>();
        this.stringVelocities = stringVelocities;
        this.numberOfBlocksToRemove = numberOfBlocks;
        this.backgroundString = background;
        this.blocks = blocks;

        ColorsParser colorsParser = new ColorsParser();
        String[] parts = this.backgroundString.split("\\(");
        if (parts[0].equals("color")) {
            this.image = null;
            this.color = colorsParser.colorFromString(parts);

        } else if (parts[0].equals("image")) {
            this.color = null;
            this.image = colorsParser.imageFromString(parts);
        }
    }

    /**
     * counter the number of balls of this levels.
     *
     * @return the number of balls.
     */
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    /**
     * The initial velocity of each ball.
     *
     * @return the list of velocity balls.
     */
    public List<Velocity> initialBallVelocities() {
        int sizeList = stringVelocities.size();

        for (int i = 0; i < sizeList; i++) {
            String[] parts = stringVelocities.get(i).split(",");
            String left = parts[0]; // left
            String right = parts[1]; // right
            if ((parts.length == 2) && (!left.isEmpty())) {
                Integer angle = Integer.parseInt(left);
                Integer speed = Integer.parseInt(right);
                ballVelocities.add(Velocity.fromAngleAndSpeed(angle, speed));
                this.numberOfBalls++;
            } else {
                return null;
            }
        }
        return ballVelocities;
    }

    /**
     * return the speed of the paddle of this levels.
     *
     * @return the paddle speed.
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
     * the method draw the background of the level.
     *
     * @param d is drawsurface.
     */
    public void drawBackground(DrawSurface d) {
        if (this.color == null) {
            d.drawImage(0, 0, this.image);
        } else {
            d.setColor(this.color);
            d.fillRectangle(0, 0, 800, 600);
        }
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return backgroundN the name of the level.
     */
    public Sprite getBackground() {
        Backround backgroundN = new Backround(this);
        return backgroundN;
    }


    /**
     * The Blocks that make up this level, each block contain its size,
     * color and location.
     *
     * @return blockOfLevel the list of the blocks on the level.
     */
    public java.util.List<Block> blocks() {
        List<Block> blockOfLevel = this.blocks;
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
        int yStartBall = 400;
        Point[] center = new Point[this.numberOfBalls()];

        // adding balls to the game.
        for (int i = 0; i < this.numberOfBalls(); i++) {
            center[i] = new Point(xStartBall, yStartBall);
            // define the another ball point.
            xStartBall += 50;
            yStartBall += 20;
        }
        return center;
    }
}
