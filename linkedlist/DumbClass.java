package linkedlist;

import java.util.Arrays;
import java.util.List;

/**
 * Created by nachiketlondhe on 3/9/17.
 */
public class DumbClass {

    static int[][] memoization = new int[10][10];

    public static void main(String args[]) {
        for (int i = 0 ; i < 10 ; i++ ) {
            Arrays.fill(memoization[i], -1);
        }

        System.out.println("Something happened to the array");

        Integer[] a = new Integer[]{1,2,2,3,4,4,4,5,6,1};

        System.out.println("Majority: " + majorityElement(Arrays.asList(a)));
    }

    public static int majorityElement(List<Integer> num) {
        int majorityIndex = 0;

        for (int count = 1, i = 1; i < num.size(); i++) {
            if (num.get(majorityIndex) == num.get(i)) {
                count++;
            } else {
                count--;
            }

            if (count == 0) {
                majorityIndex = i;
                count = 1;
            }
        }

        return num.get(majorityIndex);
    }
 }
