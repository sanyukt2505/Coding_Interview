package problem.leetcode.medium;

import java.util.Arrays;


public class MeetingRooms_252_253 {
    /**
     * https://leetcode.com/problems/meeting-rooms-ii/
     * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
     * find the minimum number of conference rooms required.
     * Input: [[0, 30],[5, 10],[15, 20]]    Output: 2
     */
    public int minMeetingRooms(int[][] intervals) {
        int[] start = new int[intervals.length];
        int[] end = new int[intervals.length];

        /** make 2 array of start and end times -- sort them
         * Increment roomCount when the next meeting starts before the last one ends */
        for (int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }

        Arrays.sort(start);
        Arrays.sort(end);
        int startPointer = 0;
        int endPointer = 0;
        int rooms = 0;

        while (startPointer < intervals.length) {
            if (start[startPointer] >= end[endPointer]) {
                rooms--;
                endPointer++;
            }
            /** whenever a new meeting start, room count is incremented
             *  whether a room got freed or not     */
            rooms++;
            startPointer++;
        }
        return rooms;
    }

    /**
     * https://leetcode.com/problems/meeting-rooms/
     * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
     * determine if a person could attend all meetings.
     * Input: [[0,30],[5,10],[15,20]]       Output: false
     */
    public static boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i-1][1])
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(canAttendMeetings(new int[][]{{2,3}, {4,5}, {5,6}, {3,4}}));
    }
}
