package management;

import object.Ball;
import object.Block;

/**
 * Class ScoreTrackingListener is a listener of the score in the game.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Construct a ScoreTrackingListener according to parameters that recieved.
     *
     * @param scoreCounter is the score counter to draw into the block on the screen.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * the function is called whenever the beingHit object is hit.
     *
     * @param beingHit is the block that hurt
     * @param hitter   is the Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitsPoint() != 0) {
            this.currentScore.increase(5);
        } else {
            this.currentScore.increase(10);
        }
    }

    /**
     * the function is set the counter score.
     *
     * @param num is the score to change.
     */
    public void setCounter(int num) {
        this.currentScore.increase(num);
    }

    /**
     * the function is get the counter score.
     *
     * @return the score to change.
     */
    public int getCounter() {
        return this.currentScore.getValue();
    }
}
