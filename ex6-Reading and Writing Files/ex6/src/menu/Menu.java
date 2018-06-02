package menu;

import management.Animation;

/**
 * interface <T> - is interface that extends Animation.
 * @param <T> is the object.
 */
public interface Menu<T> extends Animation {
    /**
     * the function adds selection.
     *
     * @param key       is the key string.
     * @param message   is message string.
     * @param returnVal is return val.
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * the function return status.
     *
     * @return T is the status.
     */
    T getStatus();

    /**
     * the function adds selection.
     *
     * @param key     is the key string.
     * @param message is message string.
     * @param subMenu is the sub menu val.
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);

    /**
     * the function resets parameters in the class.
     */
    void reset();
}