
public class CollisionInfo {
    private Point p1;
    private Collidable collisionObject;
        

        public CollisionInfo(Point p1, Collidable collisionObject) {
            this.p1 = p1;
            this.collisionObject = collisionObject;    
        }
        // the point at which the collision occurs.
        public Point collisionPoint() {
            return this.p1;
        }

        // the collidable object involved in the collision.
        public Collidable collisionObject() {
            return this.collisionObject;
            
        }
}
