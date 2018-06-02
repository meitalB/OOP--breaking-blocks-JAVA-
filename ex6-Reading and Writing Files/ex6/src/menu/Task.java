package menu;

/**
 * interface <T> - is interface has tasks.
 *
 * @param <T>
 */
public interface Task<T> {
    /**
     * the function return the run function.
     *
     * @return T is the run function.
     */
    T run();
}