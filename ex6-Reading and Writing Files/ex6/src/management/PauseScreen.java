package management;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Class PauseScreen implements Animation. will display a screen with
 * the message of pausing until a key is pressed.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Construct a pauseScreen according to a KeyboardSensor recieved.
     */
    public PauseScreen() {
        this.stop = false;
    }

    /**
     * The function draws a one frame with the pause message on the screen.
     *
     * @param d  is the surface to be draw on it.
     * @param dt is the dt value.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**
     * the function check if this animation should be stop.
     *
     * @return false if it should stop
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
