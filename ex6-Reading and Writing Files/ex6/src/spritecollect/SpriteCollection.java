package spritecollect;

import java.util.ArrayList;

import biuoop.DrawSurface;
import object.Sprite;
/**
 * class spritecollect.SpriteCollection- is a collections of sprites objects.
 * the class has some functions on it.
 */
public class SpriteCollection {
    private ArrayList<Sprite> spriteList;
    /**
     * constructor - creates new list of sprites.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<Sprite>();
    }
    /**
     * the function add one sprite to the list of sprites.
     * @param s is the sprite that need add to the list.
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }
    /**
     * the function remove one sprite to the list from sprites.
     * @param s is the sprite that need remove from the list.
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }
    /**
     * the function calls timePassed functions for all the sprites.
     * @param dt is the dt value.
     */
    public void notifyAllTimePassed(double dt) {
        for (int i = 0; i < this.spriteList.size(); i++) {
            this.spriteList.get(i).timePassed(dt);
        }
    }
    /**
     * the function draw each one of the sprites.
     * @param d is the draw surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < this.spriteList.size(); i++) {
            this.spriteList.get(i).drawOn(d);
        }
    }
}