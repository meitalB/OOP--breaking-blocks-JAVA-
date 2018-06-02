package designblock;

import object.Block;

import java.util.HashMap;
import java.util.Map;

/**
 * class BlocksFromSymbolsFactory - has the blocks and spacers of the game.
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * Constructor - define a new hash maps of blocks ans spaces to null.
     */
    public BlocksFromSymbolsFactory() {
        this.spacerWidths = new HashMap<String, Integer>();
        this.blockCreators = new HashMap<String, BlockCreator>();
    }

    /**
     * The function return true or false if there is valid spacer symbol.
     *
     * @param s is the string space.
     * @return true if 's' is a valid space symbol.
     */
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);
    }

    /**
     * The function return true or false if there is valid block symbol.
     *
     * @param s is the string block.
     * @return true if 's' is a valid block symbol.
     */
    public boolean isBlockSymbol(String s) {
        return this.blockCreators.containsKey(s);
    }

    /**
     * The function return new block.
     *
     * @param s    is the string of block parameters.
     * @param xpos is x value.
     * @param ypos if the y value.
     * @return a block according to the definitions associated
     * with symbol s. The block will be located at position (xpos, ypos).
     */
    public Block getBlock(String s, int xpos, int ypos) {
        return (this.blockCreators.get(s)).create(xpos, ypos);
    }

    /**
     * The function return number of spacers.
     *
     * @param s is the string of block parameters.
     * @return the width in pixels associated with the given spacer-symbol.
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }

    /**
     * The function return number of spacers.
     *
     * @param s       is the string of spacers parameters.
     * @param integer set an integer value of the spacers.
     */
    public void setSpacerWidths(String s, Integer integer) {
        this.spacerWidths.put(s, integer);
    }

    /**
     * The function return number of spacers.
     *
     * @param s is the string of block parameters.
     * @param b set a blockCreator object.
     */
    public void setBlockCreators(String s, BlockCreator b) {
        this.blockCreators.put(s, b);
    }
}