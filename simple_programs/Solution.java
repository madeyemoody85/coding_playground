package simple_programs;

/**
 * Created by nachiketlondhe on 3/9/17.
 */
public class Solution {

    public static void main(String args[]) {
        int[] a = new int[]{1,2,2,3,1};

        char[] chars = new char[]{'a', 'a', 'a', 'b', 'b','b','c','c'};

        System.out.println("Single number : " + singleNumber(a));

        System.out.println("Single char : " + singleChar(chars));
    }

    public static int singleNumber(int[] a) {
        int num = 0;

        for (int i = 0; i < a.length; i++) {
            num ^= a[i];
        }

        return num;
    }

    public static char singleChar(char[] a) {
        char c = 0;
        for (int i = 0; i < a.length; i++ ) {
            c ^= a[i];
        }

        return c;
    }
}


//public boolean isPower(int x) {
//    if (x < 1) return true;
//
//    for (int base = 2; base < x && base < Integer.MAX_VALUE/base; base++) {
//        int temp = base;
//        while (temo <= x && temp < Integer.MAX_VALUE/base) {
//            temp *= base;
//            if (temp == x) return true;
//        }
//    }
//    return false;
//}
