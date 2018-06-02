import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import level.LevelInformation;
import management.AnimationRunner;
import management.GameFlow;
import level.Level1;
import level.Level2;
import level.Level3;
import level.Level4;

/**
 * class Ass5Game is start the game.
 */
public class Ass5Game {
    /**
     * the function is the main function the run the game.
     *
     * @param args from the command-line.
     */
    public static void main(String[] args) {
        LevelInformation[] levelsName = new LevelInformation[4];
        levelsName[0] = new Level1();
        levelsName[1] = new Level2();
        levelsName[1] = new Level2();
        levelsName[2] = new Level3();
        levelsName[3] = new Level4();
        List<LevelInformation> levels = new LinkedList<LevelInformation>();
        if (args.length == 0) {
            for (int i = 0; i < 4; i++) {
                levels.add(levelsName[i]);
            }
        } else {
            levels = orderLevels(args);
        }
        AnimationRunner runner = new AnimationRunner();
        GameFlow game = new GameFlow(runner, runner.getKeyboard());
        game.runLevels(levels);
    }

    /**
     * the function create levels order according to cmd line.
     *
     * @param argsOfLevels from the command-line.
     *
     * @return levelsOrder - the levels in order that entered.
     */
    public static List<LevelInformation> orderLevels(String[] argsOfLevels) {
        List<LevelInformation> levelsOrder = new LinkedList<LevelInformation>();
        // save the relevent numbers of levels.
        Map<String, LevelInformation> assignment = new TreeMap<String,
                LevelInformation>();
        assignment.put("1", new Level1());
        assignment.put("2", new Level2());
        assignment.put("3", new Level3());
        assignment.put("4", new Level4());
        for (String s : argsOfLevels) {
            if (assignment.containsKey(s)) {
                levelsOrder.add(assignment.get(s));
            }
        }
        return levelsOrder;
    }
}