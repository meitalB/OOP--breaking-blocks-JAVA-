package menu;


/**
 * Class selection add selection.
 * @param <T> object.
 */
public class Selection<T> {
    private String key;
    private String message;
    private T returnVal;

    /**
     * Construct a KeyPressStoppableAnimation according to the parameters.
     *
     * @param key       is the key string.
     * @param message   is the string of message.
     * @param returnVal is the return val.
     */
    public Selection(String key, String message, T returnVal) {
        this.key = key;
        this.message = message;
        this.returnVal = returnVal;
    }

    /**
     * the function return key.
     *
     * @return String of key.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * the function return message.
     *
     * @return String of message.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * the function return the val.
     *
     * @return return val.
     */
    public T getReturnVal() {
        return this.returnVal;
    }
}
