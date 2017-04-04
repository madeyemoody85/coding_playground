package code_fights;

/**
 * Created by nachiketlondhe on 4/1/17.
 */
public class AlmostIncreasingSequence {

    public static void main(String args[]) {
//        int[] array = new int[] { 1, 2, 3, 4, 99, 5, 6 };
        int[] array = new int[] { 40, 50, 60, 10, 20, 30 };
        System.out.println("Is almost increasing: " + almostIncreasingSequence(array));
    }

    public static boolean almostIncreasingSequence(int[] sequence) {

        int erasedElements = 0;

        for (int i = 0; i < sequence.length - 1 ; i++) {
            if (sequence[i] >= sequence[i+1]) {
                erasedElements += 1;
            }
        }

        return (erasedElements <= 1);
    }

}
