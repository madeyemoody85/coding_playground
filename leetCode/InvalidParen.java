package leetCode;

import java.util.*;

/**
 * "()())()" -> ["()()()", "(())()"]
 *      ^
 * Created by nachiketlondhe on 2/21/17.
 */
public class InvalidParen {

    public static void main(String args[]) {

    }

    /**
     * Apparently we are going to use BFS.
     * First generate all the strings one level at a time if strings are valid add
     * the moment we found a valid string dont generate the possibilities
     *
     * BFS logic -
     * 1. take a string add it to the queue just like regular root
     * @param s string
     * @return valid possibilities
     */
    public List<String> removeInvalidParentheses(String s) {

        List<String> result = new ArrayList<>();

        Set<String> checkedCombinations = new HashSet<>();

        Queue<String> queue = new LinkedList<>();

        queue.add(s);

        while (!queue.isEmpty()) {

            // Get the from the queue (First in first out order)
            String temp = queue.poll();

            if (isValid(temp)) {
                result.add(temp);

                // we have found that this string is valid so continue
                // dont generate different possibilities
                continue;
            }

            for (int i = 0; i < temp.length(); i++) {
                if (temp.charAt(i) != '(' && temp.charAt(i) != ')') {
                    continue;
                }

                String possibility = s.substring(0,i) + s.substring(i+1);
                if (!checkedCombinations.contains(possibility)) {
                    checkedCombinations.add(possibility);
                    queue.add(possibility);
                }
            }
        }

        return result;
    }

    public boolean isValid(String s) {
        if(s == null) {
            return false;
        }

        int count = 0;
        for( int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') count++;
            if (c == ')' && count-- == 0) return false;
        }

        return true;
    }
}
