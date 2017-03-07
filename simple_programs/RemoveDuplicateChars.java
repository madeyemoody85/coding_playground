package simple_programs;

public class RemoveDuplicateChars {
    public static void main(String args[]) {
        String input = "aabggdhyrtttfsrfdhfjj";

        char[] str = input.toCharArray();

        removeDuplicates(str);
    }

    /**
     *  Remove duplicates without using any additional buffers
     *  One or two extra variables is ok
     */
    public static void removeDuplicates(char[] input) {
        if (input == null) return;
        int length = input.length;
        if (length < 2) return ;

        int duplicateIndex = Integer.MIN_VALUE;

        for (int i = 1; i < length; i++) {
            int j;
            for (j = 0; j < length; j++) {
                if (input[i] == input[j] && i > j) {
                    duplicateIndex = j;
                    break;
                }
            }
            input[duplicateIndex] = 0;
        }

        helperMoveZeroes(input);

        System.out.println("doing somethingß");
    }

    public static void helperMoveZeroes(char[] input) {
        int insertPosition = 0;

        for (int i = 0;i < input.length; i++) {
            if (input[i] != 0) {
                input[insertPosition++] = input[i];
            }
        }

        while (insertPosition < input.length) {
            input[insertPosition++] = 0;
        }
    }
}