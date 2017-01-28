package com.google.interview.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Find the longest substring in a given string without repeating characters
 *
 * Created by nachiketlondhe on 1/8/17.
 */
public class LongestSubstring {

    public static void main(String args[]) {
        String s = "ggabcdhghh";

        LongestSubstring sol = new LongestSubstring();

        int length = sol.lengthOfLongestSubstringv2(s);

        System.out.println("Length of longest substring : " + length);
    }

    /**
     * "aabbabcaaa" in this case abc will be the longest substring returning length of 3
     *
     * @param s String that needs to be examined
     * @return length of longest substring
     */
    public int lengthOfLongestSubstring(String s) {
        int length = 0;

        // Keep track of character and its position int the string
        Map<Character, Integer> positionMap = new HashMap<>();

        for (int closeIndex = 0, openIndex = 0; closeIndex < s.length(); closeIndex++) {

            // If the map contains the current char, then change the openIndex
            if (positionMap.containsKey(s.charAt(closeIndex))) {
                openIndex = Math.max(positionMap.get(s.charAt(closeIndex)), openIndex);
            }

            // Length will be difference between close index and open index
            length = Math.max(length, closeIndex - openIndex + 1);

            // Track the current character in the map
            positionMap.put(s.charAt(closeIndex), closeIndex + 1);
        }

        return length;
    }

    /**
     * "ggabcdhghh" in this case abc will be the longest substring returning length of 3
     *
     * 1. Add the things to the set
     * 2. Keep track of duplicates
     * @param s String that needs to be examined
     * @return length of longest substring
     */
    public int lengthOfLongestSubstringv2(String s) {

        int open = 0, close = 0, length = 0;

        Set<Character> charSet = new HashSet();

        while ( open < s.length() && close < s.length()) {

            // Add the things to the set
            if (!charSet.contains(s.charAt(close))) {
                charSet.add(s.charAt(close));
                close++;
                length = Math.max(length, close - open);
            } else {
                charSet.remove(s.charAt(open));
                open++;
            }
        }

        return length;
    }
}
