package com.google.interview.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution.
 *
 * Created by nachiketlondhe on 1/12/17.
 */
public class TwoSum {

    public static void main(String args[]) {
        int numbers[] = new int[] {1, 7, 11, 3, 6, 5, 15};
        int target = 6;

        TwoSum solution = new TwoSum();
        int result[] = solution.twoSum(numbers, target);
        System.out.println("got the resutls" + result.length);
    }

    public int[] twoSum(int[] nums, int target) {
        int result[] = new int[2];

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.keySet().contains(target - nums[i])) {
                result[1] = i;
                result[0] = map.get(target - nums[i]);
                return  result;
            }

            map.put(nums[i], i);
        }

        return result;
    }
}
