package leetCode;

/**
 * Problem description: https://leetcode.com/problems/minimum-size-subarray-sum/?tab=Description
 *
 * Created by nachiketlondhe on 2/26/17.
 */
public class MinSizeOfSubarraySum {

    public static void main(String args[]) {
        int nums[] = new int[]{2,3,1,2,4,3};
        int s = 7;
        System.out.println("Minimum subarray len: " + minSubArrayLen(s, nums));
    }

    public static int minSubArrayLen(int s, int[] nums) {
        int sum = 0; int minLength = Integer.MAX_VALUE;
        int length = nums.length;
        int left = 0, right = 0;

        while (right < length) {
            // Move right till you get the sum greater than you expect
            do {
                sum += nums[right++];
            } while (sum < s && right < length);

            // Move left pointer while keeping the sum greater than s
            // NOTE: check before you actually assign the value
            while (left <= right && (sum - nums[left]) >= s) {
                sum -= nums[left++];
            }

            if (sum >= s && minLength > (right - left)) {
                minLength = right - left;
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}