package simple_programs;

/**
 * This class you will mess around with bitwise operators
 *
 * Created by nachiketlondhe on 3/9/17.
 */
public class BitWise {

    public static void main(String args[]) {

//        System.out.println(17 >> 1);
//        System.out.println(4 | 4);
//        System.out.println(4 ^ 5 ^ 4 ^ 5);

        int a = 4;
        int b = 5;

        a ^= b;
        b ^= a;
        a ^= b;

//        System.out.println(a);
//        System.out.println(b);
//
//        System.out.println(4 & (4-1));

        long A = 11;

        int count = 0;

        while (A > 0) {
            System.out.println( "Current : " + (A & 1));
            if ( (A & 1) != 0) {
                count++;
            }

            // I think this divides by 2 and '<< 1' multiplies by 2
            A >>= 1;
            System.out.println("Now value : " + A);
        }

        System.out.println("number of 1's : " + count);
    }
}
