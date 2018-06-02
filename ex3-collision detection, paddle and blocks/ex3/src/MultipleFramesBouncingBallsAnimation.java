import java.awt.Color;
import biuoop.GUI;
import biuoop.Sleeper;
import biuoop.DrawSurface;
import java.util.Random;
/**
 * MultipleBouncingBallsAnimation.
 * The program  accepts from the command Line and radius.
 * and prints the first half of the balls to bounce inside the first frame.
 *and the second half of the balls to bounce inside the second frame.
 * their balls randomly on the screencreate two frames.
 */
public class MultipleFramesBouncingBallsAnimation {
    /**
     * This main function of the class.
     * the function operate the frames  bouncing ball by calling other function.
     * @param args - array of radius (from command line).
     */
    public static void main(String[] args) {
        MultipleFramesBouncingBallsAnimation framesOfBall =
                new MultipleFramesBouncingBallsAnimation();
        framesOfBall.frames(args);
    }
    /**
     * the function "frames" accepts array of radius and prints
     * balls randomly on the screen in to frames.
     * @param args is array of radius.
     */
    public void frames(String[] args) {
        GUI gui = new GUI("MultipleFrame", 600, 600);
        Sleeper sleeper = new Sleeper();
        DrawSurface dFirst = gui.getDrawSurface();
        String[] arrFirst = split(args, 0, (args.length / 2) - 1);
        String[] arrSecond = split(args, (args.length / 2), args.length - 1);
        this.ballAnimation(arrFirst, arrSecond, dFirst, gui, sleeper, Color.red);
        gui.show(dFirst);
    }
    /**
     * the function "ballAnimation" accepts two arrays of radius and prints
     * balls randomly on the screen.
     * @param firstArgs is the first array of radius.
     * @param secondArgs is the second array of radius.
     * @param surface is is the DrawSurface we work on.
     * @param gui is graphic part.
     * @param sleeper is wait for milliseconds..
     * @param c is the color.
     * @throws RuntimeException checking the valid of the radius.
     */
    public void ballAnimation(String[] firstArgs, String[] secondArgs ,
                              DrawSurface surface, GUI gui, Sleeper sleeper, Color c)
                              throws RuntimeException {
        Ball[]firstBall = defineArray(firstArgs, 50, 50, 500, 500);
        Ball[]secondBall = defineArray(firstArgs, 450, 450, 600, 600);
        while (true) {
            surface = gui.getDrawSurface();
            surface.setColor(Color.gray);
            surface.drawRectangle(50, 50, 450, 450);
            surface.setColor(Color.yellow);
            surface.drawRectangle(450, 450, 150, 150);
            surface.setColor(Color.blue);
            for (int j = 0; j < firstBall.length; j++) {
                firstBall[j].moveOneStep();
                secondBall[j].moveOneStep();
                firstBall[j].drawOn(surface);
                secondBall[j].drawOn(surface);
            }
            gui.show(surface);
            sleeper.sleepFor(24); // wait for 50 milliseconds.
        }
    }
    /**
     * the function "defineArray" accepts array of radius
     * and bonders and define array of balls.
     * @param args is the  array of radius.
     * @param bounerUpLeftX is x of the up left x.
     * @param bounerUpLeftY is y of the up left Y.
     * @param bounerDownRightX is x of the down right x.
     * @param bounerDownRightY is y of the down right y.
     * @return array of balls.
     */
public Ball [] defineArray(String[] args, int bounerUpLeftX, int bounerUpLeftY,
            int bounerDownRightX, int bounerDownRightY) {
        int angle, radius, pointX, pointY;
        double speed;
        int minBoard;
        float colr1, colr2, colr3;
        Random rand = new Random();
        Point bounderUpLeft = new Point(bounerUpLeftX, bounerUpLeftY);
        Point bounderDownRight = new Point(bounerDownRightX, bounerDownRightY);
        Ball[] ball = new Ball[args.length];
        for (int i = 0; i < args.length; i++) {
            angle = rand.nextInt(MultipleBouncingBallsAnimation.ANGLE) + 1;
            colr1 = rand.nextFloat();
            colr2 = rand.nextFloat();
            colr3 = rand.nextFloat();

            radius = Integer.parseInt(args[i]);
            int bounerWight = (int) (bounderDownRight.getX() - bounderUpLeft.getX());
            int bounderLength = (int) (bounderDownRight.getY() - bounderUpLeft.getY());
            if (bounerWight < bounderLength) {
                minBoard = bounerWight / 2;
            } else {
                minBoard = bounderLength / 2;
            }
            if (radius > minBoard || radius < 0) {
                throw new RuntimeException("ERROR RADIUS BALL!");
            }
            int maxRanX = (int) bounderDownRight.getX() + 1;
            int minRanX = (int) bounderUpLeft.getX() + 1;
            pointX = rand.nextInt(maxRanX - minRanX) + minRanX;

            while ((pointX - radius <= bounderUpLeft.getX())
                  || (pointX + radius >= bounderDownRight.getX())) {
                pointX = rand.nextInt(maxRanX - minRanX) + minRanX;
            }
             int maxRanY = (int) bounderDownRight.getY() + 1;
             int minRanY = (int) bounderUpLeft.getY() + 1;
             pointY = rand.nextInt(maxRanY - minRanY) + minRanY;
             while ((pointY - radius <= bounderUpLeft.getY())
                   || (pointY + radius >= bounderDownRight.getY())) {
                  pointY = rand.nextInt(maxRanY - minRanY) + minRanY;
            }
            if (radius >= 50) {
                speed = 1;
            } else {
                speed = (50 - radius) / 4;
            }
            ball[i] = new Ball(pointX, pointY, radius,
                               java.awt.Color.getHSBColor(colr1, colr2, colr3),
                               bounderUpLeft, bounderDownRight);
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            ball[i].setVelocity(v);
        }
        return ball;
    }
    /**
     * The function split the array to two array.
     * @param args is augment from the commend line.
     * @param start is the start of the array.
     * @param end is the end of the array.
     * @return string array
     */
    public String[] split(String[] args, int start, int end) {
        String[] stringsArray = new String[end - start + 1];
        int temp = start;
        for (int i = 0; i < stringsArray.length; i++) {
            stringsArray[i] = args[temp];
            temp++;
        }
        return stringsArray;
    }
}
