package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * For description please check out: https://leetcode.com/problems/queue-reconstruction-by-height/?tab=Description
 */
public class QueueReconstruction {

    public static void main(String args[]) {

        int[][] input = new int[][] {
                new int[]{7,1},
                new int[]{4,4},
                new int[]{7,0},
                new int[]{5,0},
                new int[]{6,1},
                new int[]{5,2},
        };

        QueueReconstruction solution = new QueueReconstruction();
        int [][] answer = solution.reconstructQueue(input);
    }

    public int[][] reconstructQueue(int[][] people) {

        int[][] answer = new int[people.length][2];
        ArrayList<int[]> someFuckingList = new ArrayList<>();

        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // If two people have same height sort them based on the number
                // of people in front of them
                if (o2[0] == o1[0]) return o1[1] - o2[1];
                return o2[0] - o1[0];
            }
        });

        for (int i = 0; i < people.length; i++) {
            someFuckingList.add(people[i][1], new int[]{ people[i][0], people[i][1]} );
        }

        for (int i = 0; i < someFuckingList.size(); i++) {
            answer[i][0] = someFuckingList.get(i)[0];
            answer[i][1] = someFuckingList.get(i)[1];
        }

        return answer;
    }
}

