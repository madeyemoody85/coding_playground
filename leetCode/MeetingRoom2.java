package leetCode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/meeting-rooms-ii/?tab=Description
 *
 * Created by nachiketlondhe on 3/6/17.
 */
public class MeetingRoom2 {

    static class Interval {
        int start;
        int end;

        public Interval() {
            this.start = 0;
            this.end = 0;
        }

        public Interval(int s, int e) {
            this.start = s;
            this.end = e;
        }
    }

    public static void main(String args[]) {

//        Interval[] meetings = new Interval[5];
//
//        meetings[0] = new Interval(2,15);
//        meetings[2] = new Interval(9,29);
//        meetings[1] = new Interval(36,45);
//        meetings[4] = new Interval(4,9);
//        meetings[3] = new Interval(16,23);
//
        Interval[] meetings = new Interval[3];
        meetings[0] = new Interval(1,5);
        meetings[1] = new Interval(8,9);
        meetings[2] = new Interval(8,9);

        int numberOfMeetingRooms = minMeetingRooms(meetings);

        System.out.println("Number of meeting rooms: " + numberOfMeetingRooms);
    }

    /**
     * Following solution uses min heap
     * @param intervals
     * @return
     */
    public static int minMeetingRooms(Interval[] intervals) {

        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        // Sort the meetings
        Arrays.sort(intervals, (a, b) -> (a.start - b.start));

        // Priority queue to find the room thats available first
        PriorityQueue<Interval> pq = new PriorityQueue<>(intervals.length, (a, b) -> (a.end - b.end));

        pq.offer(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {

            Interval temp = pq.poll();

            // Current meeting ends when the old one ends
            // or it start after
            // Merge the end of current meeting into the old one
            // room is busy forever
            if (intervals[i].start >= temp.end) {
                temp.end = intervals[i].end;
            } else {
                // There is no romm so add one
                pq.offer(intervals[i]);
            }

            pq.offer(temp);
        }

        return pq.size();
    }


    /**
     * [[2,15],[4,9],[9,29],[16,23],[36,45]]
     * WRONG SOLUTION: In the following solution you cant really keep track of
     * multiple rooms ending at different times
     * sort the meetings
     */
    public static int minMeetingRooms2(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        int numberOfMeetingRooms = 1;

        Arrays.sort(intervals, (Interval i1, Interval i2) -> {
            if (i1.start == i2.start)
                return i1.end - i2.end;
            return i1.start - i2.start;
        });

        int currentStartTime = intervals[0].start;
        int currentEndTime = intervals[0].end;
        int earliestAvailable = intervals[0].end;

        for (int i = 1; i < intervals.length; i++) {

            if (intervals[i].start < earliestAvailable) {

                if (intervals[i].start == currentStartTime) {
                    numberOfMeetingRooms++;
                } else if (intervals[i].start > currentStartTime && intervals[i].end < currentEndTime) {
                    numberOfMeetingRooms++;
                } else if (intervals[i].start < currentEndTime) {
                    numberOfMeetingRooms++;
                }

                earliestAvailable = Math.min(intervals[i].end, earliestAvailable);
            }
            currentStartTime = intervals[i].start;
            currentEndTime = intervals[i].end;
        }

        return numberOfMeetingRooms;
    }

}
