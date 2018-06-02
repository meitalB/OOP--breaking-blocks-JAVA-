import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
/**
 * class BouncingBallAnimation - draw an animation ball.
 */
public class BouncingBallAnimation {
    /**
     * This main function of the class.
     * the function operate the bouncing ball.
     * @param args - array of radius (from command line).
     */
    public static void main(String[] args) {
        GUI gui = new GUI("title", 200, 200);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(30, 30, 30, java.awt.Color.BLACK);
        ball.setVelocity(2, 2);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50); // wait for 50 milliseconds.
        }
    }
}
