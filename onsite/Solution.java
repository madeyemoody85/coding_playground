package onsite;

import java.util.*;

/**
 * Following file contains all the problems solved to prepare for onsite
 * there may be some overlap
 *
 * Created by nachiketlondhe on 3/12/17.
 */
public class Solution {

    public static void main(String args[]) {

/*        String[][] equations =
                {
                        {"a", "b"},
                        {"b", "c"}
                };

        double[] values = new double[]{2.0, 3.0};

        String[][] queries =
                {
                        {"a","c"},
                        {"b","a"},
                        {"a","e"},
                        {"a","c"},
                        {"x","x"}
                };

        double[] result = calcEquation(equations, values, queries);

        String s = "ecebaeaaaead";
        System.out.println("max substring length: " + lengthOfLongestSubstringKDistinct(s));

        System.out.println("toal number of primes in 10,000 - " + findAllPrimesTillN(10000));


        System.out.println("Some weird jank...");
        ArrayList<ArrayList<Integer>> input = new ArrayList<>();

        for (int i = 0; i < 3; i++)
            input.add(new ArrayList<>());

        input.get(0).add(1);
        input.get(0).add(2);
        input.get(0).add(3);

        input.get(1).add(4);
        input.get(1).add(5);
        input.get(1).add(6);

        input.get(2).add(7);
        input.get(2).add(8);
        input.get(2).add(9);

        diagonal(input);

        ArrayList<Integer> inputList = new ArrayList<>();
        inputList.add(1);
        inputList.add(1);
        inputList.add(1);
        inputList.add(2);
        inputList.add(3);
        inputList.add(3);
        inputList.add(4);
        inputList.add(5);
        inputList.add(5);
        inputList.add(6);

        removeDuplicates(inputList);

        String inputString = "PAYPALISHIRING";

        System.out.println("Converted String: " + convert(inputString, 3)); */

        ArrayList<Integer> inputList = new ArrayList<>();
        inputList.add(1);
        inputList.add(1);
        inputList.add(1);
        inputList.add(1);
        inputList.add(2);
        inputList.add(3);
        inputList.add(4);
        inputList.add(4);
        inputList.add(5);
        inputList.add(10);

        removeDuplicatesAtMostTwice(inputList);

        System.out.println("Ways to represent" + numDecodings("2345"));
    }

    /***************************************************************************************************
     * https://leetcode.com/problems/evaluate-division/?tab=Description
     ***************************************************************************************************/
    public static double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        HashMap<String, ArrayList<String>> pairs = new HashMap<String, ArrayList<String>>();
        HashMap<String, ArrayList<Double>> valuesPair = new HashMap<String, ArrayList<Double>>();

        // Construct the graph sort of adjacency list representation
        for (int i = 0; i < equations.length; i++) {
            String[] equation = equations[i];
            if (!pairs.containsKey(equation[0])) {
                pairs.put(equation[0], new ArrayList<String>());
                valuesPair.put(equation[0], new ArrayList<Double>());
            }
            if (!pairs.containsKey(equation[1])) {
                pairs.put(equation[1], new ArrayList<String>());
                valuesPair.put(equation[1], new ArrayList<Double>());
            }

            pairs.get(equation[0]).add(equation[1]);
            pairs.get(equation[1]).add(equation[0]);
            valuesPair.get(equation[0]).add(values[i]);
            valuesPair.get(equation[1]).add(1/values[i]);
        }

