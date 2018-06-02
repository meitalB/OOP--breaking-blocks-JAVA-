package scores;

import java.io.Serializable;
/**
 * class ScoreInfo - is a display the scores information.
 */
public class ScoreInfo implements Serializable {
    private String name;
    private int score;

    /**
     * construct the score info.
     * @param name is the name string.
     * @param score is the num score.
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * return the name.
     * @return the name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * rethen the score.
     * @return the score.
     */
    public int getScore() {
        return this.score;
    }
}
