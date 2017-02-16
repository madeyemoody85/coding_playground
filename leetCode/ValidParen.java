package leetCode;

import java.util.Stack;

/**
 * Created by nachiketlondhe on 2/9/17.
 */
public class ValidParen {
    public static void main(String args[]) {
        ValidParen solution = new ValidParen();
        solution.isValid("((");
    }

    public boolean isValid(String s) {
        if(s == null || s.length() == 1) {
            return false;
        }

        Stack<Character> stack = new Stack<>();
        for( int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
