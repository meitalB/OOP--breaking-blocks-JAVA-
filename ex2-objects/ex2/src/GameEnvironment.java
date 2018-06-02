import java.util.ArrayList;

public class GameEnvironment {
    private ArrayList<Collidable> collideList;

    public GameEnvironment() {
    	this.collideList = new ArrayList<Collidable>();
    }
    // add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        collideList.add(c);
    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory) {
        int i = 0;
        double minDistance = trajectory.end().distance(trajectory.start());
        double disPoint;
        Point p1 = null;
        Point minP1 = null;
        CollisionInfo infColl = null;
        int iMin = 0;

        while (i < collideList.size()) {
            Rectangle r1 = collideList.get(i).getCollisionRectangle();
            p1 = trajectory.closestIntersectionToStartOfLine(r1);
            if (p1 != null) {
            disPoint = p1.distance(trajectory.start());
                if (minDistance > disPoint) {
                    minDistance = disPoint;
                    minP1 = p1;
                    iMin = i;
                }
            }
            i++;
        }
        if (minP1 != null) {
            infColl = new CollisionInfo(minP1, collideList.get(iMin));
        }
        return infColl;
    }
}
