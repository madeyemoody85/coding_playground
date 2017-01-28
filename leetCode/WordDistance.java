package com.google.interview.leetcode;

/**
 * Created by nachiketlondhe on 1/16/17.
 */
public class WordDistance {

    public static String[] words = null;

    public WordDistance(String[] words) {
        this.words = words;
    }

    public static void main(String args[]) {
        WordDistance solution = new WordDistance(new String[] {"practice", "makes", "perfect", "coding", "makes"});
        String word1 = "perfect";
        String word2 = "practice";
        System.out.println("Shortest distance : " + solution.shortest(word1, word2));
    }

    public int shortest(String word1, String word2) {
        int pos1 = -1;
        int pos2 = -1;
        int shortestDistance = Integer.MAX_VALUE;
        for (int i = 0; i < this.words.length; i++) {
            if (this.words[i] == word1) {
                pos1 = i;
            }
            if (this.words[i] == word2) {
                pos2 = i;
            }
            if ( pos1 != -1 && pos2 != -1) {
                shortestDistance = Math.min(shortestDistance, Math.abs(pos2 - pos1));
            }
        }
        return shortestDistance;
    }
}
