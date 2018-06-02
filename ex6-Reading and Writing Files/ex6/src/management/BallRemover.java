package management;

import object.Ball;
import object.Block;

/**
 * class BallRemover is remove balls, and update
 * an counter balls.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;
    public static final int BOARD_WIDE = 800;
    public static final int BOARD_LENGHT = 600;
    public static final int BLOCK_LEN = 20;
    public static final int BLOCK_WIDTH = 60;

    /**
     * Construct a listener ball remover given the current game ,number of balls.
     *
     * @param game           is the current gameLevel.
     * @param remainingBalls is counter.
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.remainingBalls = remainingBalls;
        this.game = game;
    }

    /**
     * the function  remove the current ball from the game and update the balls counter.
     *
     * @param beingHit is block that sit at the bottom of the screen.
     * @param hitter   is the ball which thatch the beingHit.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}
