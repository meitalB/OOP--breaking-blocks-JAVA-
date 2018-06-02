package management;

import java.awt.Color;
import java.util.LinkedList;

import biuoop.KeyboardSensor;
import geometricshape.Point;
import geometricshape.Rectangle;
import information.Velocity;
import designlevel.LevelInformation;
import designlevel.LevelName;
import spritecollect.SpriteCollection;
import biuoop.DrawSurface;
import object.Ball;
import object.Block;
import object.Collidable;
import object.Paddle;
import object.Sprite;
//import level.Level2;


/**
 * GameLevel class implements Animation - Catering to initialize all the
 * variables of the game.
 */
public class GameLevel implements Animation {
    private AnimationRunner runner;
    private boolean running;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter counterBlocks;
    private Counter counterBalls;
    private Counter counterScore;
    private Counter counterLives;
    private ScoreTrackingListener scoreTracking;
    private ScoreIndicator scoreIndicator;
    private LivesIndicator livesIndicator;
    private Paddle paddle;
    private biuoop.KeyboardSensor keyboard;
    private LevelInformation level;
    private java.util.List<Block> blockList;
    private java.util.List<Velocity> velocityList;
    private java.util.List<Ball> ballList;
    private Sprite background;
    private Rectangle paddleRect;
    private BallRemover ballRemover;
    private BlockRemover blockRemover;
    private LevelName levelName;
    public static final int BOARD_WIDE = 800;
    public static final int BOARD_LENGHT = 600;
    public static final int BLOCK_LEN = 20;
    public static final int BLOCK_WIDTH = 60;

    /**
     * Constructor - define game with new sprites ,and new environment.
     */
    public GameLevel() {
//        this.level = new Level2();
        this.runner = new AnimationRunner();
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.counterBlocks = new Counter(0);
        this.counterBalls = new Counter(0);
        this.counterScore = new Counter(0);
        this.counterLives = new Counter(4);
        this.scoreTracking = new ScoreTrackingListener(counterScore);
        this.running = false;
        this.keyboard = this.runner.getKeyboard();
        this.velocityList = this.level.initialBallVelocities();
        this.blockList = this.level.blocks();
        this.ballList = new LinkedList<Ball>();
        paddleRect = new Rectangle(BOARD_WIDE / 2 - this.level.paddleWidth() / 2,
                BOARD_LENGHT - BLOCK_WIDTH + BLOCK_LEN, this.level.paddleWidth(),
                BLOCK_LEN);
        this.paddle = new Paddle(keyboard, paddleRect, this.level.paddleSpeed());
        this.ballRemover = new BallRemover(this, this.counterBalls);
        this.background = this.level.getBackground();
        this.blockRemover = new BlockRemover(this, this.counterBlocks);
    }

    /**
     * contract of GameLevel.
     *
     * @param currentLevel    is the current level
     * @param animationRunner is the runner of the game
     * @param livesContinue   the intilaize lives
     * @param counterBlock    the numbers of block at current level
     * @param scoreContinue   the intialize score
     */
    public GameLevel(LevelInformation currentLevel, AnimationRunner animationRunner,
                     Counter counterBlock, Counter scoreContinue, Counter livesContinue) {
        this.level = currentLevel;
        this.runner = animationRunner;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.counterBlocks = counterBlock;
        this.counterBalls = new Counter(0);
        this.counterScore = scoreContinue;
        this.counterLives = livesContinue;
        this.scoreTracking = new ScoreTrackingListener(this.counterScore);
        this.running = true;
        this.keyboard = this.runner.getKeyboard();
        this.velocityList = this.level.initialBallVelocities();
        this.blockList = this.level.blocks();
        this.ballList = new LinkedList<Ball>();
        paddleRect = new Rectangle(BOARD_WIDE / 2, BOARD_LENGHT - BLOCK_WIDTH + BLOCK_LEN, this.level.paddleWidth(),
                BLOCK_LEN);
        this.paddle = new Paddle(keyboard, paddleRect, this.level.paddleSpeed());
        this.ballRemover = new BallRemover(this, this.counterBalls);
        this.background = this.level.getBackground();
        this.blockRemover = new BlockRemover(this, this.counterBlocks);
    }

