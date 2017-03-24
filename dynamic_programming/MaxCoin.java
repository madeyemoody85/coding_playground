package dynamic_programming;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by nachiketlondhe on 3/19/17.
 */
public class MaxCoin {
    ArrayList<Integer> A;

    int dpTable[][][];

    public static void main(String args[]) {
        ArrayList coins = new ArrayList(Arrays.asList(4, 5, 2, 7));

        MaxCoin profit = new MaxCoin();

        System.out.println("Max profit you can make: " + profit.maxcoin(coins));
    }

    public int maxcoin(ArrayList<Integer> A) {

        this.A = A;
        int n = A.size();

        dpTable = new int[2][n][n];

        for (int k = 0; k < 2; k++) {
            for (int i = 0; i < n; i++) {
                Arrays.fill(dpTable[k][i], -1);
            }
        }

        return calculateProfit(0, n-1, 0);
    }

    public int calculateProfit(int start, int end, int player) {
        System.out.println("Current playet: " + player);
        // no more coins left this shouldn't happen but this WILL avoid the infinite
        // interation
        if (start == end) {
            return dpTable[player][start][end] = 0;
        }

        // We have canculated this value previously use that
        // memoization
        if (dpTable[player][start][end] != -1) {
            return dpTable[player][start][end];
        }

        int sum;

        if (player == 0) {
            sum = Math.max(A.get(start) + calculateProfit(start + 1, end, 1),
                    A.get(end) + calculateProfit(start, end - 1, 1));
        } else {
            sum = Math.min(calculateProfit(start + 1, end, 0),
                    calculateProfit(start, end - 1, 0));

        }

        // Store the sum in dpTable and return it
        return dpTable[player][start][end] = sum;
    }
}
