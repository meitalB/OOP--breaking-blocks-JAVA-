package management;

import object.Ball;
import object.Block;

/**
 * BlockRemover class suppose remove blocks from the game,
 * and count of the number of blocks that were removed.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * construct a listener block remover give the game and  number of blocks.
     *
     * @param game          is the current game
     * @param removedBlocks is count the number of blocksthat remove.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.remainingBlocks = removedBlocks;
        this.game = game;
    }

    /**
     * the function removed the blocks when they are hit to 0 hit-points.
     * from the game.
     *
     * @param beingHit is the block that hit.
     * @param hitter   is the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitsPoint() == 0) {
            beingHit.removeFromGame(game);
            beingHit.removeHitListener(this);
            this.remainingBlocks.decrease(1);
        }
    }
}