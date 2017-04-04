package code_fights;

import java.util.*;

/**
 * Created by nachiketlondhe on 4/2/17.
 */
public class SortByHeightHasTrees {

    public static void main(String args[]) {

        int[] heightsWithTrees = new int[] { -1, 150, 190, 170, -1, -1, 160, 180 };
//        sortByHeight(heightsWithTrees);
        System.out.println(reverseParentheses("a(bcdefghijkl(mno)p)q"));
    }

    public static int[] sortByHeight(int[] a) {
        for (int i = 0; i < a.length - 1; i++)
        {
            if (a[i] == -1) continue;
            int index = i;
            for (int j = i + 1; j < a.length; j++){
                if (a[j] == -1) continue;
                if (a[j] < a[index]){
                    index = j;//searching for lowest index
                }
            }
            int smallerNumber = a[index];
            a[index] = a[i];
            a[i] = smallerNumber;
        }
        return a;
    }

    /**
     * s = "a ( b c d ( m n o ) p ) q"
     *                  o n m
     * exepcted = a p m n o d c b q
     */
    public static String reverseParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        Stack stack = new Stack();
        int openParens = 0;

        for (char c: s.toCharArray()) {
            if (c != '(' && c != ')' && openParens == 0) {
                sb.append(c);
                continue;
            }
            if (c == '(') {
                openParens++;
                stack.push(c);
            } else if (c == ')') {

                if (openParens > 1) {
                    Queue temp = new LinkedList();

                    while ((Character) stack.peek() != '(') {
                        temp.offer(stack.pop());
                    }

                    stack.pop();
                    openParens--;

                    while (!temp.isEmpty()) {
                        stack.push(temp.poll());
                    }
                } else if (openParens == 1) {

                    while ( (Character) stack.peek() != '(' && !stack.isEmpty()) {
                        sb.append(stack.pop());
                    }
                    stack.pop();
                    openParens--;
                }
            } else {
                stack.push(c);
            }
        }

        return sb.toString();
    }

}
