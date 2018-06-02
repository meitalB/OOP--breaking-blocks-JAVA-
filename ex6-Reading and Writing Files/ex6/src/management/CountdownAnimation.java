package management;

import spritecollect.SpriteCollection;
import biuoop.DrawSurface;

/**
 * The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) secods, before
 * it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private long startTime;
    private double timeStsticDisplay;
    private int numDisplay;
    private double timeChangeDisplay;

    /**
     * Construct a CountdownAnimation according to given parameters.
     *
     * @param numOfSeconds is the number of seconds to display the gameScreen
     * @param countFrom    is the number to be count from
     * @param gameScreen   is the screen to be display
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.startTime = System.currentTimeMillis();
        this.numDisplay = countFrom;
        this.timeChangeDisplay = numOfSeconds / countFrom;
        this.timeStsticDisplay = numOfSeconds / countFrom;
        ;
    }

    /**
     * The function  run a one frame.
     *@param dt is for the farme draw
     * @param d is the surface to display on.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.gameScreen.drawAllOn(d);
        long timePass = (System.currentTimeMillis() - this.startTime) / 1000;
        if (timePass >= timeChangeDisplay) {
            this.numDisplay--;
            this.timeChangeDisplay += this.timeChangeDisplay;
        }
        if (timePass > this.numOfSeconds) {
            this.numDisplay = 0;
            this.stop = true;
        }
        d.drawText(d.getWidth() / 2, d.getHeight() / 2,
                String.valueOf(this.numDisplay), 48);
    }

    /**
     * the function check if this animation have to stop.
     *
     * @return false if it have stop
     */
    public boolean shouldStop() {
        return this.stop;
    }
}