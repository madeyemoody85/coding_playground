package code_fights;

import java.util.ArrayList;

/**
 * Created by nachiketlondhe on 4/1/17.
 */
public class MatrixElementSum {
    public static void main(String args[]) {
        int[][] rooms = new int[][]{ { 0, 1, 1, 2 },
                                     { 0, 5, 0, 0 },
                                     { 2, 0, 3, 3 } };

        System.out.println("Sum : " + matrixElementsSum(rooms));
    }
    public static int matrixElementsSum(int[][] matrix) {
        int sum = 0;
        for (int i = 0; i < matrix.length; i ++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][j] = Integer.MIN_VALUE;
                    if (i+1 < matrix.length && matrix[i+1][j] != 0) {
                        matrix[i+1][j] = Integer.MIN_VALUE;
                    }
                }
            }
        }

        for (int i = 0; i < matrix.length; i ++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != Integer.MIN_VALUE) {
                    sum += matrix[i][j];
                }
            }
        }

        return sum;
    }
}
