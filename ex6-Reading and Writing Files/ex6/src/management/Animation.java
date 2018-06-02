package management;

import biuoop.DrawSurface;

/**
 * Animation is an interface to keep the current object
 * that should be run.
 */
public interface Animation {
    /**
     * This function's responsible of  current frame of the game.
     *
     * @param d  is the DrawSurface to draw on.
     * @param dt is the dt value.
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * the function check if this animation have to stop.
     *
     * @return false if it have stop
     */
    boolean shouldStop();
}