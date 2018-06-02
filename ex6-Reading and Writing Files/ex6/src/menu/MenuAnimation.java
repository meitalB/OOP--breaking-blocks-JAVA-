package menu;

import backround.ColorsParser;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.util.LinkedList;
import java.util.List;
import java.awt.Color;
import java.awt.Image;


/**
 * Class MenuAnimation is a menu of the game.
 *
 * @param <T> is the object.
 */
public class MenuAnimation<T> implements Menu<T> {
    private List<Selection<T>> selections;
    private KeyboardSensor key;
    private T status;
    private Menu<T> subStatus;
    private boolean stop;
    private List<String> subKeysList;
    private List<String> subMessageList;
    private List<Menu<T>> subMenusList;
    private boolean isPress;
    private Image image;

    /**
     * Construct a MenuAnimation according to a KeyboardSensor recieved.
     *
     * @param key id the KeyboardSensor.
     */
    public MenuAnimation(KeyboardSensor key) {
        selections = new LinkedList<Selection<T>>();
        this.status = null;
        this.subStatus = null;
        this.key = key;
        this.stop = false;
        this.subKeysList = new LinkedList<>();
        this.subMessageList = new LinkedList<>();
        this.subMenusList = new LinkedList<>();
        this.isPress = false;

        ColorsParser img = new ColorsParser();
        String [] strImage = {".", "background_images/landscape.jpg)"};
        this.image = img.imageFromString(strImage);

    }

    /**
     * the function adds selection.
     *
     * @param keyVal    is the key string.
     * @param message   is message string.
     * @param returnVal is return val.
     */
    @Override
    public void addSelection(String keyVal, String message, T returnVal) {
        Selection<T> select = new Selection<T>(keyVal, message, returnVal);
        this.selections.add(select);
    }

    /**
     * the function return status.
     *
     * @return T is the status.
     */
    @Override
    public T getStatus() {
        return this.status;
    }

    /**
     * the function return sub status.
     *
     * @return Menu<T> is the sub status.
     */
    public Menu<T> getSubStatus() {
        return this.subStatus;
    }

    /**
     * the function adds selection.
     *
     * @param keyVal  is the key string.
     * @param message is message string.
     * @param subMenu is the sub menu val.
     */
    @Override
    public void addSubMenu(String keyVal, String message, Menu<T> subMenu) {
        this.subKeysList.add(keyVal);
        this.subMessageList.add(message);
        this.subMenusList.add(subMenu);

    }

    /**
     * the function resets parameters in the class.
     */
    @Override
    public void reset() {
        this.stop = false;
        this.status = null;
        this.subStatus = null;
    }

    /**
     * This function's responsible of doing frame of the game.
     *
     * @param d  is the DrawSurface to draw on
     * @param dt is the dt value.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        drawOn(d);
        for (Selection<T> sel : this.selections) {
            if (key.isPressed(sel.getKey())) {
                if (this.isPress) {
                    status = sel.getReturnVal();
                    this.stop = true;
                } else {
                    this.isPress = true;
                }
            }
        }
        int i = 0;
        for (String k : subKeysList) {
            if (this.key.isPressed(k)) {
                if (this.isPress) {
                    this.subStatus = this.subMenusList.get(i);
                    this.stop = true;
                } else {
                    this.isPress = true;
                }
                i++;
            }
        }
    }

    /**
     * This function's responsible of drawing.
     *
     * @param d is the DrawSurface to draw on
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.drawText(100, 100, "Menu", 48);
        int x = 50;
        d.drawImage(0, 0, this.image);

        for (int i = 0; i < this.subKeysList.size(); i++) {
            d.drawText(100, 100 + x, subMessageList.get(i), 24);
            x += 50;
        }
        for (int i = 0; i < this.selections.size(); i++) {
            d.drawText(100, 100 + x, selections.get(i).getMessage(), 24);
            x += 50;
        }
    }

    /**
     * the function check if this animation should be stop.
     *
     * @return false if it should stop
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * the function resets the menu.
     */
    public void restartMenu() {
        this.stop = false;

    }


}
