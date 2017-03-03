package leetCode;

import java.util.*;

/**
 * This is going to be fucking crazy TODO: Sometime this week
 *
 * [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ]
 * https://leetcode.com/problems/the-skyline-problem/?tab=Description
 * Created by nachiketlondhe on 2/20/17.
 */
public class Skyline {
    public static void main(String args[]) {
        int[][] buildings = new int[5][3];
        buildings[0] = new int[]{2, 9, 10};
        buildings[1] = new int[]{3, 7, 15};
        buildings[2] = new int[]{5, 12, 12};
        buildings[3] = new int[]{15, 20, 10};
        buildings[4] = new int[]{19, 24, 8};

        Skyline solution = new Skyline();

        List<int[]> skyline = solution.getSkyline(buildings);

        System.out.println("Got skyine");
    }

    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> skyline = new ArrayList<>();
        List<int[]> heightMap = new ArrayList<>();

        // Add negative height at start and positive height at end
        for (int[] b: buildings) {
            heightMap.add(new int[]{b[0], -b[2]});
            heightMap.add(new int[]{b[1], b[2]});
        }

        Collections.sort(heightMap, (a, b) -> {
           // if the start points are different
           if (a[0] != b[0])
               return a[0] - b[0];
           // same start point different heights
           return a[1] - b[1];
        });

        // Use a maxHeap to store possible heights
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));

        pq.offer(0);

        int prevMaxHeight = 0;

        for (int[] h: heightMap) {
            // if its a start point add the height
            if (h[1] < 0) {
                pq.offer(-h[1]);
            } else {
                pq.remove(h[1]);
            }

            // Look at the current max height
            int currentMaxHeight = pq.peek();

            if (prevMaxHeight != currentMaxHeight) {
                skyline.add(new int[] {h[0], currentMaxHeight});
                prevMaxHeight = currentMaxHeight;
            }
        }
        return skyline;
    }
}
