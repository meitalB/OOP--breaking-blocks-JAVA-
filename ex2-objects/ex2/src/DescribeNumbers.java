/**
 * DescribeNumbers.
 * The program gets a list of numbers in the command line.
 * The program prints their minimum, maximum, and average values.
 */
public class DescribeNumbers {
    /**
     * Main Program DescribeNumbers.
     *<p>
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        if (checkIsIfEmpty(args)) {
            System.out.println("the array is empty");
        } else {
            int[] arrayOfNumbers = stringsToInts(args);
            System.out.println("min: " + min(arrayOfNumbers));
            System.out.println("max: " + max(arrayOfNumbers));
            System.out.println("avg: " + avg(arrayOfNumbers));
            }
        }
     /**
     * stringsToInts: converts the string array to integer array.
     *<p>
     * @param numbers - array of numbers.
     * <p>
     * @return array of integer numbers.
     */
    public static int[] stringsToInts(String[] numbers) {
        int[] integerNumbers = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            integerNumbers[i] = Integer.parseInt(numbers[i]);
        }
        return integerNumbers;
    }
    /**
     *min: find the minimum number in the array.
     *<p>
     * @param numbers - array of numbers.
     * <p>
     * @return the minimum integer number.
     */
    public static int min(int[] numbers) {
        int min = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] < min) {
                min = numbers[i];
            }
        }
        return min;
    }
    /**
     * max: find the maximum number in the array.
     *<p>
     * @param numbers - array of numbers.
     * <p>
     * @return the maximum integer number.
     */
    public static int max(int[] numbers) {
        int max = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }
        return max;
    }
    /**
     *avg: find the average of the numbers in the array.
     *<p>
     * @param numbers - array of numbers.
     * <p>
     * @return the average of the numbers in the array.
     */
    public static float avg(int[] numbers) {
        float avg = 0;
        for (int i = 0; i < numbers.length; i++) {
            avg += numbers[i];
        }
        avg = avg / numbers.length;
        return avg;
    }
    /**
     * checkIsIfEmpty: check if the array is empty.
     *<p>
     * @param numbers - array of numbers of strings.
     * <p>
     * @return true if empty, otherwise false.
     */
    public static boolean checkIsIfEmpty(String[] numbers) {
        if (numbers.length == 0) {
            return true;
        }
        return false;
    }
}