package management;

import geometricshape.Line;
import geometricshape.Point;
import geometricshape.Rectangle;
import information.CollisionInfo;
import object.Collidable;

import java.util.ArrayList;

/**
 * Class management.GameEnvironment - is a collection of all the objects in the game.
 */
public class GameEnvironment {
    private ArrayList<Collidable> collideList;
    /**
     * Constructor - define game environment with new array list of collidable.
     */
    public GameEnvironment() {
        this.collideList = new ArrayList<Collidable>();
    }
    /**
     * the function add the given collidable to the environment.
     *
     * @param c is the collidable object that we add.
     */
    public void addCollidable(Collidable c) {
        collideList.add(c);
    }
    /**
     * the function remove the given collidable to the environment.
     *
     * @param c is the collidable object that we remove.
     */
    public void removeCollidable(Collidable c) {
        collideList.remove(c);
    }

    /**
     * the function check if the object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     *
     * @param trajectory is the trajectory of the ball.
     * @return the infColl that is give information about the closet collision that will be.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        int i = 0;
        double minDistance = trajectory.end().distance(trajectory.start());
        double disPoint;
        Point p1 = null;
        Point minP1 = null;
        CollisionInfo infColl = null;
        int iMin = 0;
        while (i < collideList.size()) {
            // define new closet intersection point.
            Rectangle r1 = collideList.get(i).getCollisionRectangle();
            p1 = trajectory.closestIntersectionToStartOfLine(r1);
            // check if the point is not null.
            if (p1 != null) {
                // update the new distance.
                disPoint = p1.distance(trajectory.start());
                if (minDistance > disPoint) {
                    minDistance = disPoint;
                    minP1 = p1;
                    iMin = i;
                }
            }
            i++;
        }
        // update the new collision info.
        if (minP1 != null) {
            infColl = new CollisionInfo(minP1, collideList.get(iMin));
        }
        return infColl;
    }
}
