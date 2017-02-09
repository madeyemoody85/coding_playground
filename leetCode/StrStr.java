package leetCode;

/**
 * Created by nachiketlondhe on 2/9/17.
 */
public class StrStr {
    public static  void main(String args[]) {
        StrStr solution = new StrStr();

        int index = solution.strStr("mississippi","issip");

        System.out.println("needle index : " +  index);
    }

    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) {
            return 0;
        }
        char[] h = haystack.toCharArray();
        char[] n = needle.toCharArray();
        int haystackIndex = 0;
        int needleIndex = 0;

        while (haystackIndex < haystack.length()) {
            if (h[haystackIndex] == n[needleIndex]) {
                if (needleIndex == needle.length() - 1) {
                    return haystackIndex - needle.length() + 1;
                }
                haystackIndex++;
                needleIndex++;
            } else {
                if(haystack.length() - haystackIndex < needle.length()) {
                    return -1;
                }
                needleIndex = 0;
                haystackIndex++;
            }
        }

        return -1;
    }
}
