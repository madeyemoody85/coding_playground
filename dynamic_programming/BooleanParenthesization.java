package dynamic_programming;

import java.util.HashMap;

/**
 * Created by nachiketlondhe on 4/2/17.
 */
public class BooleanParenthesization {
    public static void main(String args[]) {
        System.out.println(booleanParenthesization("T^T|F^F|T^T&T&T|F|T^T^F^T|T"));
    }

    static int booleanParenthesization(String expression) {
        HashMap<String, Integer> cache = new HashMap();
        return countDP(expression, true, 0, expression.length()-1, cache);
    }

    static private int countDP(String expression, boolean result, int start, int end, HashMap<String, Integer> cache) {
        String key = "" + result + start + end;
        if (cache.containsKey(key)) return cache.get(key);
        // Base case: single value evaluation
        if (start == end) {
            char operand = expression.charAt(start);
            if (result) {
                if (operand == 'T') return 1;
                else if (operand == 'F') return 0;
            } else {
                if (operand == 'F') return 1;
                else if (operand == 'T') return 0;
            }
        }
        // Recursive case: subexpression evaluation
        int cnt = 0;
        if (result) {
            for (int i = start + 1; i < end; i += 2) {
                char operator = expression.charAt(i);
                if (operator == '&') {
                    cnt += countDP(expression, true, start, i - 1, cache) * countDP(expression, true, i + 1, end, cache);
                } else if (operator == '|') {
                    cnt += countDP(expression, true, start, i - 1, cache) * countDP(expression, false, i + 1, end, cache);
                    cnt += countDP(expression, false, start, i - 1, cache) * countDP(expression, true, i + 1, end, cache);
                    cnt += countDP(expression, true, start, i - 1, cache) * countDP(expression, true, i + 1, end, cache);
                } else if (operator == '^') {
                    cnt += countDP(expression, true, start, i - 1, cache) * countDP(expression, false, i + 1, end, cache);
                    cnt += countDP(expression, false, start, i - 1, cache) * countDP(expression, true, i + 1, end, cache);
                } else {
                    throw new IllegalArgumentException("Invalid operator!");
                }
            }
        } else {
            for (int i = start + 1; i < end; i += 2) {
                char operator = expression.charAt(i);
                if (operator == '&') {
                    cnt += countDP(expression, false, start, i - 1, cache) * countDP(expression, false, i + 1, end, cache);
                    cnt += countDP(expression, false, start, i - 1, cache) * countDP(expression, true, i + 1, end, cache);
                    cnt += countDP(expression, true, start, i - 1, cache) * countDP(expression, false, i + 1, end, cache);
                } else if (operator == '|') {
                    cnt += countDP(expression, false, start, i - 1, cache) * countDP(expression, false, i + 1, end, cache);
                } else if (operator == '^') {
                    cnt += countDP(expression, true, start, i - 1, cache) * countDP(expression, true, i + 1, end, cache);
                    cnt += countDP(expression, false, start, i - 1, cache) * countDP(expression, false, i + 1, end, cache);
                } else {
                    throw new IllegalArgumentException("Invalid operator!");
                }
            }
        }
        cache.put(key, cnt);
        return cnt;
    }
}
