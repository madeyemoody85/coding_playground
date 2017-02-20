package leetCode;

/**
 * For description check https://leetcode.com/problems/sentence-screen-fitting/?tab=Description
 *
 * Created by nachiketlondhe on 2/19/17.
 */
public class SentenceScreenFitting {

    public static void main(String args[]) {

        SentenceScreenFitting solution = new SentenceScreenFitting();

        String[] sentence = new String[]{"I","had","apple","pie"};
        int rows = 4;
        int cols = 5;

        long startTime = System.currentTimeMillis();
        int howManyTimes = solution.wordsTyping(sentence, rows, cols);
        long calTime = (System.currentTimeMillis() - startTime);

        System.out.println("How many times: " + howManyTimes + ", in " + calTime + "ms");
    }

    public int wordsTyping(String[] sentence, int rows, int cols) {
        int howManyTimes = 0;
        int remainingColumns = cols;
        int currentNumberOfRows = rows;
        boolean newLine = false;

        while (true) {
            for(int i = 0; i < sentence.length; i++) {
                if (newLine && i == 0) {
                    return (rows * howManyTimes) / (rows - currentNumberOfRows);
                }
                if (currentNumberOfRows <= 0) {
                    return howManyTimes;
                }
                if (remainingColumns >= sentence[i].length()) {
                    newLine = false;
                    remainingColumns -= sentence[i].length() + 1;
                } else {
                    newLine = true;
                    currentNumberOfRows = currentNumberOfRows - 1;
                    remainingColumns = cols;
                    i--;
                }
            }
            howManyTimes++;
        }
    }
}
