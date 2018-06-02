package management;
import biuoop.KeyboardSensor;
import level.LevelInformation;

import java.util.List;
/**
 * GameFlow class -is in charge of creating the levels, and moving
 *  from one level to the next.
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
     * @param levels is List with order of play whereby levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo,
                    this.animationRunner,
                    this.remainBlocks , this.scoreGame, this.livesGame);
            level.initialize();
            while (level.getRemainBlocks() > 0 && level.getLivesGame() > 0) {
                level.playOneTurn();
            }
            if (level.getLivesGame() == 0) {
                this.winGame = false;
            }
        }
        this.animationRunner.run(new EndScreen(this.winGame, this.keyboardSensor,
                this.scoreGame.getValue(), this.animationRunner));
    }
}