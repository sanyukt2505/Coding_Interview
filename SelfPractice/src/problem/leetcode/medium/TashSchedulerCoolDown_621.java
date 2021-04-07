package problem.leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/task-scheduler/
 * Given a characters array tasks, representing the tasks a CPU needs to do, where each letter represents a different task.
 * Tasks could be done in any order. Each task is done in one unit of time. For each unit of time, the CPU could complete either one task or just be idle.
 *
 * However, there is a non-negative integer n that represents the cooldown period between two same tasks
 * (the same letter in the array), that is that there must be at least n units of time between any two same tasks.
 *
 * Return the least number of units of times that the CPU will take to finish all the given tasks.
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation:
 * A -> B -> idle -> A -> B -> idle -> A -> B
 * There is at least 2 units of time between any two same tasks.
 */
public class TashSchedulerCoolDown_621 {
    public int leastInterval(char[] tasks, int n) {
        HashMap<Character, Integer> map = new HashMap();
        for(char c: tasks) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        maxHeap.addAll(map.values());

        int cycles = 0;

        while(!maxHeap.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < n + 1; i++) {
                if (!maxHeap.isEmpty()) {
                    /** get the task that has highest occurences and add it to the list
                    // once you remove it the same task can't get scheduled until n cycles are passed  */
                    temp.add(maxHeap.remove());
                }
            }
            /** if the task has multiple cycles it should be added back to the heap */
            for (int i : temp) {
                if(--i > 0) {
                    maxHeap.add(i);
                }
            }
            /** at the end of n cycles either u have n + 1 cycles of (tasks or idles)
            // unless the heap was empty and u added lesser than n  */
            cycles += maxHeap.isEmpty() ? temp.size() : n + 1;
        }
        return cycles;
    }
}
