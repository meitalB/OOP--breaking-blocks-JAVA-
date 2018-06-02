import java.util.ArrayList;

import biuoop.DrawSurface;

public class SpriteCollection {
    private ArrayList<Sprite> spriteList;

    public SpriteCollection() {
        this.spriteList = new ArrayList<Sprite>();
    }
    public int getSizeSpriteCollection() {
       return this.spriteList.size();
    }
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    // call timePassed() on all sprites.
    public void notifyAllTimePassed() {
        for (int i = 0; i < this.spriteList.size(); i++) {
            this.spriteList.get(i).timePassed();
        }
    }

    // call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < this.spriteList.size(); i++) {
            this.spriteList.get(i).drawOn(d);
        }
    }
}