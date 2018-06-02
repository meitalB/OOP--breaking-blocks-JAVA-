
package menu;

import biuoop.DialogManager;
import biuoop.KeyboardSensor;
import management.AnimationRunner;
import management.GameFlow;
import management.KeyPressStoppableAnimation;
import scores.HighScoresAnimation;
import scores.HighScoresTable;

import java.io.File;
import java.io.IOException;

/**
 * Class TaskMenu is a  task menu of the game.
 */
public class TaskMenu {

    private AnimationRunner runner;
    private MenuAnimation<Task<Void>> menu;
    private GameFlow gameFlow;

    /**
     * Construct a KeyPressStoppableAnimation according to the parameters.
     *
     * @param runner is the AnimationRunner.
     * @param menu   is the menu animation.
     */
    public TaskMenu(AnimationRunner runner, MenuAnimation<Task<Void>> menu) {
        this.runner = runner;
        this.menu = menu;
        this.gameFlow = new GameFlow(runner, runner.getKeyboard());
    }

    /**
     * the function doing the menu with more options.
     *
     * @param animationRunner is the AnimationRunner.
     */
    public void initiliazeMenu(AnimationRunner animationRunner) {

        final AnimationRunner newRunner = animationRunner;

        Task<Void> q = new Task<Void>() { // inner class
            public Void run() {
                System.exit(1);
                return null;
            }
        };

        Task<Void> h = new Task<Void>() { // inner class
            public Void run() {
                File f = new File("highscores.ser");
                DialogManager dialog = newRunner.getDialogManager();
                HighScoresTable scores = new HighScoresTable(5);
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

                newRunner.run(new KeyPressStoppableAnimation(newRunner.getKeyboard(),
                        KeyboardSensor.SPACE_KEY,
                        new HighScoresAnimation(scores)));
                return null;
            }

        };

        this.menu.addSelection("q", "press q exit game", q);
        this.menu.addSelection("h", "press h score game", h);
    }
}



