package information;

import geometricshape.Point;
import object.Collidable;

/**
 * class Collisioninfo - is an object of collide point and collide object.
 */
public class CollisionInfo {
    private Point p1;
    private Collidable collisionObject;

    /**
     * Constructor the collision-info with point of collide and the object.
     *
     * @param p1              is the point of the collision.
     * @param collisionObject is the type of collide object.
     */
    public CollisionInfo(Point p1, Collidable collisionObject) {
        this.p1 = p1;
        this.collisionObject = collisionObject;
    }

    /**
     * The function returns the point of the collision.
     *
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.p1;
    }

    /**
     * The function returns the collide object involved in the collision.
     *
     * @return the collide object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
