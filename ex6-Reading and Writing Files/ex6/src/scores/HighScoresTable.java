package scores;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * class HighScoresTable - is a display the high scores table.
 */
public class HighScoresTable implements Serializable {
    public static final int TABLE = 5;
    private List<ScoreInfo> scores;
    private int size;
    private int currentSize;

    /**
     * Create an empty high-scores table with the specified size.
     *
     * @param size The size means that the table holds up to size top scores.
     */
    public HighScoresTable(int size) {
        this.size = size;
        this.scores = new ArrayList<ScoreInfo>(size);
        this.currentSize = 0;
    }

    /**
     * add a high score.
     *
     * @param score is the high score.
     */
    public void add(ScoreInfo score) {
        System.out.println(this.size);
        System.out.println(this.scores.size());
        if (getRank(score.getScore()) <= this.size) {
            if (this.size() > currentSize) {
                this.scores.add(getRank(score.getScore()) - 1, score);
                currentSize++;
            } else {
                this.scores.remove(size - 1);
                this.scores.add(getRank(score.getScore()) - 1, score);
            }
        }
    }

    /**
     * Return table size.
     *
     * @return table size.
     */
    public int size() {
        return this.size;
    }

    /**
     * The list is sorted such that the highest scores come first.
     *
     * @return the current high scores.
     */
    public List<ScoreInfo> getHighScores() {
        return this.scores;
    }

    /**
     * Rank 1 means the score will be highest on the list.
     * Rank `size` means the score will be lowest.
     * Rank > `size` means the score is too low and will not
     * be added to the list.
     *
     * @param score is the new score.
     * @return return the rank of the current score
     */
    public int getRank(int score) {
        int i;
        for (i = 0; i < currentSize; i++) {
            if (score >= this.scores.get(i).getScore()) {
                return i + 1;
            }
        }
        return i + 1;
    }

    /**
     * Clears the table.
     */
    public void clear() {
        this.scores = new ArrayList<ScoreInfo>(this.size);
    }

    /**
     * Load table data from file.
     *
     * @param filename if file name.
     * @throws IOException error reading.
     */
    public void load(File filename) throws IOException {
        if (filename.exists()) {
            this.clear();
            ObjectInputStream objectInputStream = null;
            HighScoresTable table = null;
            try {
                objectInputStream = new ObjectInputStream(
                        new FileInputStream(filename));
                table = (HighScoresTable) objectInputStream.readObject();
            } catch (ClassNotFoundException e) {
                System.err.println("error");
                return;
            } finally {
                objectInputStream.close();
            }
            this.scores.addAll(table.getHighScores());
        } else {
            throw new IOException();
        }
    }

    /**
     * Save table data to the specified file.
     *
     * @param filename is the file name.
     * @throws IOException error reading.
     */
    public void save(File filename) throws IOException {

        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream(filename));
            objectOutputStream.writeObject(this);

        } catch (IOException e) {
            System.err.println("Failed saving object");
        } finally {
            objectOutputStream.close();
        }
    }

    /**
     * Read a table from file and return it.
     * If the file does not exist, or there is a problem with
     * reading it, an empty table is returned.
     *
     * @param filename is the file name.
     * @return HighScoresTable
     */
    public static HighScoresTable loadFromFile(File filename) {

        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(
                    new FileInputStream(filename));

            // unsafe down casting, we better be sure that the stream really contains a Person!
            HighScoresTable highScore = (HighScoresTable) objectInputStream.readObject();
            return highScore;
        } catch (FileNotFoundException e) { // Can't find file to open
            return new HighScoresTable(TABLE);
        } catch (ClassNotFoundException e) { // The class in the stream is unknown to the JVM
            return new HighScoresTable(TABLE);
        } catch (IOException e) { // Some other problem
            return new HighScoresTable(TABLE);
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file");
            }
        }
    }
}


