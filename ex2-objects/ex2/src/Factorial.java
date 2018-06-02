/**
 * Factorial.
 * The program is to receive a number.
 * The program prints the factorial number.
 * One by regular function and one by recursion.
 */
public class Factorial {
    /**
    * Main Program Factorial.
    *<p>
    * @param args command line arguments.
    */
    public static void main(String[] args) {
        if (checkIsRigth(args)) {
            System.out.println("The string is wrong");
        } else {
        long numFact = Long.parseLong(args[0]);
        System.out.println("recursive: " + factorialIter(numFact));
        System.out.println("iterative: " + factorialRecursive(numFact));
        }
    }
    /**
     * checkIsRigth - check if numFact is valid .
     * <p>
     * @param args - command line arguments.
     * <p>
     * @return true if it right, otherwise false.
     */
    public static boolean checkIsRigth(String[] args) {
        if ((args.length == 0) || (args.length > 1)) {
            return true;
        }
        return false;
    }
    /**
     * factorialIter: calculate the factorial of the number.
     *<p>
     * @param n - number.
     * <p>
     * @return sumFact - the number of factorial.
     */
    public static long factorialIter(long n) {
        long sumFact = 1;
        for (int i = 1; i <= n; i++) {
            sumFact = sumFact * i;
        }
        return sumFact;
    }
    /**
    * factorialRecursive: calculate the factorial by recursion.
    *<p>
    * @param n - number.
    * <p>
    * @return sumFact - the number of factorial.
    */
    public static long factorialRecursive(long n) {
        long sumFact = 1;
        if (n == 1) {
            return n;
        }
        sumFact = n * factorialRecursive(n - 1);
        return sumFact;
    }
}