        double[] result = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String[] query = queries[i];
            result[i] = dfs(query[0], query[1], pairs, valuesPair, new HashSet<String>(), 1.0);
            if (result[i] == 0.0) result[i] = -1.0;
        }
        return result;
    }

    private static double dfs(String start,
                       String end,
                       HashMap<String, ArrayList<String>> pairs,
                       HashMap<String, ArrayList<Double>> values,
                       HashSet<String> set,
                       double value) {
        if (set.contains(start)) return 0.0;
        if (!pairs.containsKey(start)) return 0.0;
        if (start.equals(end)) return value;
        set.add(start);

        ArrayList<String> strList = pairs.get(start);
        ArrayList<Double> valueList = values.get(start);
        double tmp = 0.0;
        for (int i = 0; i < strList.size(); i++) {
            tmp = dfs(strList.get(i), end, pairs, values, set, value*valueList.get(i));
            if (tmp != 0.0) {
                break;
            }
        }
        set.remove(start);
        return tmp;
    }

    /***************************************************************************************************
     * Given a string, find the length of the longest substring T that contains at most k distinct characters
     * https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/#/description
     *
     * "eceba" k = 2
     ***************************************************************************************************/
    public static int lengthOfLongestSubstringKDistinct(String s) {
        int[] count = new int[256];
        int num = 0, startIndex = 0, res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i)]++ == 0) {
                num++;
            }
            if (num > 2) {
                while ( --count[s.charAt(startIndex++)] > 0);
                num--;
            }
            res = Math.max(res, i - startIndex + 1);
        }
        return res;
    }

    /***************************************************************************************************
     * Find all the prime numbers between 2 - n
     ***************************************************************************************************/
    public static int findAllPrimesTillN(int n) {
        boolean[] primes = new boolean[n+1];
        Arrays.fill(primes, true);

        primes[0] = false;
        primes[1] = false;

        for (int i = 2; i * i <= n; i++) {
            if(primes[i]) {
                // Mark all the multiples of i (prime as not prime)
                for (int j = i; j * i <= n; j++) {
                    primes[j * i] = false;
                }
            }
        }

        int totalNumberOfPrimes = 0;

        for (Boolean isPrime : primes) {
            if (isPrime) {
                totalNumberOfPrimes++;
            }
        }

        return totalNumberOfPrimes;
    }

    /***************************************************************************************************
     * Given a set of candidate numbers (C) (without duplicates) and a target number (T), find all unique
     * combinations in C where the candidate numbers sums to T.
     * The same repeated number may be chosen from C unlimited number of times.
     ***************************************************************************************************/
    // This is not really a knapsack problem
    // I should call this a coin change possibilities
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        if ( target <= 0 ) {
            return new ArrayList<>();
        }

        List<List<Integer>> dp = new ArrayList<>();
        for (int i = 0; i < target + 1; i++) {
            dp.add(new ArrayList<>());
        }

        // Sort the given values in ascending order
        Arrays.sort(candidates);

        for (int i = 1; i <= target; i++) {

            for (int j = 0; j < candidates.length - 1; j++) {
                // The sum we are trying to find is less than the sum of the running target
                if (i < candidates[j]) continue;

                int temp = i - candidates[j];

                List<Integer> tempResult = new ArrayList<>();

                if (temp == 0) {
                    tempResult.add(candidates[j]);
                } else if (!dp.get(temp).isEmpty() ) {
                    tempResult.add(candidates[j]);
                    tempResult.addAll(dp.get(temp));
                } else {
                    continue;
                }

                dp.set(i, tempResult);
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0 ; i <= candidates.length - 1; i ++) {

            int temp = target - candidates[i];

            if (temp == 0) {
                result.add(Arrays.asList(candidates[i]));
            } else if (!dp.get(temp).isEmpty() ) {
                Collections.addAll(dp.get(temp), candidates[i]);
                result.add(dp.get(temp));
            }

        }

        return result;
    }


    // Following solution is same as the one you have written and IT did GET ACCEPTED. Move the loop in if else on line
    // 46
    public List<List<Integer>> combinationSum2(int[] cands, int t) {
        Arrays.sort(cands);

        List<List<List<Integer>>> dp = new ArrayList<>();

        for (int i = 1; i <= t; i++) {

            List<List<Integer>> newList = new ArrayList();

            // run through all candidates <= i
            for (int j = 0; j < cands.length && cands[j] <= i; j++) {

                // special case when curr target is equal to curr candidate
                if (i == cands[j]) newList.add(Arrays.asList(cands[j]));

                    // if current candidate is less than the target use prev results
                else for (List<Integer> l : dp.get(i-cands[j]-1)) {
                    if (cands[j] <= l.get(0)) {
                        List cl = new ArrayList<>();
                        cl.add(cands[j]); cl.addAll(l);
                        newList.add(cl);
                    }
                }
            }
            dp.add(newList);
        }
        return dp.get(t-1);
    }

    /***************************************************************************************************
     * Give a N*N square matrix, return an array of its anti-diagonals. Look at the example for more details.
     * 1 2 3
     * 4 5 6
     * 7 8 9
     * 2 - 3, 3 - 5 , 4 - 7 , 5 - 9
     * [
     *      [1],
     *      [2, 4],
     *      [3, 5, 7],
     *      [6, 8],
     *      [9]
     * ]
     *
     * good point is that the anti diagonal row increases by 1 and column decreases by -1
     * one you know the start point keep on adding (1, -1) to the next element till in bounds
     *
     * How to find the start points all the elements in the first row and now when you reach the last one
     * they are last elements of each column from that pooint on wards
     **************************************************************************************************/
    public static ArrayList<ArrayList<Integer>> diagonal(ArrayList<ArrayList<Integer>> a) {

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        int maxRow = a.size() - 1;
        int minColumn = 0;

        int startColumn;

        // Find the first half
        for ( startColumn = 0; startColumn <= a.get(0).size() - 1; startColumn++ ) {
            int row = 0;
            int column = startColumn;
            ArrayList<Integer> temp = new ArrayList<>();
            while (row <= maxRow && column >= minColumn) {
                temp.add(a.get(row).get(column));
                row++;
                column--;
            }

            result.add(temp);
        }

        int currentRow = 1;
        while (currentRow <= maxRow) {
            int row = currentRow;
            int column = startColumn-1;
            ArrayList<Integer> temp = new ArrayList<>();
            while (row <= maxRow && column >= minColumn) {
                temp.add(a.get(row).get(column));
                row++;
                column--;
            }
            result.add(temp);
            currentRow++;
        }

        return result;
    }


    /***************************************************************************************************
     * Given N and M find all stepping numbers in range N to M
     * A number is called as a stepping number if the adjacent digits have a difference of 1.
     * e.g 123 is stepping number, but 358 is not a stepping number
     ***************************************************************************************************/
    public static ArrayList<Integer> stepnum(int a, int b) {
        ArrayList<Integer> result = new ArrayList<>();

        for ( int i = a; i <= b; i++) {
            ArrayList<Integer> array = intToArray(i);
            int previous = array.get(0);
            boolean addNumber = true;
            for (int j = 1; j < array.size(); j++) {
                if (Math.abs(array.get(j) - previous) == 1) {
                    previous = array.get(j);
                } else {
                    addNumber = false;
                }
            }

            if (addNumber) {
                result.add(i);
            }
        }
        return result;
    }

    public static ArrayList<Integer> intToArray(int a) {
        ArrayList<Integer> resultArray = new ArrayList<>();
        int temp = a;
        do {
            resultArray.add(temp % 10);
            temp = temp / 10;
        } while (temp > 0);

        return resultArray;
    }

    /***************************************************************************************************
     * Find shortest unique prefix to represent each word in the list.
     **************************************************************************************************/
    class TrieNode {
        char c;
        TrieNode[] links;
        boolean isFullWord;
        int charFrequency;

        public TrieNode(char c) {
            this.c = c;
            this.links = new TrieNode[26];
            this.isFullWord = false;
            this.charFrequency = 0;
        }
    }

    class Trie {
        TrieNode root = null;

        public TrieNode getTree() {
            if (this.root == null) {
                this.root = new TrieNode('\0');
            }
            return this.root;
        }

        /**
         * Method to add the word in the trie
         * char by char and it will increase the frequency on each path
         * @param word
         */
        public void addWord(String word) {
            if (word == null || word.isEmpty()) {
                return;
            }

            char[] s = word.toCharArray();

            TrieNode currentNode = root;

            for (int i = 0; i < s.length; i++) {
                // Add the character if it doesn't exist
                if (currentNode.links[s[i] - 97] == null) {
                    currentNode.links[s[i] - 97] = new TrieNode(s[i]);
                    currentNode = currentNode.links[s[i] - 97];
                }
                currentNode.links[s[i]-97].charFrequency++;
            }

            currentNode.isFullWord = true;
        }
    }

    /***************************************************************************************************
     * For facebook
     ***************************************************************************************************/
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /*****************************************************************************************************
     * Given a binary tree, determine if it is height-balanced.
     * For this problem, a height-balanced binary tree is defined as a binary tree in which
     * the depth of the two subtreesof every node never differ by more than 1.
     ****************************************************************************************************/
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        int leftHeight = depth(root.left);
        int rightHeight = depth(root.right);

        return Math.abs(leftHeight - rightHeight) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    public int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(depth(node.left), depth(node.right)) + 1;
    }

    /*****************************************************************************************************
     * Given length of the longest path from root to the farthest leaf node
     ****************************************************************************************************/
    public int maxHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(maxHeight(root.left), maxHeight(root.right));
    }

    /*****************************************************************************************************
     * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
     *     1
     *    / \
     *   2   2
     *  / \ / \
     * 3  4 4  3
     *
     * basically node1.right == node2.left && node1.left == node2.right
     ****************************************************************************************************/
    public int isSymmetric(TreeNode a) {
        if (a == null) {
            return 0;
        }

        return compareSubtree(a.left, a.right) ? 1 : 0;
    }

    public boolean compareSubtree(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }

        if (node1 == null || node2 == null) {
            return false;
        }

        return (node1.val == node2.val) &&
                compareSubtree(node1.left, node2.right) &&
                compareSubtree(node1.right, node2.left);
    }

    /****************************************************************************************************
     * Remove Element
     * Given an array and a value, remove all the instances of that value in the array.
     * Also return the number of elements left in the array after the operation.
     * It does not matter what is left beyond the expected length.
     *
     * Any of the two solutions are right.
     ***************************************************************************************************/
    public int removeElement(ArrayList<Integer> a, int b) {

//        for (int i = 0 ; i < a.size(); i++) {
//            if (a.get(i) == b) {
//                a.remove(i);
//            }
//        }
//
//        return a.size();

        int kIndex = 0;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) != b) {
                a.set(kIndex++, a.get(i));
            }
        }

        return kIndex;
    }

    /****************************************************************************************************
     * Remove duplicates in a given array in place and return length of
     *
     * 1 1 2 3 4 4 5 5 6
     *
     * 1 1 2 3 3
     ***************************************************************************************************/
    public static int removeDuplicates(ArrayList<Integer> a) {

        int k = 0;

        for (int i = 0; i < a.size() - 1; i++) {
            if (a.get(i) != a.get(i+1)) {
                a.set(k++, a.get(i));
            }
        }

        a.set(k++, a.get(a.size()-1));

        while (k < a.size()) {
            a.remove(k);
        }
        return a.size();
    }

    public void mozeZeroes(int[] nums) {
        int k = 0;

        for (int i = 0 ; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[k++] = nums[i];
            }
        }

        while (k < nums.length) {
            nums[k++] = 0;
        }
    }

    /***************************************************************************************************
     * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
     * (you may want to display this pattern in a fixed font for better legibility)
     *
     * P.......A........H.......N
     * ..A..P....L....S....I...I....G
     * ....Y.........I........R
     *
     * And then read line by line: PAHNAPLSIIGYIR Write the code that will take a string and
     * make this conversion given a number of rows
     *
     * ABCD 2
     *
     * A....C
     * ...B....D
     *
     * May be create an array of String builders and appencd the values
     ***************************************************************************************************/
    public static String convert(String a, int b) {
        StringBuilder[] builders = new StringBuilder[b];

        for (int i = 0; i < b; i++) {
            builders[i] = new StringBuilder();
        }

        int length = a.length();
        int i = 0;

        while (i < length) {
            for (int j = 0; j < b && i < length; j++)
                builders[i].append(a.charAt(i++));
            for (int k = length - 2; k >= 1 && i < length; k--)
                builders[k].append(a.charAt(i++));
        }

        for (int n = 1; i < builders.length; n++ ) {
            builders[0].append(builders[n]);
        }

        return builders[0].toString();
    }

    /***************************************************************************************************
     * Given a sorted array, remove the duplicates in place such that each element can appear atmost
     * twice and return the new length.
     *
     * [ 1, 1, 1, 2 ]
     ***************************************************************************************************/
    public static int removeDuplicatesAtMostTwice(ArrayList<Integer> a) {
        int index = 0;
        int n = 0;

        for (int i = 1; i < a.size(); i++) {
            if (a.get(i) == a.get(i - 1) && n < 2) {
                a.set(index++, a.get(i));
                n++;
            } else if (a.get(i) != a.get(i-1)) {
                a.set(index++, a.get(i));
                n = 1;
            }
        }

        while (index < a.size()) {
            a.remove(index);
        }

        return index;
    }

    /***************************************************************************************************
     * You are a product manager and currently leading a team to develop a new product. Unfortunately,
     * the latest version of your product fails the quality check. Since each version is developed based
     * on the previous version, all the versions after a bad version are also bad.
     * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes
     * all the following ones to be bad.
     * You are given an API bool isBadVersion(version) which will return whether version is bad.
     * Implement a function to find the first bad version. You should minimize the number of calls to the API.
     ***************************************************************************************************/
    public int firstBadVersion(int n) {
        int start = 0;
        int end = n;

        while (start < end) {
            int mid = start + (end - start)/2;
            if (!isBadVersion(mid)) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return start;
    }

    public boolean isBadVersion(int version) {
        return true;
    }

    /****************************************************************************************************
     * A message containing letters from A-Z is being encoded to numbers using the following mapping:
     * Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
     *
     * 783
     *
     * 1
     * 1
     ***************************************************************************************************/
    public static int numDecodings(String s) {
        int n = s.length();
        if (n <= 0) {
            return 0;
        }
        int[] dpTable = new int[n+1];

        dpTable[n] = 1;
        dpTable[n - 1] = s.charAt(n-1) == '0' ? 0 : 1;

        for (int i = n - 2; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                continue;
            }

            dpTable[i] = (Integer.parseInt(s.substring(i, i+2)) <= 26) ? dpTable[i+1] + dpTable[i+1] : dpTable[i+1];
        }

        return dpTable[0];
    }
}
