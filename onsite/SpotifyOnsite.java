package onsite;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by nachiketlondhe on 4/3/17.
 */
public class SpotifyOnsite {

    public static void main(String args[]) {
        SpotifyOnsite solution = new SpotifyOnsite();

//        System.out.println(solution.compareVersion("1","1.1"));

        String[] toJustify = new String[] {"This", "is", "an", "example", "of", "text", "justification."};
        solution.fullJustify(new ArrayList<String>(Arrays.asList(toJustify)), 16);
    }

    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int i = 0;
        int j = 0;

        int result = 0;

        /*
         * v1 = 0.1
         * v2 = 0.0.1
         */
        while (i < v1.length && j < v2.length) {
            int v1i = Integer.parseInt(v1[i]);
            int v2j = Integer.parseInt(v2[j]);

            result = v1i - v2j;

            i++;
            j++;

            if (result >= 1) {
                return 1;
            } else if (result < 0) {
                return -1;
            }
        }

        if (v1.length != v2.length) {
            if (i > v1.length - 1) return -1;

            if (j > v2.length - 1) return 1;
        }

        if (i < j) {
            return -1;
        } else if (i > j) {
            return 1;
        } else {
            return 0;
        }
    }

    public int compareVersion2(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");

        for ( int i = 0; i < Math.max(v1.length, v2.length); i++ ) {
            int num1 = i < v1.length ? Integer.parseInt( v1[i] ) : 0;
            int num2 = i < v2.length ? Integer.parseInt( v2[i] ) : 0;
            if ( num1 < num2 ) {
                return -1;
            } else if ( num1 > num2 ) {
                return +1;
            }
        }

        return 0;
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public ArrayList<Integer> recoverTree(TreeNode a) {
        ArrayList<Integer> result = new ArrayList<>();
        helperRecoverTree(a, result);
        return result;
    }

    public void helperRecoverTree(TreeNode a, ArrayList<Integer> result) {
        if (a == null) {
            return;
        }

        if (a.left != null) {
            if (a.left.val > a.val) {
                result.add(a.val);
                result.add(a.left.val);
                return;
            }
        }

        if (a.right != null) {
            if (a.right.val < a.val) {
                result.add(a.val);
                result.add(a.right.val);
                return;
            }
        }

        helperRecoverTree(a.left, result);
        helperRecoverTree(a.right, result);
    }

    public ArrayList<String> fullJustify(ArrayList<String> a, int b) {
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0, nextword; i < a.size(); i = nextword) {
            // current length;
            int l = -1;

            // Gather all the words till you exceed the given line length
            for (nextword = i; nextword < a.size() && a.get(nextword).length() + 1 + l <= b; nextword++ ) {
                l += a.get(nextword).length() + 1;
            }

            // Add the first word to the string builder
            StringBuilder sb = new StringBuilder(a.get(i));

            // We need atleast one space and no extra spaces to begin with
            int spaces = 1, extra = 0;
            if (nextword != i + 1 && nextword != a.size()) {
                spaces = (b-l)/(nextword - i - 1) + 1;

                //get the remaining spaces using mod
                extra = (b-l)%(nextword - i - 1);
            }

            for (int j = i +1; j<nextword; j++) {
                for (int s = 0;s < spaces;s++) sb.append(' ');
                if (extra-- > 0) sb.append(' ');
                sb.append(a.get(j));
            }

            int remaining = b - sb.length();
            while(remaining-- > 0) sb.append(' ');
            list.add(sb.toString());
        }

        return list;
    }
}
