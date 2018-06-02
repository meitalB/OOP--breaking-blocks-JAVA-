package designblock;

import backround.ColorsParser;

import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * class ColorsParser - mapping from symbols
 * to spaces and blocks. These symbols are then used in the level specification
 * files to define the blocks that need to be created. The class creates the desired block.
 */
public class BlocksDefinitionReader {
    private String symbol;
    private Integer height;
    private Integer width;
    private Integer hitPoints;
    private Color color;
    private Image image;
    private Color stroke;
    private Map<Integer, Color> colorMap;
    private Map<Integer, Image> imageMap;
    private Map<Integer, String> hitMap;
    private String[] partsDefault;
    private BlocksFromSymbolsFactory blockCreators;

    /**
     * Constructor - define block with specific parameters to null.
     */
    public BlocksDefinitionReader() {
        this.symbol = null;
        this.height = null;
        this.width = null;
        this.hitPoints = null;
        this.color = null;
        this.image = null;
        this.stroke = null;
        this.colorMap = new HashMap<Integer, Color>();
        this.imageMap = new HashMap<Integer, Image>();
        this.hitMap = new HashMap<Integer, String>();
        this.partsDefault = new String[1];
        this.blockCreators = new BlocksFromSymbolsFactory();
    }

    /**
     * The function get a file text and define an object to create new blocks.
     *
     * @param reader is the java io reader.
     * @return blockCreatorList is a BlocksFromSymbolsFactory object.
     */
    public BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        List<SimpleBlock> allStringBlocks;
        try {
            allStringBlocks = stringToAllBlocks(reader);
            for (SimpleBlock block : allStringBlocks) {
                blockCreators.setBlockCreators(block.getSymbol(), block);
            }
        } catch (IOException e) {
            System.out.println("error reading!");
            return null;
        }
        return blockCreators;
    }

    /**
     * The function get a file text and define an object to create new blocks.
     *
     * @param read is the java io reader.
     * @return blockCreatorList is a list of simple block object.
     * @throws IOException throws exception.
     */
    public List<SimpleBlock> stringToAllBlocks(java.io.Reader read) throws IOException {
        List<SimpleBlock> blockCreatorList = new LinkedList<>();

        BufferedReader reader = null;
        List<String> stringBlock = new LinkedList<>();
        try {
            reader = new BufferedReader(read);
            String thisLine = null;

            while ((thisLine = reader.readLine()) != null) {
                if ((!"".equals(thisLine)) && (!thisLine.startsWith("#"))) {
                    String[] blockFeature = thisLine.split(" ");
                    if (blockFeature[0].equals("default")) {
                        // treat on default values
                        this.partsDefault = blockFeature;
                        this.defaultDef(blockFeature);
                    } else if (blockFeature[0].equals("bdef")) {
                        // treat on blockes
                        this.defaultDef(this.partsDefault);
                        blockCreatorList.add(checkBlock(blockFeature));
                    } else if (blockFeature[0].equals("sdef")) {
                        // treat on spacers
                        String[] symbolOfSpacers = blockFeature[1].split(":");
                        String[] widthOfSpacers = blockFeature[2].split(":");
                        this.blockCreators.setSpacerWidths(symbolOfSpacers[1],
                                Integer.parseInt(widthOfSpacers[1]));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("error reading!");
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("error closing!");
                    // return null;
                    blockCreatorList = null;
                }
            }
        }
        return blockCreatorList;
    }

    /**
     * The function get an array and update the values of the local parameter.
     *
     * @param block is an array of string of the default.
     */
    public void defaultDef(String[] block) {
        if (this.partsDefault.length > 1) {
            for (int i = 1; i < this.partsDefault.length; i++) {
                String[] partsDef = this.partsDefault[i].split(":");
                this.checkingValues(partsDef);
            }
        }
    }

    /**
     * The function get an array and update the values of the local parameter according to this block.
     *
     * @param block is an array of string of one block.
     * @return the simple block that we create.
     * @throws IOException throws exception.
     */
    public SimpleBlock checkBlock(String[] block) throws IOException {
        try {
            String[] partsBlocks = new String[block.length];
            for (int i = 1; i < block.length; i++) {
                partsBlocks[i] = block[i];
                String[] partsBlockbdef = partsBlocks[i].split(":");
                this.checkingValues(partsBlockbdef);
            }
        } catch (Exception e) {
            System.out.println("error!!");
        }
        SimpleBlock simpleBlock = new SimpleBlock(this.hitPoints
                , this.color, this.image, this.stroke, this.colorMap, this.imageMap, this.hitMap);
        simpleBlock.setSymbol(this.symbol);
        simpleBlock.setHeight(this.height);
        simpleBlock.setWidth(this.width);
        return simpleBlock;
    }

    /**
     * The function get an array of values and update the local values.
     *
     * @param str is an array of one value of one block.
     */
    public void checkingValues(String[] str) {
        String nameLeft = str[0]; // name
        String valueRight = str[1]; // value
        int countFill = 1;
        int numFill = 0;
        if (nameLeft.contains("-")) {
            String[] fillStr = nameLeft.split("-");
            nameLeft = fillStr[0];
            countFill = Integer.parseInt(fillStr[1]);
        }
        if (nameLeft.equals("symbol")) {
            this.symbol = valueRight;
        } else if (nameLeft.equals("height")) {
            this.height = Integer.parseInt(valueRight);
        } else if (nameLeft.equals("width")) {
            this.width = Integer.parseInt(valueRight);
        } else if (nameLeft.equals("hit_points")) {
            this.hitPoints = Integer.parseInt(valueRight);
        } else if (nameLeft.equals("fill")) {
            String[] fillStr = valueRight.split("\\(");
            ColorsParser background = new ColorsParser();
            if (fillStr[0].equals("color")) {
                this.color = background.colorFromString(fillStr);
                this.colorMap.put(countFill, this.color);
            } else if (fillStr[0].equals("image")) {
                this.image = background.imageFromString(fillStr);
                this.imageMap.put(countFill, this.image);
            }
        } else if (nameLeft.equals("stroke")) {
            String[] fillStroke = valueRight.split("\\(");
            ColorsParser backStroke = new ColorsParser();
            if (fillStroke[0].equals("color")) {
                this.stroke = backStroke.colorFromString(fillStroke);
            }
        } else if (this.hitPoints > 1) {
            String[] strManyFill = valueRight.split("-");
            for (int i = 0; i < strManyFill.length; i++) {
                this.hitMap.put(Integer.parseInt(strManyFill[1]), valueRight);
            }
        }
    }
}



