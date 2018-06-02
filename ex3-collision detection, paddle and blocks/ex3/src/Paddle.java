import java.awt.Color;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * class Paddle - is a paddle object can move right and left.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddle;
    public static final int BOARD_WIDE = 900;
    public static final int BOARD_LENGHT = 500;
    public static final int MOVE_PADDLE = 10;
    public static final int SPEED = 5;
    public static final int BLOCK_LEN = 20;
    /**
     * construct a paddle according to upperleft point, width, height, and keyboard sensor.
     * @param keyboard is the keyboard sensor of the paddle.
     * @param paddle is the rectangle shape.
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Rectangle paddle) {
        this.keyboard = keyboard;
        this.paddle = paddle;
    }
    /**
     * the function move the paddle to left.
     */
    public void moveLeft() {
        Point pRectleft;
        Rectangle newRect;
        if (this.paddle.getUpperLeft().getX() - MOVE_PADDLE >= BLOCK_LEN) {
            pRectleft = new Point(this.paddle.getUpperLeft().getX() - MOVE_PADDLE, this.paddle.getUpperLeft().getY());
            newRect = new Rectangle(pRectleft, this.paddle.getWidth(), this.paddle.getHeight());
            this.paddle = newRect;
        } else {
            pRectleft = new Point(BLOCK_LEN, this.paddle.getUpperLeft().getY());
            newRect = new Rectangle(pRectleft, this.paddle.getWidth(), this.paddle.getHeight());
        }

    }
    /**
     * the function move the paddle to Right.
     */
    public void moveRight() {
        Point pRectRight;
        Rectangle newRect;
        if (this.paddle.getUpperRight().getX() + MOVE_PADDLE <= BOARD_WIDE - BLOCK_LEN) {
            pRectRight = new Point(this.paddle.getUpperLeft().getX() + MOVE_PADDLE,
                    this.paddle.getUpperRight().getY());
            newRect = new Rectangle(pRectRight, this.paddle.getWidth(), this.paddle.getHeight());
            this.paddle = newRect;
        } else {
            pRectRight = new Point(BOARD_WIDE - this.paddle.getWidth() - BLOCK_LEN, this.paddle.getUpperRight().getY());
            newRect = new Rectangle(pRectRight, this.paddle.getWidth(), this.paddle.getHeight());
        }
    }
    /**
     * implements of Sprite-  the function call moveLeft or moveRight function.
     * according to which key board was pressed.
     */
    // Sprite
   public void timePassed() {
       if ((keyboard.isPressed(KeyboardSensor.LEFT_KEY)
               && (!keyboard.isPressed(KeyboardSensor.RIGHT_KEY)))) {
           this.moveLeft();
         }
       if ((keyboard.isPressed(KeyboardSensor.RIGHT_KEY)
               && (!keyboard.isPressed(KeyboardSensor.LEFT_KEY)))) {
           this.moveRight();
         }
   }
   /**
    * implements of Sprite- the function draws a paddle on the surface.
    * @param d is the surface to be draw on.
    */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.yellow);
        d.fillRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
    }
    /**
     * implements of  collidable  - the function return the collision rectangle.
     * @return the collision rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }
    /**
     * implements of  collidable - the function calculate new velocity of the ball
     * according to the place were the collision.
     * occuredits current velocity, and .
     * @param collisionPoint is the point were the collision occur.
     * @param currentVelocity is the current velocity of the ball.
     * @return the new velocity.
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        Velocity newVel = currentVelocity;
        double wideAfterSeperate = this.paddle.getWidth() / 5;
        Point[] numberPart = new Point[5];
        for (int i = 0; i < 5; i++) {
            numberPart[i] = new Point(this.paddle.getUpperLeft().getX() + (wideAfterSeperate * i),
                    this.paddle.getUpperLeft().getY());
        }
        if (collisionPoint.getY() > 0 && collisionPoint.getY() < this.paddle.getUpperLeft().getY()) {
            newVel = new Velocity(currentVelocity.getDX() * -1, currentVelocity.getDY());
        } else {
            // part 1
            if (collisionPoint.getX() >= numberPart[0].getX() && collisionPoint.getX() < numberPart[1].getX()) {
                newVel = currentVelocity.fromAngleAndSpeed(300, SPEED);
                // part 2
            } else if (collisionPoint.getX() >= numberPart[1].getX() && collisionPoint.getX() < numberPart[2].getX()) {
                newVel = currentVelocity.fromAngleAndSpeed(330, SPEED);
                // part 3
            } else if (collisionPoint.getX() >= numberPart[2].getX() && collisionPoint.getX() < numberPart[3].getX()) {
                newVel = new Velocity(currentVelocity.getDX(), currentVelocity.getDY() * -1);
                // part 4
            } else if (collisionPoint.getX() >= numberPart[3].getX() && collisionPoint.getX() < numberPart[4].getX()) {
                newVel = currentVelocity.fromAngleAndSpeed(30, SPEED);
                // part 5
            } else if (collisionPoint.getX() >= numberPart[4].getX()
                    && collisionPoint.getX() <= this.paddle.getUpperRight().getX()) {
                newVel = currentVelocity.fromAngleAndSpeed(60, SPEED);
            }
        }
        return newVel;
    }
    /**
     * the function add this paddle to the game.
     * @param g is a new game.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}