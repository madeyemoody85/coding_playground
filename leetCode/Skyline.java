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

/**
Input: [4, 1, 2, 3, 5, 5] [4, 1, 3]
        Longest Path: [1, 2, 3]
        Output: 3

        import java.util.PriorityQueue;

public int findLengthOfLongestConseqPath(int[] input) {
        if (input == null || input.length == 0) {
        return 0;
        }

        int maxLength = 1;
        int startIndex = 0;
        PriorityQueue<Integer> queue = new PriorityQueue((a, b) -> (b - a));
        for (int i = 1; i < input.length; i++) {
        if ((input[i] - input[startIndex]) == 1) {
        maxLength++;
        startIndex = i;
        } else {
        queue.offer(maxLength);
        startIndex = i;
        maxLength = 1;
        }
        }

        if (!queue.isEmpty()) {
        return queue.peek();
        }
        return maxLength;
        }


        [4, 1, 2, 3]
public int findLengthOfLongestConseqPath(int[] input) {
        if (input == null || input.length == 0) {
        return 0;
        }

        int maxLength = 1;
        int startIndex = 0;
        int newMaxLength = 1;
        for (int i = 1; i < input.length; i++) {
        if ((input[i] - input[startIndex]) == 1) {
        newMaxLength++;
        startIndex = i;
        } else {
        maxLength = Math.max(maxLength, newMaxLength);
        startIndex = i;
        newMaxLength = 1;
        }
        }

        return Math.max(maxLength, newMaxLength);
        }
 **/

/**
 class Pramp {
 public static void main(String[] args) {
 String pramp = "Practice Makes Perfect";
 System.out.println(pramp);
 }

 public int[] multiplyAllButThis(int[] arr) {
 int[] productArr = new int[arr.length];

 int product = 1;
 for (int i = 0; i < arr.length-1; i++) {
 productArr[i] *= product;
 product *= arr[i];
 }

 product = 1;

 for (int i = arr.length - 1; i >= 0 ; i--) {
 productArr[i] *= product;
 product *= arr[i];
 }
 }
 }


 productArr = [1, 1, 1, 1]

 [1, 2, 3, 4]

 -> [1, 1, 2, 6]



 , 1*product=1*4*3 , 2*product=1*4 ,6*product=1]


 [1, 1, 2, 6]

 [1, 1, 6, 6]

 