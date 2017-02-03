package leetCode;

/**
 * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
 *
 * For example,
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 *
 * Given word1 = “coding”, word2 = “practice”, return 3.
 * Given word1 = "makes", word2 = "coding", return 1.
 *
 * You may assume that word1 does not equal to word2, and word1 and word2 are both in the list
 *
 * Created by nachiketlondhe on 1/16/17.
 */
public class ShortestWordDistance {

    public static void main (String args[]) {
        String words[] = new String[] {"practice", "makes", "perfect", "coding", "makes"};
        String word1 = "practice";
        String word2 = "perfect";

        ShortestWordDistance solution = new ShortestWordDistance();
        int shortestDistance = solution.shortestDistanceV2(words, word1, word2);
        System.out.println("Shortest word distance: " + shortestDistance);
    }

    public int shortestDistance(String[] words, String word1, String word2) {
        int shortestDistance = Integer.MAX_VALUE;
        int index = -1;
        for(int i = 0; i < words.length; i++) {
            if (words[i] == word1 || words[i] == word2) {
                if(index != -1 && !words[index].equals(words[i])) {
                    shortestDistance = Math.min(shortestDistance, i - index);
                }
                index = i;
            }
        }

        return shortestDistance;
    }

    // fird the first position
    // find the second position
    // Once both positions are found find the minimum of shortest distance
    // Always define this as the max possible value
    public int shortestDistanceV2(String[] words, String word1, String word2) {
        int pos1 = -1;
        int pos2 = -1;
        int shortestDistance = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (words[i] == word1) {
                pos1 = i;
            }
            if (words[i] == word2) {
                pos2 = i;
            }
            if ( pos1 != -1 && pos2 != -1) {
                shortestDistance = Math.min(shortestDistance, Math.abs(pos2 - pos1));
            }
        }
        return shortestDistance;
    }
}
