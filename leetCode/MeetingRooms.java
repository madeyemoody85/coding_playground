package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * [[0, 30],[5, 10],[15, 20]]
 *
 * can person attend all of the meetings above
 *
 * Created by nachiketlondhe on 3/6/17.
 */
public class MeetingRooms {
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

    public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return true;

        boolean canAttend = true;

        // Sort the meetings
        Arrays.sort(intervals, (a, b) -> (a.start - b.start));

        int prevEnd = intervals[0].end;
        for (int i = 1; i < intervals.length; i++ ) {
            // this meeting is starting before the prev one
            if (intervals[i].start < prevEnd || intervals[i].end < prevEnd) {
                canAttend = false;
            }
            prevEnd = intervals[i].end;
        }
        return canAttend;
    }

    /**
     * Given array of intervals merge all overlapping intervals
     *
     * Given [1,3],[2,6],[8,10],[15,18],
     * return [1,6],[8,10],[15,18].
     */
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) return new ArrayList<>();

        // Sort the intervals
        Collections.sort(intervals, (a, b) -> (a.start - b.start));

        ArrayList<Interval> mergedList = new ArrayList<>();

        Interval temp = null;

        for (Interval i : intervals) {
            if (temp == null) {
                temp = i;
            } else if (i.start <= temp.end) {
                temp.end = Math.max(temp.end, i.end);
            } else {
                mergedList.add(temp);
                temp = i;
            }
        }

        mergedList.add(temp);

        return mergedList;
    }

    /**
     * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
     * You may assume that the intervals were initially sorted according to their start times.
     *
     * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16]
     *
     */
    public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if (intervals == null || intervals.size() == 0) {
            return Arrays.asList(newInterval);
        }

        intervals.add(newInterval);

        Collections.sort(intervals, (a, b) -> (a.start - b.start));

        List<Interval> mergedList = new ArrayList<>();

        Interval temp = null;

        for (Interval i : intervals) {
            if (temp == null) {
                temp = i;
            } else if (i.start <= temp.end) {
                temp.end = Math.max(temp.end, i.end);
            } else {
                mergedList.add(temp);
                temp = i;
            }
        }

        mergedList.add(temp);

        return mergedList;
    }

    public static void main(String args[]) {
        List<Interval> meetings = new ArrayList<>();
        meetings.add(new Interval(1, 5));

        List<Interval> result = insert(meetings, new Interval(2, 3));
    }
}
