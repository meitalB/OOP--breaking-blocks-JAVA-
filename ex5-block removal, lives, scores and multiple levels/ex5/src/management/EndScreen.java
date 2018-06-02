package management;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Class EndScreen implements Animation and display a screen with
 * the message of termination in accordance with the state of the game.
 *
 */
public class EndScreen implements Animation {
    private Boolean win;
    private KeyboardSensor k;
    private int score;
    private AnimationRunner runner;
    /**
     * Construct a EndScreen according to a KeyboardSensor that ws recieved.
     * @param score is the score of the game
     * @param win indicates whether player won or lost
     * @param k is the KeyboardSensor be initialize.
     * @param runner is the animation of the game.
     */
    public EndScreen(Boolean win, KeyboardSensor k, int score, AnimationRunner runner) {
        this.win = win;
        this.k = k;
        this.score = score;
        this.runner = runner;
    }
    /**
     * the function check if the animation have to stop.
     * @return false if it should stop
     */
    @Override
    public boolean shouldStop() {
        return false;
    }
    /**
     * The function draws a one frame with the pause message on the screen.
     * @param d is the surface to be draw on it.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        if (win) {
            d.drawText(10, d.getHeight() / 2,
                    "You Win! Your score is " + score, 32);
        } else {
            d.drawText(10 , d.getHeight() / 2,
                    "Game Over. Your score is " + score, 32);
        }
        if (this.k.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.runner.getGui().close();
        }
    }
}
