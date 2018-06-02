package management;

/**
 * class counter is  count object like blocks etc.
 */
public class Counter {
    private int count;

    /**
     * Contractor a counter.
     *
     * @param count is the count of something.
     */
    public Counter(int count) {
        this.count = count;
    }

    /**
     * the function add number to current count.
     *
     * @param number to add
     */
    void increase(int number) {
        this.count += number;
    }

    /**
     * the function  subtract number from current count.
     *
     * @param number to subtract
     */
    void decrease(int number) {
        this.count -= number;
    }

    /**
     * the function get current count.
     *
     * @return the current count.
     */
    int getValue() {
        return this.count;
    }
}