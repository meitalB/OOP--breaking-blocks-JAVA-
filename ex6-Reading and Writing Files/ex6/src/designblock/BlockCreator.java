package designblock;

import object.Block;

/**
 * interface BlockCreator - is an interface that has a method to create
 * a block with specified location.
 */
public interface BlockCreator {
    /**
     * The function create an new block.
     * @param xpos is the x specified location.
     * @param ypos is the y specified location.
     * @return the new block.
     */
    Block create(int xpos, int ypos);
}