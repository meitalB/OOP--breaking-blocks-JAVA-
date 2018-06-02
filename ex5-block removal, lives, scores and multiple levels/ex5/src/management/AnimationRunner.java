package management;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
/**
 * Class AnimationRunner - responsible to run the current Animation object.
 *
 */
public class AnimationRunner  {
    private GUI gui;
    private int framesPerSecond;
    private biuoop.Sleeper sleeper;
    public static final int BOARD_WIDE = 800;
    public static final int BOARD_LENGHT = 600;
    /**
     * Construct to AnimationRunner.
     */
    public AnimationRunner() {
     // create the new GUI of the game.
        this.gui = new GUI("Bricks-Game", BOARD_WIDE, BOARD_LENGHT);
        this.framesPerSecond = 60;
    }
    /**
     * The function runs the current Animation.
     * @param animation a object to be run.
     */
    public void run(Animation animation) {
       this.sleeper = new Sleeper();
       int millisecondsPerFrame = 1000 / framesPerSecond;
       while (!animation.shouldStop()) {
          long startTime = System.currentTimeMillis(); // timing
          DrawSurface d = gui.getDrawSurface();
          animation.doOneFrame(d);
          gui.show(d);
          long usedTime = System.currentTimeMillis() - startTime;
          long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
          if (milliSecondLeftToSleep > 0) {
              this.sleeper.sleepFor(milliSecondLeftToSleep);
          }
       }
    }
    /**
     * The function returns gui.
     * @return a gui.
     */
    public GUI getGui() {
        return gui;
    }
    /**
     * The function returns KeyboardSensor.
     * @return a KeyboardSensor.
     */
    public biuoop.KeyboardSensor getKeyboard() {
       return this.gui.getKeyboardSensor();
    }
 }

