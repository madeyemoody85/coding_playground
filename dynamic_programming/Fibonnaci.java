package dynamic_programming;

/**
 * Following code will try to solve the fibonnaci series using dynamic programming
 *
 * Created by nachiketlondhe on 2/20/17.
 */
public class Fibonnaci {

    public static void main(String args[]) {

        System.out.println("Fibbonacci : " + fibRec(4));
    }

    public static int fibonnacci(int n) {
        if (n < 2) {
            return 1;
        }

        int p1 = 1;
        int p2 = 1;
        int returnValue = Integer.MAX_VALUE;

        for (int i = 2; i < n; i++) {
            returnValue = p1 + p2;
            p1 = p2; //n-2
            p2 = returnValue; // n-1
        }

        return returnValue;
    }

    public static int fibRec(int n) {
        if (n <= 2) {
            return 1;
        }

        return fibRec(n-1) + fibRec(n-2);
    }
}
