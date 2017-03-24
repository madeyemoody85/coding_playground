package dynamic_programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by nachiketlondhe on 3/13/17.
 */
public class KnapSack {

    public static void main(String args[]) {
        int[] candidates = new int[]{2,3,6,7};
        int target = 7;

        List<List<Integer>> result = combinationSum(candidates, target);
    }
    // This is not really a knapsack problem
    // I should call this a coin change possibilities
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        if ( target <= 0 ) {
            return new ArrayList<>();
        }

        List<List<Integer>> dp = new ArrayList<>();
        for (int i = 0; i < target + 1; i++) {
            dp.add(new ArrayList<>());
        }

        // Sort the given values in ascending order
        Arrays.sort(candidates);

        for (int i = 1; i <= target; i++) {

            for (int j = 0; j < candidates.length - 1; j++) {
                // The sum we are trying to find is less than the sum of the running target
                if (i < candidates[j]) continue;

                int temp = i - candidates[j];

                List<Integer> tempResult = new ArrayList<>();

                if (temp == 0) {
                    tempResult.add(candidates[j]);
                } else if (!dp.get(temp).isEmpty() ) {
                    tempResult.add(candidates[j]);
                    tempResult.addAll(dp.get(temp));
                } else {
                    continue;
                }

                dp.set(i, tempResult);
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0 ; i <= candidates.length - 1; i ++) {

            int temp = target - candidates[i];

            if (temp == 0) {
                result.add(Arrays.asList(candidates[i]));
            } else if (!dp.get(temp).isEmpty() ) {
                Collections.addAll(dp.get(temp), candidates[i]);
                result.add(dp.get(temp));
            }

        }

        return result;
    }


    // Following solution is same as the one you have written and IT did GET ACCEPTED. Move the loop in if else on line
    // 46
    public List<List<Integer>> combinationSum2(int[] cands, int t) {
        Arrays.sort(cands);

        List<List<List<Integer>>> dp = new ArrayList<>();

        for (int i = 1; i <= t; i++) {

            List<List<Integer>> newList = new ArrayList();

            // run through all candidates <= i
            for (int j = 0; j < cands.length && cands[j] <= i; j++) {

                // special case when curr target is equal to curr candidate
                if (i == cands[j]) newList.add(Arrays.asList(cands[j]));

                // if current candidate is less than the target use prev results
                else for (List<Integer> l : dp.get(i-cands[j]-1)) {
                    if (cands[j] <= l.get(0)) {
                        List cl = new ArrayList<>();
                        cl.add(cands[j]); cl.addAll(l);
                        newList.add(cl);
                    }
                }
            }
            dp.add(newList);
        }
        return dp.get(t-1);
    }
}
