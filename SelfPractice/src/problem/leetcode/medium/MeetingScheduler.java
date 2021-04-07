package problem.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/meeting-scheduler/
 * Given the availability time slots arrays slots1 and slots2 of two people and a meeting duration duration,
 * return the earliest time slot that works for both of them and is of duration duration.
 *
 * Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
 * Output: [60,68]
 */
public class MeetingScheduler {
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        List<Integer> output = new ArrayList<>();

        if(slots1.length == 0 || slots2.length == 0)
            return output;
        Arrays.sort(slots1, (a1, a2) -> a1[0] - a2[0]);
        Arrays.sort(slots2, (a1, a2) -> {
            return a1[0] - a2[0];
        });

        // take 2 pointer, pointing to first element of each slots
        // there is an overlap, if laterStartTime < firstFinishTime
        int a = 0;
        int b = 0;
        while(a < slots1.length && b < slots2.length) {
            int lastStart = Math.max(slots1[a][0], slots2[b][0]);
            int firstEnd = Math.min(slots1[a][1], slots2[b][1]);
            // if there is an overlap, check the size of overlap
            if(firstEnd - lastStart >= duration) {
                output.add(lastStart);
                output.add(lastStart + duration);
                return output;
            } else {
                // increment the index of the slot that finished first
                if(slots1[a][1] == firstEnd)
                    a++;
                if(slots2[b][1] == firstEnd)
                    b++;
            }
        }
        return output;
    }
}
