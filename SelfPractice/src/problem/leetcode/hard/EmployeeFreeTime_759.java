package problem.leetcode.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/employee-free-time/
 * We are given a list schedule of employees, which represents the working time for each employee.
 * Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
 * Return the list of finite intervals representing common, positive-length free time for all employees,
 * also in sorted order.
 */
public class EmployeeFreeTime_759 {
    /** Merge Interval Approach */
    class Interval {
        public int start;
        public int end;
        public Interval() {}
        public Interval(int _start, int _end) {
            start = _start;
            end = _end;
        }
    }

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> intervals = new ArrayList<>();
        LinkedList<Interval> merged = new LinkedList<>();
        List<Interval> freeSlots = new ArrayList<>();

        for (List<Interval> interval: schedule) {
            intervals.addAll(interval);
        }
        Collections.sort(intervals, (a, b) -> a.start - b.start);

        for (Interval i: intervals)
            System.out.println(i.start + " " + i.end);

        /** merge the intervals */
        for (int i = 0; i < intervals.size(); i++) {
            if (merged.size() == 0 || merged.getLast().end < intervals.get(i).start) {
                merged.add(intervals.get(i));
            } else {
                merged.getLast().end = Math.max(merged.getLast().end, intervals.get(i).end);
            }
        }

        /** get the free slot between the merged intervals */
        for (int i = 1; i < merged.size(); i++) {
            freeSlots.add(new Interval(merged.get(i-1).end, merged.get(i).start));
        }
        return freeSlots;
    }

    /** ----------------------------------------------
     * Making use of the fact the every employee Intervals are sorted within themselves
     */
//    class EmpInterval {
//        Interval interval;
//        int empIndex;
//        int intervalIndex;
//
//        public EmpInterval(Interval i, int empIndex, int intervalIndex) {
//            this.interval = i;
//            this.empIndex = empIndex;
//            this.intervalIndex = intervalIndex;
//        }
//    }
//
//    public List<Interval> findEmployeeFreeTime(List<List<Interval>> schedule) {
//        List<Interval> intervals = new ArrayList<>();
//        PriorityQueue<EmpInterval> q = new PriorityQueue<>((a,b) -> a.interval.start - b.interval.start);
//    }
}