    /**
     * the function add new collidable to the environment.
     *
     * @param c a new object.Collidable.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * the function add new object.Sprite to the environment.
     *
     * @param s is a new object.Sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * the function remover  collidable to the environment.
     *
     * @param c the remover object.Collidable.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * the function remover new object.Sprite to the environment.
     *
     * @param s is remover object.Sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * the function Initialize a new game: create the Blocks and object.Ball
     * (and object.Paddle) . and add them to the game.
     */
    public void initialize() {

        // Create background.
        this.background.addToGame(this);
        // the upper block.
        Rectangle upRectBlock = new Rectangle(0, 0, BOARD_WIDE, BLOCK_LEN * 2);
        Block upperBlock = new Block(upRectBlock, Color.pink);
        Rectangle lowRectBlock = new Rectangle(0, BOARD_LENGHT - 0.999, BOARD_WIDE, BLOCK_LEN);
        // the lower block.
        Block lowerBlock = new Block(lowRectBlock, Color.gray);
        Rectangle leftRectBlock = new Rectangle(0, BLOCK_LEN, BLOCK_LEN, BOARD_LENGHT - BLOCK_LEN);
        // the left block.
        Block leftBlock = new Block(leftRectBlock, Color.pink);
        Rectangle rigthRectBlock = new Rectangle(BOARD_WIDE - BLOCK_LEN, BLOCK_LEN, BLOCK_LEN,
                BOARD_LENGHT - BLOCK_LEN);
        // the right block.
        Block rightBlock = new Block(rigthRectBlock, Color.pink);
        // add the borders to the game.
        upperBlock.addToGame(this);
        lowerBlock.addToGame(this);
        leftBlock.addToGame(this);
        rightBlock.addToGame(this);
        lowerBlock.addHitListener(ballRemover);
        // adding the paddle.
        paddle.addToGame(this);
        //add blocks

        for (Block block : this.blockList) {
            block.addToGame(this);
            block.addHitListener(this.blockRemover);
            block.addHitListener(this.scoreTracking);
        }
        this.counterBlocks.increase(this.level.numberOfBlocksToRemove());

        Rectangle answerRect = new Rectangle(0, 0, BOARD_WIDE, BLOCK_LEN);
        Block scoreBlock = new Block(answerRect, Color.WHITE);
        this.scoreIndicator = new ScoreIndicator(answerRect, counterScore);
        this.livesIndicator = new LivesIndicator(answerRect, counterLives);
        this.levelName = new LevelName(answerRect, this.level.levelName());
        scoreBlock.addToGame(this);
        this.scoreIndicator.addToGame(this);
        this.livesIndicator.addToGame(this);
        this.levelName.addToGame(this);
    }

    /**
     * the function check if this animation have to stop.
     *
     * @return false if it have stop
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * This function's responsible of  current frame of the game.
     *
     * @param d  is the DrawSurface to draw on
     * @param dt is the dt value.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        if (counterBlocks.getValue() == 0) {
            scoreTracking.setCounter(100);
            this.running = false;
        }
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new PauseScreen()));
        }
        if (counterBalls.getValue() == 0) {
            this.counterLives.decrease(1);
            if (counterLives.getValue() <= 0) {
                this.running = false;
            } else {
                this.run();
            }
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);
    }

    /**
     * the function run the game - start the animation loop.
     */
    public void playOneTurn() {
        this.createBallsOnTopOfPaddle(); // or a similar method
        this.runner.run(new CountdownAnimation(2.0, 3, this.sprites));
        // countdown before turn starts.
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    /**
     * the function run the game .
     */
    public void run() {
        this.playOneTurn();

    }

    /**
     * the function create Balls On Top Of Paddle .
     */
    public void createBallsOnTopOfPaddle() {


        int numBalls = this.level.numberOfBalls();
        Ball[] ball = new Ball[numBalls];
        //List<Velocity> velocityList = this.level.initialBallVelocities();
        Point[] center = this.level.setPointBalls();
        // adding 2 balls to the game.
        for (int i = 0; i < this.level.numberOfBalls(); i++) {
            ball[i] = new Ball(center[i], 5, java.awt.Color.white, environment);
            ball[i].setVelocity(this.velocityList.get(i));
            ball[i].addToGame(this);
            // define the another ball point.
            this.counterBalls.increase(1);
            BallRemover b = new BallRemover(this, this.counterBalls);
            ball[i].addHitListener(b);
        }

        paddle.removeFromGame(this);
        // adding the paddle.
        Rectangle paddleRectangle = new Rectangle((BOARD_WIDE / 2 - this.level.paddleWidth() / 2),
                BOARD_LENGHT - BLOCK_WIDTH + BLOCK_LEN,
                this.level.paddleWidth(),
                BLOCK_LEN);
        paddle = new Paddle(keyboard, paddleRectangle, this.level.paddleSpeed());
        paddle.addToGame(this);
    }

    /**
     * the function return the numbers the current blocks .
     *
     * @return the numbers of blocks.
     */
    public int getRemainBlocks() {
        return this.counterBlocks.getValue();
    }

    /**
     * the function return the numbers of lives at game.
     *
     * @return the number of lives
     */
    public int getLivesGame() {
        return this.counterLives.getValue();
    }
}