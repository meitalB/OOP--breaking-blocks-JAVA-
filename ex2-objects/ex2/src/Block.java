import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Block implements Collidable {
    private Rectangle rect;
    private Color color;

    private static final int boundEdge = 1;
    // Return the "collision shape" of the object. 
    
    public Block(Rectangle rect) {
    	this.rect = rect;
    }
    

	public Block(Rectangle rec, Color black) {
    	this.rect = rect;
    	this.color = black;
	}


	public Rectangle getCollisionRectangle() {
       return this.rect;
    }

    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us). 
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        Velocity newVel = currentVelocity;
        List<Line> linesRect = new ArrayList<Line>();
        linesRect = rect.linesRectangle();
        if (linesRect.get(0).isPointInLine(collisionPoint) || linesRect.get(1).isPointInLine(collisionPoint)) {
        	newVel = new Velocity(currentVelocity.getDX(), currentVelocity.getDY() * -1);
        }
        if (linesRect.get(2).isPointInLine(collisionPoint) || linesRect.get(3).isPointInLine(collisionPoint)) {
        	newVel = new Velocity(currentVelocity.getDX() * -1, currentVelocity.getDY());
        }
        return newVel;
    }
    public void drawOn(DrawSurface surface) {
        surface.fillRectangle((int)rect.getUpperLeft().getX(), (int)rect.getUpperLeft().getY(), (int)rect.getWidth(), (int)rect.getHeight());
    }
}


