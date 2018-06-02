package object;

import java.awt.Color;
import java.util.List;

import geometricshape.Line;
import geometricshape.Point;
import geometricshape.Rectangle;
import information.Velocity;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import management.GameLevel;

/**
 * class Paddle - is a paddle object can move right and left.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddle;
    public static final int BOARD_WIDE = 800;
    public static final int BOARD_LENGHT = 600;
    public static final int MOVE_PADDLE = 300;
    private int speed = 5;
    public static final int BLOCK_LEN = 20;

    /**
     * construct a paddle according to upperleft point, width, height, and keyboard sensor.
     *
     * @param keyboard is the keyboard sensor of the paddle.
     * @param paddle   is the rectangle shape.
     * @param speed    is of the paddle.
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Rectangle paddle, int speed) {
        this.keyboard = keyboard;
        this.paddle = paddle;
        this.speed = speed;
    }

    /**
     * the function move the paddle to left.
     *
     * @param dt is the dt value.
     */
    public void moveLeft(double dt) {
        Point pRectleft;
        Rectangle newRect;
        // checking if the move left is in the surface.
        if (this.paddle.getUpperLeft().getX() - MOVE_PADDLE * dt >= BLOCK_LEN) {
            pRectleft = new Point(this.paddle.getUpperLeft().getX() - MOVE_PADDLE * dt,
                    this.paddle.getUpperLeft().getY());
            newRect = new Rectangle(pRectleft, this.paddle.getWidth(), this.paddle.getHeight());
            this.paddle = newRect;
            // move the paddle to the first point of the boundary.
        } else {
            pRectleft = new Point(BLOCK_LEN, this.paddle.getUpperLeft().getY());
            newRect = new Rectangle(pRectleft, this.paddle.getWidth(), this.paddle.getHeight());
        }
    }

    /**
     * the function move the paddle to Right.
     *
     * @param dt is the dt value.
     */
    public void moveRight(double dt) {
        Point pRectRight;
        Rectangle newRect;
        // checking if the move right is in the surface.
        if (this.paddle.getUpperRight().getX() + MOVE_PADDLE * dt <= BOARD_WIDE - BLOCK_LEN) {
            pRectRight = new Point(this.paddle.getUpperLeft().getX() + MOVE_PADDLE * dt,
                    this.paddle.getUpperRight().getY());
            newRect = new Rectangle(pRectRight, this.paddle.getWidth(), this.paddle.getHeight());
            this.paddle = newRect;
            // move the paddle to the last point of the boundary.
        } else {
            pRectRight = new Point(BOARD_WIDE - this.paddle.getWidth() - BLOCK_LEN, this.paddle.getUpperRight().getY());
            newRect = new Rectangle(pRectRight, this.paddle.getWidth(), this.paddle.getHeight());
        }
    }

    /**
     * implements of object.Sprite-  the function call moveLeft or moveRight function.
     * according to which key board was pressed.
     *
     * @param dt is the dt value.
     */
    public void timePassed(double dt) {
        // check if press left.
        if ((keyboard.isPressed(KeyboardSensor.LEFT_KEY)
                && (!keyboard.isPressed(KeyboardSensor.RIGHT_KEY)))) {
            this.moveLeft(dt);
        }
        // check if press right.
        if ((keyboard.isPressed(KeyboardSensor.RIGHT_KEY)
                && (!keyboard.isPressed(KeyboardSensor.LEFT_KEY)))) {
            this.moveRight(dt);
        }
    }

    /**
     * implements of object.Sprite- the function draws a paddle on the surface.
     *
     * @param d is the surface to be draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.yellow);
        d.fillRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
    }

    /**
     * implements of  collidable  - the function return the collision rectangle.
     *
     * @return the collision rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    /**
     * implements of  collidable - the function calculate new velocity of the ball
     * according to the place were the collision.
     * occuredits current velocity, and .
     *
     * @param collisionPoint  is the point were the collision occur.
     * @param currentVelocity is the current velocity of the ball.
     * @param hitter          is the current  ball.
     * @return the new velocity.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVel = currentVelocity;
        // devide the paddle to 5 parts.
        double wideAfterSeperate = this.paddle.getWidth() / 5;
        Point[] numberPart = new Point[5];
        double newSpeed = Math.sqrt(Math.pow(currentVelocity.getDX(), 2)
                + Math.pow(currentVelocity.getDY(), 2));
        // define the start point of each part of the paddle.
        for (int i = 0; i < 5; i++) {
            numberPart[i] = new Point(this.paddle.getUpperLeft().getX() + (wideAfterSeperate * i),
                    this.paddle.getUpperLeft().getY());
        }
        List<Line> paddleLines = this.paddle.linesRectangle();

        if (paddleLines.get(2).isPointInLine(collisionPoint)
                || paddleLines.get(3).isPointInLine(collisionPoint)) {
            newVel = new Velocity(currentVelocity.getDX() * -1, currentVelocity.getDY());
        } else {
            // part 1 of the paddle.
            if (collisionPoint.getX() >= numberPart[0].getX() && collisionPoint.getX() < numberPart[1].getX()) {
                newVel = Velocity.fromAngleAndSpeed(-60, newSpeed);
                // part 2 of the paddle.
            } else if (collisionPoint.getX() >= numberPart[1].getX() && collisionPoint.getX() < numberPart[2].getX()) {
                newVel = Velocity.fromAngleAndSpeed(-30, newSpeed);
                // part 3 of the paddle.
            } else if (collisionPoint.getX() >= numberPart[2].getX() && collisionPoint.getX() < numberPart[3].getX()) {
                newVel = new Velocity(currentVelocity.getDX(), currentVelocity.getDY() * -1);
                // part 4 of the paddle.
            } else if (collisionPoint.getX() >= numberPart[3].getX() && collisionPoint.getX() < numberPart[4].getX()) {
                newVel = Velocity.fromAngleAndSpeed(30, newSpeed);
                // part 5 of the paddle.
            } else if (collisionPoint.getX() >= numberPart[4].getX()
                    && collisionPoint.getX() <= this.paddle.getUpperRight().getX()) {
                newVel = Velocity.fromAngleAndSpeed(60, newSpeed);
            }
        }
        return newVel;
    }

    /**
     * the function add this paddle to the game.
     *
     * @param g is a new game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * the function remove this paddlefrom  the game.
     *
     * @param game is a new game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }
}