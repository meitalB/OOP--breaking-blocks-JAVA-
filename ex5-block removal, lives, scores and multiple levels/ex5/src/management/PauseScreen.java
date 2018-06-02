package management;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * Class PauseScreen implements Animation. will display a screen with
 * the message of pausing until a key is pressed.
 *
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    /**
     * Construct a pauseScreen according to a KeyboardSensor recieved.
     * @param k is the KeyboardSensor be initialize.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }
    /**
     * The function draws a one frame with the pause message on the screen.
     * @param d is the surface to be draw on it.
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10 , d.getHeight() / 2 , "paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }
    /**
     * the function check if this animation should be stop.
     * @return false if it should stop
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
