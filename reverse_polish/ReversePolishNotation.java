package reverse_polish;

import java.util.Stack;

/**
 * Created by nachiketlondhe on 1/23/16.
 *
 * Test prep for reverse polish notation 2 3 + 5 - --> ((2+3) - 5)
 */
public class ReversePolishNotation {

    public static void main(String args[]) {

        String[] argumentList = {"2","3","*","5","+","6", "-"};

        System.out.println("Value of rpn: " + evaluateRPN(argumentList));
    }

    public static int evaluateRPN(String[] args) {

        String operators = "+-*/";

        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < args.length; i++) {

            if(!operators.contains(args[i])) {
                stack.push(Integer.valueOf(args[i]));
            } else {

                int a = stack.pop();
                int b = stack.pop();

                int c = operators.indexOf(args[i]);

                switch (c) {

                    case 0:
                        stack.push(a + b);
                        break;

                    case 1:
                        stack.push(a - b);
                        break;

                    case 2:
                        stack.push(a * b);
                        break;

                    case 3:
                        stack.push(a / b);
                        break;
                }
            }
        }

        return stack.pop();
    }

}
