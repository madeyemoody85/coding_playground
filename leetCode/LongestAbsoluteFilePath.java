package leetCode;

import java.util.HashMap;
import java.util.Stack;

/**
 * For description refer to https://leetcode.com/problems/longest-absolute-file-path/
 * Created by nachiketlondhe on 1/10/17.
 */
public class LongestAbsoluteFilePath {
    public static void main(String args[]) {
        LongestAbsoluteFilePath solution = new LongestAbsoluteFilePath();

        String path1 = "a\n\tb1\n\t\tf1.txt\n\taaaaa\n\t\tf2.txt";
        System.out.println(
            solution.lengthLongestPath(path1)
        );
    }

    // Using hashmap, keep track of max length at each level
    // later on add the max lengths together 14ms
    public int lengthLongestPath(String input) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,0);
        int result = 0;
        for (String s : input.split("\n")) {
            int currentLevel = s.lastIndexOf("\t") + 1;
            int length = s.replaceAll("\t","").length();
            if (s.contains(".")) {
                result = Math.max(result, map.get(currentLevel) + length);
            } else {
                map.put(currentLevel + 1, map.get(currentLevel) + length + 1);
            }
        }
        return result;
    }
}
