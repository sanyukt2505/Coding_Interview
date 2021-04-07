package problem.leetcode.medium;

import java.util.Stack;

/**
 * https://leetcode.com/problems/course-schedule/
 * https://leetcode.com/problems/course-schedule-ii/
 *
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 */
public class CourseSchedulePossible_207_210 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        /** if need to return the sequence of Courses */
//        int[] result = new int[numCourses];
//        if (prerequisites.length == 0) {
//            for (int i = 0; i < numCourses; i++) {
//                result[i] = i;
//            }
//            return result;
//        }

        int[] preReqCount = new int[numCourses];
        int count = 0;

        //keeping a count of prerequisites of each course
        for (int i = 0; i < prerequisites.length; i++) {
            preReqCount[prerequisites[i][0]]++;
        }

        Stack<Integer> stack = new Stack<>();

        // find a  course that doesn't have a preReq
        for (int i = 0; i < preReqCount.length; i++) {
            if (preReqCount[i] == 0) {
                stack.push(i);
            }
        }

        // stack always contains the courses that doesn't have a prereq or the preReq is completed
        while (!stack.isEmpty()) {
            int curr = stack.pop();
            count++;
//            result[count] == curr;

            // taking the preReq loop through the original list and see which courses has this curr prereq
            // and then decrease the prereq count of those courses
            for (int i = 0; i < prerequisites.length; i++) {
                if (prerequisites[i][1] == curr) {
                    preReqCount[prerequisites[i][0]]--;
                    if (preReqCount[prerequisites[i][0]] == 0) {
                        stack.push(prerequisites[i][0]);
                    }
                }
            }
        }
        return count == numCourses;
        /** if need to return the sequence of Courses */
//        if (count != numCourses) {
//            return new int[0];
//        }
//
//        for (int i = 0; i < schedule.size(); i++) {
//            result[i] = schedule.get(i);
//        }
//        return result;
    }
}
