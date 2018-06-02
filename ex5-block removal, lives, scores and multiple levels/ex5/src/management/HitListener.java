package management;

import object.Ball;
import object.Block;
/**
 * interface hitLIstener - is interface that announced when object is hit.
 *
 */
public interface HitListener {
    /**
     *the function is called whenever the beingHit object is hit.
     * @param beingHit is the block that hurt
     * @param hitter is the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
