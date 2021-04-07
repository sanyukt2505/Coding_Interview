package problem.leetcode.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/my-calendar-i/
 * MyCalendar();
 * MyCalendar.book(10, 20); // returns true
 * MyCalendar.book(15, 25); // returns false
 * MyCalendar.book(20, 30); // returns true
 */
public class MyCalendar_729 {
    List<int[]> calendar;

    MyCalendar_729() {
        calendar = new ArrayList<>();
        treeMap = new TreeMap();
    }

    public boolean book(int start, int end) {
        for (int[] iv: calendar) {
            if (iv[0] < end && start < iv[1]) return false;
        }
        calendar.add(new int[]{start, end});
        return true;
    }

    //---------  TreeMap implementation ----------------
    TreeMap<Integer, Integer> treeMap;

    public boolean bookTreeMap(int start, int end) {
        Integer prev = treeMap.floorKey(start),
                next = treeMap.ceilingKey(start);
        if ((prev == null || treeMap.get(prev) <= start) &&
                (next == null || end <= next)) {
            treeMap.put(start, end);
            return true;
        }
        return false;
    }
}
