package leetCode;

import java.util.HashMap;

public class MaxSubArraySum {

    public static void main(String args[]) {
        MaxSubArraySum solution = new MaxSubArraySum();

        int[] nums = new int[]{ -2, -1, 2, 1};
        int k = 0;

        System.out.println("Max subarray length: " +
                solution.maxSubArrayLen(nums, k));
    }

    public int maxSubArrayLen(int[] nums, int k) {
        int sum = 0, max = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {

            sum = sum + nums[i];

            if (sum == k) {
                max = i + 1;
            } else if (map.containsKey(sum - k)) {
                max = Math.max(max, i - map.get(sum - k));
            }

            // At index i sum is there
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }

        // default value to be returned
        return max;
    }
}
