package designlevel;

import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

/**
 * class LevelSets - responsible to keep sets of levels
 * with their parameters cmd operations.
 */
public class LevelSet {
    private String keys;
    private String messages;
    private String path;

    /**
     * construct a LevelSets according to given parameters.
     *
     * @param key     is the given key
     * @param massage is the given message
     * @param path    is the given path
     */
    public LevelSet(String key, String massage, String path) {
        this.keys = key;
        this.messages = massage;
        this.path = path;
    }

    /**
     * construct a LevelSets.
     */
    public LevelSet() {
        this.keys = "";
        this.messages = "";
        this.path = "";
    }

    /**
     * the function reads the file and returns list of LevelSets.
     *
     * @param reader is the reader
     * @return list of LevelSets.
     */
    public List<LevelSet> fromReader(java.io.Reader reader) {
        List<LevelSet> listOfLevelSets = new ArrayList<LevelSet>();
        LineNumberReader is = null;
        try {
            is = new LineNumberReader(reader);
            String thisLine;
            while ((thisLine = is.readLine()) != null) {
                thisLine = thisLine.trim();
                if (is.getLineNumber() % 2 != 0) {
                    String[] keyMassage = thisLine.split(":");
                    this.keys = keyMassage[0];
                    this.messages = keyMassage[1];
                } else {
                    this.path = thisLine;
                    LevelSet levelSet = new LevelSet(this.keys, this.messages, this.path);
                    listOfLevelSets.add(levelSet);
                }
            }
        } catch (IOException e) {
            System.out.println(" Something went wrong while reading !");
            return null;
        }
        return listOfLevelSets;
    }

    /**
     * the function returns keys.
     *
     * @return keys.
     */
    public String getKeys() {
        return this.keys;
    }

    /**
     * the function returns keys.
     *
     * @return keys.
     */
    public String getMessages() {
        return this.messages;
    }

    /**
     * the function returns path.
     *
     * @return path.
     */
    public String getPath() {
        return this.path;
    }
}
