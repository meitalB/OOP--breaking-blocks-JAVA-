package object;

import geometricshape.Point;
import geometricshape.Rectangle;
import information.Velocity;

/**
 * interface collidable - is an interface of the objects and points we can collide with them.
 */
public interface Collidable {
    /**
     * the function return collision shape of the object.
     * @return the collision shape of the object.
     */
    Rectangle getCollisionRectangle();
    /**
     *  the function notify the object that we collided with it at collisionPoint with
     * a given velocity.
     *  @param collisionPoint is the point of the collide was.
     *  @param hitter is the current ball.
     *  @param currentVelocity is the current velocity of the ball that collide.
     *  @return The return is the new velocity expected after the hit .
     *  (based on the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
