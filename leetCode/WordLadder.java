package leetCode;

import java.util.*;

/**
 * https://leetcode.com/problems/word-ladder/?tab=Description
 * Created by nachiketlondhe on 3/3/17.
 */
public class WordLadder {

    public static void main(String args[]) {
        List<String> wordlist = new ArrayList<>();

        wordlist.add("hot");
        wordlist.add("dot");
        wordlist.add("dog");
        wordlist.add("lot");
        wordlist.add("log");
        wordlist.add("cog");

        System.out.println(ladderLength("hit", "cog", wordlist));
    }

    /**
     * Start with the beginning word, change one letter at a time and add
     * the possibilities (the ones from word to the list to be visited
     */
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Get obvious edge cases
        if (beginWord == null || endWord == null || beginWord.length() != endWord.length()) {
            return -1;
        }

        int numberOfSteps = 0;

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        visited.add(beginWord);

        while (!queue.isEmpty()) {

            String temp = queue.poll();

            char[] word = temp.toCharArray();
            // Add all the possibilities to the queue
            for (int i = 0; i < word.length; i++) {
                char p = word[i];

                // Iterate over alphabets
                for (char c = 'a'; c <= 'z'; c++) {
                    word[i] = c;
                    String s = String.valueOf(word);
                    if (wordList.contains(s) && !visited.contains(s)) {
                        queue.add(s);
                        visited.add(s);
                    } else if (s.equals(endWord)){
                        return numberOfSteps++;
                    }
                }
                word[i] = p;
            }
            numberOfSteps++;
        }

        return 0;
    }

    public int workingSolution(String beginWord, String endWord, List<String> wordList) {
        Set<String> words = new HashSet<>();
        for (String s : wordList) {
            words.add(s);
        }

        Queue<String> visited = new ArrayDeque<>();
        Set<String> visistedSet = new HashSet<>();

        visited.add(beginWord);
        visistedSet.add(beginWord);


        int count = 1;
        while(!visited.isEmpty()) {
            int t = visited.size();
            count++;
            for (int i = 0; i < t; i++) {
                String current = visited.poll();
                for (int p = 0; p < current.length(); p++) {
                    char[] chars = current.toCharArray();
                    for (char k = 'a'; k <= 'z'; k++) {
                        chars[p] = k;
                        String w = new String(chars);
                        if (words.contains(w) && w.equals(endWord)) {
                            return (count);
                        } else if (visistedSet.contains(w)) {
                            continue;
                        } else if (words.contains(w) && !w.equals(current)) {
                            visited.add(w);
                            visistedSet.add(w);
                        }
                    }
                }
            }
        }
        return 0;
    }
}
