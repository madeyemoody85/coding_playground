package leetCode;

/**
 * Created by nachiketlondhe on 2/9/17.
 */
public class ReverseString {

    public String reverseString(String s) {
        if (s == null) {
            return null;
        }
        char[] input = s.toCharArray();

        int i=0;

        int j=s.length()-1;
        while ( i <= j) {
            char c = input[i];
            input[i] = input[j];
            input[j] = c;
            i++;
            j--;
        }
        return new String(input);
    }

//    public String reverseString(String s) {
//        if (s == null) {
//            return null;
//        }
//        char[] input = s.toCharArray();
//
//        int i=0;
//        int j=s.length()-1;
//        while ( i <= j) {
//            input[i] = input[i] ^ input[j];
//            input[j] = input[i] ^ input[j];
//            input[i] = input[i] ^ input[j];
//            i++;
//            j--;
//        }
//        return new String(input);
//    }
}
