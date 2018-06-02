package management;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Class KeyPressStoppableAnimation implements Animation.
 * KeyPressStoppableAnimation is the key press.
 */
public class KeyPressStoppableAnimation implements Animation {
    private Animation animation;
    private KeyboardSensor k;
    private String key;
    private boolean stop;
    private boolean isAlreadyPressed = true;

    /**
     * Construct a KeyPressStoppableAnimation according to the parameters.
     *
     * @param sensor    is the key sensor.
     * @param key       is the string of key.
     * @param animation is an animation runner.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.k = sensor;
        this.key = key;
        this.animation = animation;
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
        if (!this.k.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }
        this.animation.doOneFrame(d, dt);
        if (!isAlreadyPressed) {
            if (this.k.isPressed(KeyboardSensor.SPACE_KEY)) {
                this.stop = true;
            }
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