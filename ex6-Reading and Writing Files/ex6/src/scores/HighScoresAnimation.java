package scores;

import biuoop.DrawSurface;
import management.Animation;
import java.awt.Color;

/**
 * class HighScoresAnimation - is a display the high scores animation.
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable scores;
    private String endKey;
    private boolean stop;

    /**
     * Construct a MenuAnimation according to a KeyboardSensor recieved.
     *
     * @param scores is the HighScoresAnimation.
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.scores = scores;
        this.stop = false;
    }

    /**
     * This function's responsible of doing frame of the game.
     *
     * @param d  is the DrawSurface to draw on
     * @param dt is the dt value.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.black);
        d.drawText(d.getWidth() / 3, 100, "High Scores", 36);
        d.setColor(Color.blue);
        d.drawLine(d.getWidth() / 3, 120, d.getWidth() / 3 + 100, 120);
        int move = 20;
        for (ScoreInfo sc : this.scores.getHighScores()) {
            String strScore = sc.getName() + ": " + sc.getScore();
            d.drawText(d.getWidth() / 3, 120 + move, strScore, 24);
            move += 30;
        }
    }

    /**
     * the function check if this animation should be stop.
     *
     * @return false if it should stop
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
