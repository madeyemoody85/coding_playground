package onsite;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by nachiketlondhe on 3/24/17.
 */
public class SpotifyScreening {

    public static int hammingWeight(int n) {
        int ones = 0;

        while (n != 0) {
            ones = ones + (n & 1);
            n = n >>> 1;
        }

        return ones;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            this.val = x;
            this.left = null;
            this.right = null;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();

        Queue<TreeNode> currentLevel = new LinkedList<>();
        Queue<TreeNode> nextLevel = new LinkedList<>();

        currentLevel.add(root);

        ArrayList<Integer> thisLevel = new ArrayList<>();
        while (!currentLevel.isEmpty()) {
            TreeNode temp = currentLevel.poll();
            thisLevel.add(temp.val);

            if (temp.left != null) {
                nextLevel.add(temp.left);
            }
            if (temp.right != null) {
                nextLevel.add(temp.right);
            }

            if (currentLevel.isEmpty()) {
                result.add(thisLevel);
                thisLevel = new ArrayList<>();
                currentLevel = nextLevel;
                nextLevel = new LinkedList<>();
            }
        }

        return result;
    }

    /**
     * Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal
     * to the product of all the elements of nums except nums[i].
     *
     * input [1,2,3,4] return [24, 12, 8, 6]
     */
    public int[] productExceptSelf(int[] nums) {


        return new int[nums.length];
    }
}
