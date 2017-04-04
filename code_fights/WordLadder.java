package code_fights;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * https://codefights.com/interview/EDaACHNYHyH6qQFAL
 *
 * Created by nachiketlondhe on 4/2/17.
 */
public class WordLadder {

    public static void main(String args[]) {
        System.out.println(wordLadder("hit", "cog", new String[] { "hot",
                "dot",
                "dog",
                "lot",
                "log",
                "cog" }));
    }
    public static int wordLadder(String beginWord, String endWord, String[] wordList) {
        // Get obvious edge cases
        if (beginWord == null || endWord == null || beginWord.length() != endWord.length()) {
            return -1;
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Set<String> dict = new HashSet<>();

        for (String s : wordList) dict.add(s);

        queue.add(beginWord);
        visited.add(beginWord);

        int numberOfSteps = 0;

        while (!queue.isEmpty()) {
            String queueHead = queue.poll();

            char[] word = queueHead.toCharArray();

            for (int i = 0; i < word.length; i ++ ) {
                char temp = word[i];

                for (char c='a'; c <= 'z'; c++) {
                    word[i] = c;
                    String tempWord = String.valueOf(word);
                    if (dict.contains(tempWord) && !visited.contains(tempWord)) {
                        queue.add(tempWord);
                        visited.add(tempWord);
                    } else if (tempWord.equals(endWord)) {
                        return numberOfSteps++;
                    }
                }

                word[i] = temp;
            }

            numberOfSteps++;
        }

        return -1;
    }
}
