package com.google.interview.leetcode;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * (you may want to display this pattern in a fixed font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * Created by nachiketlondhe on 1/16/17.
 */
public class ZigZagConversion {
    public static void main(String args[]) {
        ZigZagConversion solution = new ZigZagConversion();
        System.out.println(solution.convert("PAYPALISHIRING", 3));
    }

    public String convert(String s, int numRows) {

        StringBuffer[] buffers = new StringBuffer[numRows];

        int stringLength = s.length();

        for (int i = 0; i < numRows; i++)
            buffers[i] = new StringBuffer();

        int i = 0;

        while (i < stringLength) {
            for ( int index = 0 ; index < numRows && i < stringLength; index ++)
                buffers[index].append(s.charAt(i++));
            for ( int obIndex = numRows - 2; obIndex >= 1 && i < stringLength; obIndex--)
                buffers[obIndex].append(s.charAt(i++));
        }

        for (int j = 1 ; j < numRows; j++) {
            buffers[0].append(buffers[j]);
        }

        return buffers[0].toString();
    }
}
