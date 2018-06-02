package object;

import biuoop.DrawSurface;
import management.GameLevel;
/**
 * interface sprite - is an interface that draws objects by drawOn,
 * and make the sprite time passe.
 */
public interface Sprite {
   /**
    * the function draw the sprite to the screen.
    * @param d is the surface drawing.
    */
   void drawOn(DrawSurface d);
   /**
    * this function notify the sprite that time has passed.
    */
   void timePassed();
   /**
    * the function add ball to the game.
    * @param gameLevel is a new game.
    */
   void addToGame(GameLevel gameLevel);
}