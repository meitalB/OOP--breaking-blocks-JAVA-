import designlevel.LevelInformation;
import designlevel.LevelSet;
import designlevel.LevelSpecificationReader;
import management.AnimationRunner;
import management.GameFlow;
import menu.Menu;
import menu.MenuAnimation;
import menu.Task;
import menu.TaskMenu;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

/**
 * class Ass6Game is start the game.
 */
public class Ass6Game {
    /**
     * the function calls the class "management.Game".
     *
     * @param args from the command-line.
     */
    public static void main(String[] args) {
        final AnimationRunner runner = new AnimationRunner();
        MenuAnimation<Task<Void>> menu = new MenuAnimation<Task<Void>>(runner.getKeyboard());

        Reader reader = null;
        InputStream inputStreamBlock = null;
        try {
            inputStreamBlock = ClassLoader.getSystemClassLoader()
                    .getResourceAsStream("level_sets.txt");
            reader = new InputStreamReader(inputStreamBlock);
        } catch (Exception e) {
            System.out.println("error reading!!!");
        }
        LevelSet levelSet = new LevelSet();
        try {
            List<LevelSet> listLevelSet = levelSet.fromReader(reader);

            if (listLevelSet == null) {
                System.exit(1);
            }
            final Menu<Task<Void>> subMenu = new MenuAnimation<Task<Void>>(runner.getGui().getKeyboardSensor());
            for (final LevelSet lSet : listLevelSet) {

                Task<Void> s = new Task<Void>() { // inner class
                    public Void run() {

                        String filename = lSet.getPath();

                        Reader readerLevel = null;
                        InputStream isLevel = null;
                        try {
                            isLevel = ClassLoader.getSystemClassLoader().getResourceAsStream(filename);
                            readerLevel = new InputStreamReader(isLevel);
                        } catch (Exception e) {
                            System.out.println(" Something went wrong while reading !");
                        }
                        LevelSpecificationReader levelReader = new LevelSpecificationReader();
                        List<LevelInformation> levels = new LinkedList<LevelInformation>();

                        try {
                            levels = levelReader.fromReader((readerLevel));
                        } catch (Exception e) {
                            System.exit(1);
                        }
                        GameFlow gameflow = new GameFlow(runner, runner.getKeyboard());
                        try {
                            gameflow.runLevels(levels);
                        } catch (Exception e) {
                            System.exit(1);
                        }
                        return null;
                    }

                };
                subMenu.addSelection(lSet.getKeys(), lSet.getMessages(), s);
            }

            menu.addSubMenu("s", "Start Game", subMenu);

            TaskMenu taskMenu = new TaskMenu(runner, menu);
            taskMenu.initiliazeMenu(runner);

            while (true) {
                runner.run(menu);
                // wait for user selection
                Task<Void> task = menu.getStatus();
                Menu<Task<Void>> sub = menu.getSubStatus();
                while (task == null) {
                    if (sub != null) {
                        runner.run(sub);
                        task = sub.getStatus();
                    }
                    sub.reset();
                }
                task.run();
                menu.reset();
                menu.restartMenu();
            }

        } catch (Exception e) {
            System.exit(1);
        }
    }
}