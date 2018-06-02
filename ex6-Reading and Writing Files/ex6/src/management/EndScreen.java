package management;

import biuoop.DrawSurface;


/**
 * Class EndScreen implements Animation and display a screen with
 * the message of termination in accordance with the state of the game.
 */
public class EndScreen implements Animation {
    private Boolean win;
    private int score;
    private boolean stop;

    /**
     * Construct a EndScreen according to a KeyboardSensor that ws recieved.
     *
     * @param score is the score of the game
     * @param win   indicates whether player won or lost
     */
    public EndScreen(Boolean win, int score) {
        this.win = win;
        this.score = score;
        this.stop = false;
    }

    /**
     * the function check if the animation have to stop.
     *
     * @return false if it should stop
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * The function draws a one frame with the pause message on the screen.
     *@param dt is for the frame drawing.
     * @param d is the surface to be draw on it.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        if (win) {
            d.drawText(10, d.getHeight() / 2,
                    "You Win! Your score is " + score, 32);
        } else {
            d.drawText(10, d.getHeight() / 2,
                    "Game Over. Your score is " + score, 32);
        }

    }
}
