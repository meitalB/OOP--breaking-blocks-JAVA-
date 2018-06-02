package management;

import biuoop.DialogManager;
import biuoop.KeyboardSensor;
import designlevel.LevelInformation;
import scores.HighScoresAnimation;
import scores.HighScoresTable;
import scores.ScoreInfo;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * GameFlow class -is in charge of creating the levels, and moving
 * from one level to the next.
 */
public class GameFlow {
    private static final int LIVES = 7;
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter scoreGame;
    private Counter livesGame;
    private Counter remainBlocks;
    private Boolean winGame;


    /**
     * constract of GameFlow.
     *
     * @param ar is the AnimationRunner of the all game
     * @param ks is the KeyboardSensor be initialize at ar.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.scoreGame = new Counter(0);
        this.livesGame = new Counter(LIVES);
        this.remainBlocks = new Counter(0);
        this.winGame = true;

    }

    /**
     * the function run the  levels, and move from one level to other.
     *
     * @param levels is List with order of play whereby levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        DialogManager dialog = animationRunner.getDialogManager();

        HighScoresTable scores = new HighScoresTable(5);
        File f = new File("highscores.ser");
        if (!f.exists()) {
            try {
                f.createNewFile();
                scores.save(f);
            } catch (IOException e) {
                dialog.showErrorDialog("Error", "can't create file");
            }
        } else {
            scores = HighScoresTable.loadFromFile(f);
        }
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.animationRunner,
                    this.remainBlocks, this.scoreGame, this.livesGame);
            level.initialize();
            while (level.getRemainBlocks() > 0 && level.getLivesGame() > 0) {
                level.playOneTurn();
            }
            if (level.getLivesGame() == 0) {
                this.winGame = false;
            }
        }

        if (scores.getRank(scoreGame.getValue()) <= scores.size()) {
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");
            ScoreInfo scoreInfo = new ScoreInfo(name, this.scoreGame.getValue());
            scores.add(scoreInfo);
            try {
                scores.save(f);
            } catch (IOException e) {
                dialog.showErrorDialog("Error", "can't create file");
            }
        }

        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                new EndScreen(this.winGame, this.scoreGame.getValue())));

        this.animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor,
                KeyboardSensor.SPACE_KEY,
                new HighScoresAnimation(scores)));

    }
}