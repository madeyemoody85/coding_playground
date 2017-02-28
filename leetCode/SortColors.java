package leetCode;

/**
 * Created by nachiketlondhe on 2/25/17.
 */
public class SortColors {
    public static void main(String args[]) {
        SortColors solution = new SortColors();
        int[] input = new int[]{0, 1, 2, 2, 0, 0, 1, 2, 1};
    }

    public void sortColors2Pass(int[] nums) {
        int red = 0;
        int white = 1;
        int blue = 2;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                red++;
            } else if (nums[i] == 1) {
                white++;
            } else if (nums[i] == 2) {
                blue++;
            }
        }

        for (int i = 0; i < nums.length; i++ ) {
            if (i < red) nums[i] = 0;
            else if (i < red + white) nums[i] = 1;
            else nums[i] = 2;
        }
    }

    public void sortColors1pass(int[] nums) {

    }
}
