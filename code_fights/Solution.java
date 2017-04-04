package code_fights;

import java.util.Arrays;

/**
 * Created by nachiketlondhe on 4/2/17.
 */
public class Solution {
    public static void main(String args[]) {

        boolean[][] matrix = new boolean[][] { {true, false, false }, {false, true, false}, {false , false, false} };

//        minesweeper(matrix);

        System.out.println("The file: " + theFile("CodeFigh#ts","Co$deFig?hts"));

    }

    static int[][] minesweeper(boolean[][] matrix) {
        int[][] board = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++ ) {
            for (int j = 0; j < matrix[0].length; j++) {
                board[i][j] = 0;
            }
        }

        for (int i = 0; i < matrix.length; i++ ) {
            for (int j = 0; j < matrix[0].length; j++ ) {
                if ( i + 1 < matrix.length && j + 1 < matrix[0].length && matrix[i+1][j+1]) board[i][j] += 1;
                if ( i - 1 >= 0 && j - 1 >= 0 && matrix[i-1][j-1]) board[i][j] += 1;
                if ( i + 1 < matrix.length && matrix[i+1][j]) board[i][j] += 1;
                if ( i - 1 >= 0 && matrix[i - 1][j]) board[i][j] += 1;
                if ( j + 1 < matrix[0].length && matrix[i][j+1]) board[i][j] += 1;
                if ( j - 1 > matrix[0].length && matrix[i][j-1]) board[i][j] += 1;
            }
        }

        return board;
    }

    static String theFile(String version1, String version2) {

        int len1 = version1.length();
        int len2 = version2.length();
        int[][] maxLen = new int[len1 + 1][len2 + 1];
        boolean[][] take = new boolean[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (version1.charAt(i - 1) == version2.charAt(j - 1)) {
                    maxLen[i][j] = maxLen[i - 1][j - 1] + 1;
                    take[i][j] = true;
                } else {
                    maxLen[i][j] = Math.max(maxLen[i - 1][j], maxLen[i][j - 1]);
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        int i = len1;
        int j = len2;
        while (i > 0 && j > 0) {
            if (take[i][j]) {
                i--;
                j--;
                answer.append(version1.charAt(i));
            } else {
                if (maxLen[i - 1][j] > maxLen[i][j - 1]) {
                    i--;
                } else {
                    j--;
                }
            }
        }

        return answer.reverse().toString();
    }
}
