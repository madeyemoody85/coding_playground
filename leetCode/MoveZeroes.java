package leetCode;

/**
 * given nums = [0, 1, 0, 3, 12] should be [1, 3, 12, 0, 0]
 * Created by nachiketlondhe on 2/21/17.
 */
public class MoveZeroes {
    public static void main(String args[]) {
        int[] a = new int[] {0, 1, 0, 3, 12, 0, 0, 5, 4, 0, 2};

        System.out.println(a);

        moveZeroes(a);

    }

    public static void moveZeroes(int nums[]) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int insertPosition = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[insertPosition++] = nums[i];
            }
        }

        while (insertPosition < nums.length) {
            nums[insertPosition++] = 0;
        }
    }
}
