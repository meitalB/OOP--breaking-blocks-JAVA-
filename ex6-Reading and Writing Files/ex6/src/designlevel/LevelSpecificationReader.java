package designlevel;


import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import designblock.BlocksDefinitionReader;
import designblock.BlocksFromSymbolsFactory;
import object.Block;

/**
 * class LevelSpecificationReader - responsible to read a level of file.
 * with their parameters form reader.
 */
public class LevelSpecificationReader {
    /**
     * construct LevelSpecificationReader.
     */
    public LevelSpecificationReader() {
    }

    /**
     * the function get reader and returns list of level information.
     *
     * @param reader is file we read.
     * @return a list of level information.
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        List<LevelInformation> allLevels = new LinkedList<LevelInformation>();
        List<List<String>> allStringLevels;
        try {
            allStringLevels = stringToAllLevels(reader);

            for (List<String> level : allStringLevels) {
                allLevels.add(checkLevel(level));
            }
        } catch (IOException e) {
            System.out.println("error reading!");

            return null;
        }
        return allLevels;
    }

    /**
     * the function get reader and returns string of level information.
     *
     * @param read is file we read.
     * @return a list of list of string of level information.
     * @throws IOException is the exceptions.
     */
    public List<List<String>> stringToAllLevels(java.io.Reader read) throws IOException {
        BufferedReader reader = null;
        List<List<String>> stringAllLevels = new LinkedList<>();

        List<String> stringLevel = new LinkedList<>();
        try {
            reader = new BufferedReader(read);
            String thisLine = null;
            thisLine = reader.readLine();
            while (thisLine != null) {

                if (thisLine.equals("START_LEVEL")) {
                    thisLine = reader.readLine();
                    while (thisLine != null
                            && !thisLine.equals("END_LEVEL")) {
                        stringLevel.add(thisLine);
                        thisLine = reader.readLine();
                    }
                    stringAllLevels.add(stringLevel);
                    stringLevel = new LinkedList<>();
                    //thisLine = null;
                }
                thisLine = reader.readLine();
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
                    return null;
                }
            }
            return stringAllLevels;
        }
    }

    /**
     * the function get l and returns string of level information.
     *
     * @param level is file we read.
     * @return a list of list of string of level information.
     * @throws IOException is the exceptions.
     */
    public LevelInformation checkLevel(List<String> level) throws IOException {

        Integer paddleSpeed = null;
        Integer paddleWidth = null;
        Integer numberOfBlocks = null;
        List<String> ballVel = null;
        String levelName = null;
        List<Block> blocks = null;
        Integer blocksStartX = null;
        Integer blocksStartY = null;
        Integer rowHeight = null;
        String background = null;
        BlocksFromSymbolsFactory blockDefinition = null;
        try {
            for (int i = 0; i < level.size(); i++) {
                String str = level.get(i);
                String[] parts = str.split(":");
                String left = parts[0]; // left
                if (left.equals("level_name")) {
                    levelName = parts[1];
                } else if (left.equals("ball_velocities")) {
                    String[] vel = parts[1].split(" ");
                    ballVel = Arrays.asList(vel);
                } else if (left.equals("background")) {
                    background = parts[1];
                } else if (left.equals("paddle_speed")) {
                    paddleSpeed = Integer.parseInt(parts[1]);
                } else if (left.equals("paddle_width")) {
                    paddleWidth = Integer.parseInt(parts[1]);
                } else if (left.equals("block_definitions")) {
                    BlocksDefinitionReader blocksReader = new BlocksDefinitionReader();
                    Reader readerBlock = null;
                    InputStream inputStreamBlock = null;
                    try {
                        inputStreamBlock = ClassLoader.getSystemClassLoader()
                                .getResourceAsStream(parts[1]);
                        readerBlock = new InputStreamReader(inputStreamBlock);
                    } catch (Exception e) {
                        System.out.println("error reading!");
                        return null;
                    }

                    blockDefinition = blocksReader.fromReader(readerBlock);

                } else if (left.equals("blocks_start_x")) {
                    blocksStartX = Integer.parseInt(parts[1]);
                } else if (left.equals("blocks_start_y")) {
                    blocksStartY = Integer.parseInt(parts[1]);
                } else if (left.equals("row_height")) {
                    rowHeight = Integer.parseInt(parts[1]);
                } else if (left.equals("num_blocks")) {
                    numberOfBlocks = Integer.parseInt(parts[1]);
                } else if (left.equals("START_BLOCKS")) {
                    blocks = blockParsing(level.subList(i + 1, level.size()), blockDefinition,
                            blocksStartX, blocksStartY, rowHeight);
                    break;
                }
            }

        } catch (Exception e) {
            System.out.println("error!");
        }
        LevelInformation simpleLevel = new SimpleLevel(paddleSpeed, paddleWidth,
                numberOfBlocks, ballVel, levelName, blocks, background);
        return simpleLevel;
    }

    /**
     * The fuction return a list of blocks.
     *
     * @param blockList       string of blocks.
     * @param blockDefinition is the definition.
     * @param blocksStartX    x value.
     * @param blocksStartY    y value.
     * @param rowHeight       is the row high.
     * @return list of blocks.
     */
    private List<Block> blockParsing(List<String> blockList,
                                     BlocksFromSymbolsFactory blockDefinition,
                                     Integer blocksStartX,
                                     Integer blocksStartY,
                                     Integer rowHeight) {
        List<Block> blocks = new LinkedList<>();
        try {
            String thisLine = blockList.get(0);
            int currentX;
            int currentY = blocksStartY;
            for (int i = 1; i < blockList.size() && !thisLine.equals("END_BLOCKS"); i++) {
                char[] array = thisLine.toCharArray();
                currentX = blocksStartX;
                for (char symbol : array) {
                    String charInString = String.valueOf(symbol);
                    if (blockDefinition.isSpaceSymbol(charInString)) {
                        currentX += blockDefinition.getSpaceWidth(
                                String.valueOf(symbol));
                    } else {
                        Block b = blockDefinition.getBlock(charInString, currentX, currentY);
                        if (b == null) {
                            return null;
                        }
                        currentX += (int) (b.getCollisionRectangle().getWidth());
                        blocks.add(b);
                    }
                }
                currentY += rowHeight;
                thisLine = blockList.get(i);
            }
        } catch (Exception e) {
            System.out.print("something wrong in the blocks");
        }
        return blocks;
    }
}
