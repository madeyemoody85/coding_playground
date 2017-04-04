package code_fights;

/**
 * https://codefights.com/arcade/intro/level-3/3AdBC97QNuhF6RwsQ
 *
 * Created by nachiketlondhe on 4/1/17.
 */
public class IsLucky {

    public static void main(String args[]) {
        System.out.println("is lucky: " + isLuck(1230));
        System.out.println("is lucky: " + isLuck(239017));
        System.out.println("is lucky: " + isLuck(123123));
    }
    public static boolean isLuck(int n) {
        int leftSum = 0, rightSum = 0;

        char[] number = Integer.toString(n).toCharArray();

        int i = 0, j = number.length - 1;

        while (i < j) {
            leftSum += Character.getNumericValue(number[i]);
            rightSum += Character.getNumericValue(number[j]);
            i++;
            j--;
        }

        return leftSum == rightSum;
    }
    String reflectString(String inputString) {
        char[] stringReflection = inputString.toCharArray();
        for (int i = 0 ; i < stringReflection.length; i ++) {
            stringReflection[i] = (char) ('z' - (stringReflection[i] - 'a'));
        }

        return new String(stringReflection);
    }
}
