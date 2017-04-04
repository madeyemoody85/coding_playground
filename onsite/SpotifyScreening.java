package onsite;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by nachiketlondhe on 3/24/17.
 */
public class SpotifyScreening {

    public static void main(String args[]) {
        int[] arr = new int[] {52, 40, 2, 78, 49, 70, 39, 26, 58, 58, 52, 93, 80, 64, 33, 72, 29, 17, 81, 83, 48, 9, 49, 82, 67, 76, 54, 64, 6, 48, 16, 82, 67, 56, 32, 98, 14, 47, 48, 26, 56, 54, 80, 13, 32, 18, 4, 73, 45, 65};
        System.out.println(productExceptSelf(arr, 530));

        String[][] redirect = new String[][] {{"a-b.c","a.c"},
                {"aa-b.c","a-b.c"},
        {"bb-b.c","a-b.c"},
            {"cc-b.c","a-b.c"},
                {"d-cc-b.c","bb-b.c"},
                    {"e-cc-b.c","bb-b.c"}};

        domainForwarding(redirect);
    }

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
        int product = 1;

        int[] productArray = new int[nums.length];
        Arrays.fill(productArray, 1);

        for (int i = 0 ; i < nums.length; i ++) {
            productArray[i] = productArray[i] * product;
            product = product * nums[i];
        }

        product = 1;

        for (int i = nums.length - 1; i >= 0 ; i --) {
            productArray[i] = productArray[i] * product;
            product = product * nums[i];
        }

        return productArray;
    }

    public int maxProduct(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int max = A[0], min = A[0], result = A[0];
        for (int i = 1; i < A.length; i++) {
            int temp = max;
            max = Math.max(Math.max(max * A[i], min * A[i]), A[i]);
            min = Math.min(Math.min(temp * A[i], min * A[i]), A[i]);
            if (max > result) {
                result = max;
            }
        }
        return result;
    }

    /**
     * [1, 2, 3, 4]
     * [1, 1, 1, 1]
     * [1, 1, 1, 1]
     * [1, 1, 2, 1]
     * [1, 1, 2, 6]
     *
     * [1, 1, 2, 6] 1
     * [1, 1, 8, 6] 4
     * [1, 12, 8, 6] 12
     * [24, 12, 8, 6]
     */
    public static int productExceptSelf(int[] nums, int m) {
        BigInteger product = BigInteger.ONE;
        BigInteger[] array = new BigInteger[nums.length];

        Arrays.fill(array, BigInteger.ONE);

        for (int i  = 0; i < nums.length; i++) {
            array[i] = array[i].multiply(product);
            product = product.multiply(BigInteger.valueOf(nums[i]));
        }

        product = BigInteger.ONE;
        for (int i = nums.length - 1; i >= 0; i--) {
            array[i] = array[i].multiply(product);
            product = product.multiply(BigInteger.valueOf(nums[i]));
        }

        BigInteger num = BigInteger.ZERO;
        for (BigInteger i : array) {
            num = num.add(i);
        }

        BigInteger returnValue = num.mod(BigInteger.valueOf(m));
        return returnValue.intValue();
    }

    public String[] domainType(String[] domains) {
        String[] returnValue = new String[domains.length];

        int i = 0 ;
        for (String s: domains) {
            String temp = s.substring(s.lastIndexOf('.'));
            if (temp.equals(".com")) {
                returnValue[i] = "commercial";
            } else if(temp.equals(".org")) {
                returnValue[i] = "organization";
            } else if(temp.equals(".net")) {
                returnValue[i] = "network";
            } else if(temp.equals(".info")) {
                returnValue[i] = "information";
            }
            i++;
        }

        return returnValue;
    }

    /*
    redirects = [["godaddy.net", "godaddy.com"],
             ["godaddy.org", "godaddycares.com"],
             ["godady.com", "godaddy.com"],
             ["godaddy.ne", "godaddy.net"]]

    domainForwarding(redirects) = [["godaddy.com", "godaddy.ne", "godaddy.net", "godady.com"],
                               ["godaddy.org", "godaddycares.com"]]
     */
    static String[][] domainForwarding(String[][] redirects) {
        HashMap<String, Set<String>> redirectMap = new HashMap<>();

        for (String[] smap : redirects) {
            if (!redirectMap.containsKey(smap[1])) {
                Set<String> list = new HashSet<>();
                list.add(smap[0]);
                redirectMap.put(smap[1],list);
            }
            redirectMap.get(smap[1]).add(smap[0]);
        }

        HashMap<String, Set<String>> returnResult = new HashMap<>();

        ArrayList<String> keysToRemove = new ArrayList<>();
        for (String key : redirectMap.keySet()) {
            Set<String> l = redirectMap.get(key);

            List<String> temp = new ArrayList<>();
            for (String s : l) {
                if (redirectMap.containsKey(s)) {
                    temp.addAll(redirectMap.get(s));
                    keysToRemove.add(s);
                }
            }

            l.addAll(temp);

            returnResult.put(key, l);
        }

        for (String s: returnResult.keySet()) {
            Set<String> l = redirectMap.get(s);
            List<String> temp = new ArrayList<>();
            for (String key : l) {
                if (redirectMap.containsKey(key)) {
                    temp.addAll(redirectMap.get(key));
                    keysToRemove.add(key);
                }
            }

            l.addAll(temp);
            returnResult.put(s, l);
        }

        for (String s: keysToRemove) {
            returnResult.remove(s);
        }

        String[][] actualReturn = new String[returnResult.size()][];
        int i  = 0 ;
        for (String key : returnResult.keySet()) {
            actualReturn[i] = new String[returnResult.get(key).size()+1];
            actualReturn[i][0] = key;
            int j = 1;
            for (String s : returnResult.get(key)) {
                actualReturn[i][j] = s;
                j++;
            }
            i++;
        }

        for (String[] s: actualReturn) {
            Arrays.sort(s);
        }

        Arrays.sort(actualReturn, (a,b) -> (b.length - a.length));

        return actualReturn;
    }
}
