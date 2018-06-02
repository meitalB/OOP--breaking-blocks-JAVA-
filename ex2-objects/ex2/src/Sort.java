/**
 * Sort.
 * The program sorts a string.
 * desc - sort according to bigger to the smaller.
 * asc - sort according to smaller to the bigger.
 */
public class Sort {
    /**
     * Main Program Sort.
     * <p>
     * @param args - command line arguments.
     */
    public static void main(String[] args) {
        // check if the length is correct.
        if (args.length < 2) {
            System.out.println("The array is wrong");
        } else {
            // saving the first value of the array.
            String kindOfSort = args[0];
            // create a new string array of numbers.
            String[] numbers = stringsToString(args);
            // change the array to int from string.
            int[] arrayOfNumbers = stringsToInts(numbers);
            // sort according to smaller to the bigger.
            if (kindOfSort.equals("asc")) {
                if (args.length == 2) {
                    System.out.println(args[1]);
                } else {
                bubbleSortAsc(arrayOfNumbers);
                print(arrayOfNumbers);
                }
            } else if (kindOfSort.equals("desc")) {
                // sort according to bigger to the smaller.
                if (args.length == 2) {
                    System.out.println(args[1]);
                } else {
                    bubbleSortDesc(arrayOfNumbers);
                    print(arrayOfNumbers);
                }
            } else {
                System.out.println("The string is wrong");
                   }
                }
    }
    /**
     * print - print the array.
     * <p>
     * @param numArr - array of numbers.
     */
    public static void print(int[] numArr) {
        for (int i = 0; i < numArr.length; i++) {
            System.out.print(numArr[i] + " ");
        }
        System.out.println("");
    }
     /**
     * bubbleSortDesc - sort according to bigger to the smaller.
     * <p>
     * @param numArr - array of numbers .
     */
    public static void bubbleSortDesc(int[] numArr) {
        int j;
        boolean firstPass = true; // flag is true in the first pass
        int temp; // help to save the value
        while (firstPass) {
            firstPass = false; // change to false it is may be swap
            for (j = 0; j < numArr.length - 1; j++) {
                if (isBigger(numArr[j], numArr[j + 1])) {
                    temp = numArr[j];
                    numArr[j] = numArr[j + 1];
                    numArr[j + 1] = temp;
                    firstPass = true;
                }
            }
        }
    }
     /**
     * isBigger - check if the first number is bigger than.
     *  the second.
     * <p>
     * @param num1 - the first number.
     * <p>
     * @param num2 - the second number.
     * <p>
     * @return true if bigger or false if not.
     */
    public static boolean isBigger(int num1, int num2) {
        if (num1 < num2) {
            return true;
            }
        return false;
    }
     /**
     * bubbleSortAsc - sort according to smaller to the bigger.
     * <p>
     * @param numArr - array of numbers .
     */
    public static void bubbleSortAsc(int[] numArr) {
        int j;
        boolean firstPass = true; // flag is true in the first pass
        int temp; // help to save the value
        while (firstPass) {
            firstPass = false; // change to false it is may be swap
            for (j = 0; j < numArr.length - 1; j++) {
                if (isSmaller(numArr[j], numArr[j + 1])) {
                    temp = numArr[j];
                    numArr[j] = numArr[j + 1];
                    numArr[j + 1] = temp;
                    firstPass = true;
                }
            }
        }
    }
     /**
     * isSmaller - check if the first number is smaller than.
     *  the second.
     * <p>
     * @param num1 - the first number.
     * <p>
     * @param num2 - the second number.
     * <p>
     * @return true if smaller or false if not.
     */
    public static boolean isSmaller(int num1, int num2) {
        if (num1 > num2) {
            return true;
        }
        return false;
    }
     /**
     * stringsToInts -change the array of strings to integer array.
     * <p>
     * @param numbers - array of numbers.
     * <p>
     * @return the new array of integer.
     */
    public static int[] stringsToInts(String[] numbers) {
        int[] integerNumbers = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            integerNumbers[i] = Integer.parseInt(numbers[i]);
        }
        return integerNumbers;
    }
     /**
     * stringsToString -change the array of strings to anther.
     * array of string.
     * <p>
     * @param numbers - array of numbers.
     * <p>
     * @return the new array of string.
     */
    public static String[] stringsToString(String[] numbers) {
        String[] stringNumbers = new String[numbers.length - 1];
        for (int i = 1; i < numbers.length; i++) {
            stringNumbers[i - 1] = numbers[i].toString();
        }
        return stringNumbers;
    }
     /**
     * checkIsRigth - check if the array with length of 2 or more .
     * <p>
     * @param numbers - array of numbers.
     * <p>
     * @return true if it right, otherwise false.
     */
    public static boolean checkIsRigth(String[] numbers) {
        if (numbers.length < 2) {
            return true;
            }
        return false;
    }
}