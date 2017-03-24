package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a phone numpad and a digit string. Find out all the possible string combinations
 * Created by nachiketlondhe on 2/2/17.
 */
public class LetterCombinations {
    public static void main(String args[]) {
        LetterCombinations solution = new LetterCombinations();

        List<String> possibleCombinations = solution.letterCombinations("345");
        System.out.println("All possible combinations: " + possibleCombinations.size());
    }

    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) {
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>();

        String charMap[] = new String[]{"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        result.add("");

        for ( int i = 0; i < digits.length(); i++) {

            int num = digits.charAt(i) - '0';

            List<String> temp = new ArrayList<>();

            String nextDigit = charMap[num];

            if (nextDigit.isEmpty()) {
                continue;
            }

            for (int j = 0; j < nextDigit.length(); j++ ) {

                for (int k = 0; k < result.size(); k++ ) {
                    temp.add(result.get(k) + nextDigit.charAt(j));
                }
            }

            result = temp;
        }

        return result;
    }
}
