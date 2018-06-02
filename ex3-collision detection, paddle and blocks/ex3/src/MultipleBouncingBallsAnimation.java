import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.util.Random;
/**
 * class "MultipleBouncingBallsAnimation" accepts radius of the points
 * from the command Line prints the balls on the screen randomly.
 */
public class MultipleBouncingBallsAnimation {
    // Const parameters of the board and angle.
    public static final int BOARD_WIDE = 200;
    public static final int BOARD_LENGHT = 200;
    public static final int ANGLE = 360;
    /**
     * This main function of the class.
     * the function operate the bouncing ball by calling other function.
     * @param args - array of radius (from command line).
     */
    public static void main(String[] args) {
        MultipleBouncingBallsAnimation bouncingBall = new MultipleBouncingBallsAnimation();
        // operate bouncing balls.
        bouncingBall.ballAnimation(args);
    }
    /**
     * the function "ballAnimation" accepts array of radius and prints
     * balls randomly on the screen.
     * @param args is array of radius.
     * @throws RuntimeException checking the valid of the radius.
     */
    public void ballAnimation(String[] args) throws RuntimeException {
        int angle,  radius, pointX, pointY;
        int boardWide, boardLength;
        double speed;
        int minBoard;
        float colr1, colr2, colr3;
        Random rand = new Random();

        // Drawing balls on the screen
        GUI gui = new GUI("Multiple Bouncing Balls Animation", BOARD_WIDE, BOARD_LENGHT);
        Sleeper sleeper = new Sleeper();
        Ball[] ball = new Ball[args.length];
        // define random color, point, angle and speed each radius.
        for (int i = 0; i < args.length; i++) {
            angle = rand.nextInt(ANGLE) + 1;
            colr1 = rand.nextFloat();
            colr2 = rand.nextFloat();
            colr3 = rand.nextFloat();
            radius = Integer.parseInt(args[i]);
            // check the minimum board length or wide.
            boardWide = BOARD_WIDE;
            boardLength = BOARD_LENGHT;
            if (boardWide < boardLength) {
                minBoard = boardWide / 2;
                } else {
                minBoard = boardLength / 2;
                }
            // check if the radius is valid.
            if (radius > minBoard || radius < 0) {
                throw new RuntimeException("ERROR RADIUS BALL!");
            }
            // random point on the board.
            pointX = rand.nextInt(BOARD_WIDE - (2 * radius)) + 1 + radius;
            pointY = rand.nextInt(BOARD_LENGHT - (2 * radius)) + 1 + radius;
            // define speed.
            if (radius >= 50) {
                speed = 1;
                } else {
                speed = (50 - radius) / 4;
                }
            // create new ball.
            Point p1 = new Point(0, 0);
            Point p2 = new Point(200, 200);
            ball[i] = new Ball(pointX, pointY, radius, java.awt.Color.getHSBColor(colr1, colr2, colr3), p1, p2);
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            ball[i].setVelocity(v);
        }
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (int j = 0; j < ball.length; j++) {
                ball[j].moveOneStep();
                ball[j].drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(100); // wait for 50 milliseconds.
        }
    }
}
