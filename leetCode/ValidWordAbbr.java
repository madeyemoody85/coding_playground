package leetCode;

import java.util.HashMap;

/**
 * For description follow: https://leetcode.com/problems/unique-word-abbreviation/?tab=Description
 *
 * Created by nachiketlondhe on 2/20/17.
 */
public class ValidWordAbbr {

    HashMap<String,String> abbrsAvailable;

    public ValidWordAbbr(String[] dictionary) {
        abbrsAvailable = new HashMap();

        for (String word : dictionary) {
            String abbr = getAbbr(word);

            // If there exist another word whose abbreviation is the same
            // as one already existing replace the word with "" (make it invalid)
            if (abbrsAvailable.containsKey(abbr)) {
                if (!abbrsAvailable.get(abbr).equals(word)) {
                    abbrsAvailable.put(abbr, "");
                }
            } else {
                abbrsAvailable.put(abbr, word);
            }
        }
    }

    public boolean isUnique(String word) {
        String abbr = getAbbr(word);
        return !abbrsAvailable.containsKey(abbr) || abbrsAvailable.get(abbr).equals(word);
    }

    private String getAbbr(String word) {
        if (word.length() <= 2) {
            return word;
        }

        return word.charAt(0) + Integer.toString(word.length()-2) + word.charAt(word.length()-1);
    }

    public static void main(String args[]) {
        ValidWordAbbr solution = new ValidWordAbbr(new String[]{"deer","door","cake","card"});
        System.out.println(solution.isUnique("dear"));
        System.out.println(solution.isUnique("door"));
        System.out.println(solution.isUnique("cart"));
        System.out.println(solution.isUnique("cake"));
    }
}
