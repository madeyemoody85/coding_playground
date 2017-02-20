package leetCode;

/**
 * Created by nachiketlondhe on 2/19/17.
 */
public class LicenseKeyFormatting {

    public static void main(String args[]) {
        String input = "2-4A0r7-4k";
        int groupSize = 3;
        LicenseKeyFormatting solution = new LicenseKeyFormatting();

        String formattedString = solution.licenseKeyFormatting(input, groupSize);
        System.out.println(formattedString);
    }

    public String licenseKeyFormatting(String S, int K) {
        if (S == null || S.isEmpty()) {
            return S;
        }

        StringBuilder sb = new StringBuilder();
        int length = S.length();

        int temp = 0;

        for (int i = length-1; i >= 0 ; i--) {
            if (S.charAt(i) != '-') {
                if (temp < K) {
                    sb.append(S.charAt(i));
                    temp++;
                } else {
                    sb.append('-');
                    sb.append(S.charAt(i));
                    temp = 1;
                }
            }
        }

        return sb.reverse().toString().toUpperCase();
    }
}
