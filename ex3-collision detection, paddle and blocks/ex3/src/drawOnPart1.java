import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.util.Random;
import java.awt.Color;
/**
 * class "MultipleBouncingBallsAnimation" accepts radius of the points
 * from the command Line prints the balls on the screen randomly.
 */
public class drawOnPart1 {
    // Const parameters of the board and angle.
    public static final int BOARD_WIDE = 400;
    public static final int BOARD_LENGHT = 400;
    public static final int ANGLE = 360;

    /**
     * This main function of the class.
     * the function operate the bouncing ball by calling other function.
     * @param args - array of radius (from command line).
     */
    public static void main(String[] args) {
        drawOnPart1 bouncingBall = new drawOnPart1();
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
        Ball ball;
        
        
        Point upperLeft1 = new Point(100, 150);
        Rectangle rect1 = new Rectangle(upperLeft1, 50, 80);
        Block block1 = new Block(rect1);
        
        Point upperLeft2 = new Point(250, 300);
        Rectangle rect2 = new Rectangle(upperLeft2, 50, 80);
        Block block2 = new Block(rect2);
        
        Point upperLeft3 = new Point(200, 250);
        Rectangle rect3 = new Rectangle(upperLeft3, 50, 80);
        Block block3 = new Block(rect3);
        
        GameEnvironment newGame = new GameEnvironment();
        newGame.addCollidable(block1);
        newGame.addCollidable(block2);
        newGame.addCollidable(block3);
        
        
        
        
        // define random color, point, angle and speed each radius.
            angle = rand.nextInt(ANGLE) + 1;
            colr1 = rand.nextFloat();
            colr2 = rand.nextFloat();
            colr3 = rand.nextFloat();
            radius = 5;
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
            Point p2 = new Point(400, 400);
            ball = new Ball(pointX, pointY, radius, java.awt.Color.getHSBColor(colr1, colr2, colr3), p1, p2, newGame);
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            ball.setVelocity(v);
            
            
            
        while (true) {
            DrawSurface d = gui.getDrawSurface();
                ball.moveOneStep();
                ball.drawOn(d);
                block1.drawOn(d);
                block2.drawOn(d);
                block3.drawOn(d);
                d.setColor(Color.BLACK);
                
            gui.show(d);
            sleeper.sleepFor(100); // wait for 50 milliseconds.
        }
    }
}