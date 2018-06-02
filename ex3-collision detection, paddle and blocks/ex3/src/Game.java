import java.awt.Color;
import biuoop.DrawSurface;
import biuoop.GUI;
/**
 * class Game - define Game object, a new game with balls, blocks and paddle.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    public static final int BOARD_WIDE = 900;
    public static final int BOARD_LENGHT = 500;
    public static final int BLOCK_LEN = 20;
    public static final int BLOCK_WIDTH = 60;
    /**
     * Constructor - define game with new sprites ,and new environment.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
    }
    /**
     * the function add new collidable to the environment.
     *
     * @param c
     *            is a new Collidable.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }
    /**
     * the function add new Sprite to the environment.
     * @param s is a new Sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }
    /**
     * the function Initialize a new game: create the Blocks and Ball (and Paddle) .
     * and add them to the game.
     */
    public void initialize() {
        this.gui = new GUI("Game", BOARD_WIDE, BOARD_LENGHT);
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        this.environment = new GameEnvironment();
        // Create background.
        Rectangle rectBlockBack = new Rectangle(0, 0, BOARD_WIDE, BOARD_LENGHT);
        Block background = new Block(rectBlockBack, java.awt.Color.BLUE, 3);
        background.addToGame(this);
        // adding balls to the game.
        int angle = 45;
        int speed = 5;
        int numOfBalls = 2;
        int xStartBall = 400;
        int ystartBall = 200;
        for (int i = 0; i < 2; i++) {
            Point center = new Point(xStartBall, ystartBall);
            Ball ball = new Ball(center, 5, java.awt.Color.WHITE, environment);
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            ball.setVelocity(v);
            ball.addToGame(this);
            xStartBall += 50;
            ystartBall += 50;
        }
        // adding the paddle.
        Rectangle paddleRect = new Rectangle(BOARD_WIDE / 2,
                BOARD_LENGHT - BLOCK_WIDTH + BLOCK_LEN, BLOCK_WIDTH * 2,
                BLOCK_LEN);
        Paddle paddle = new Paddle(keyboard, paddleRect);
        paddle.addToGame(this);
        // adding the blocks.
        java.awt.Color[] color = new java.awt.Color[6];
        color[0] = java.awt.Color.GRAY;
        color[1] = java.awt.Color.RED;
        color[2] = java.awt.Color.YELLOW;
        color[3] = java.awt.Color.BLUE;
        color[4] = java.awt.Color.PINK;
        color[5] = java.awt.Color.GREEN;
        int sizeBlock = BLOCK_WIDTH;
        int levelBlock = 100;
        int hits = 1;
        for (int i = 0; i < 6; i++) {
            Point newUpLeft = new Point(BOARD_WIDE - sizeBlock - BLOCK_LEN, levelBlock);
            for (int j = 0; j < 10 - i; j++) {
                if (i == 0) {
                    hits = 2;
                }
                Rectangle rect = new Rectangle(newUpLeft, BLOCK_WIDTH, BLOCK_LEN);
                Block block = new Block(rect, color[i], hits);
                block.addToGame(this);
                sizeBlock = sizeBlock + BLOCK_WIDTH;
                newUpLeft = new Point(BOARD_WIDE - sizeBlock - BLOCK_LEN, levelBlock);
                hits = 1;
            }
            sizeBlock = BLOCK_WIDTH;
            levelBlock += BLOCK_LEN;
        }
        // define border blocks.
        Rectangle upRectBlock = new Rectangle(0, 0, BOARD_WIDE, BLOCK_LEN);
        Block upperBlock = new Block(upRectBlock, Color.gray);
        Rectangle lowRectBlock = new Rectangle(0, BOARD_LENGHT - BLOCK_LEN,
                BOARD_WIDE, BLOCK_LEN);
        Block lowerBlock = new Block(lowRectBlock, Color.gray);
        Rectangle leftRectBlock = new Rectangle(0, BLOCK_LEN, BLOCK_LEN
                , BOARD_LENGHT - BLOCK_LEN);
        Block leftBlock = new Block(leftRectBlock, Color.gray);
        Rectangle rigthRectBlock = new Rectangle(BOARD_WIDE - BLOCK_LEN,
                BLOCK_LEN, BLOCK_LEN, BOARD_LENGHT - BLOCK_LEN);
        Block rightBlock = new Block(rigthRectBlock, Color.gray);
        // add the borders to the game.
        upperBlock.addToGame(this);
        lowerBlock.addToGame(this);
        leftBlock.addToGame(this);
        rightBlock.addToGame(this);
    }
    /**
     * the function Run the game - start the animation loop.
     */
    public void run() {
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            this.sprites.notifyAllTimePassed();
            gui.show(d);
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
    /**
     * the main function make all the game work.
     * @param args - the arguments from the command line.
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }

}