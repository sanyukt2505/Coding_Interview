package problem.leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Write a function that takes in a list of (student ID number, course name) pairs and returns, for every pair of students, a list of all courses they share.
 * student_course_pairs_1 = [
 *   ["58", "Linear Algebra"],
 *   ["94", "Art History"],
 *   ["94", "Operating Systems"],
 *   ["17", "Software Design"],
 *   ["58", "Mechanics"],
 *   ["58", "Economics"],
 *   ["17", "Linear Algebra"],
 *   ["17", "Political Science"],
 *   ["94", "Economics"],
 *   ["25", "Economics"],
 *   ["58", "Software Design"] ]
 *
 *   Output:
 *   ind_pairs(student_course_pairs_1) =>
 * [
 *   [58, 17]: ["Software Design", "Linear Algebra"]
 *   [58, 94]: ["Economics"]
 *   [58, 25]: ["Economics"]
 *   [94, 25]: ["Economics"]
 *   [17, 94]: []
 *   [17, 25]: []   ]
 */
public class StudentCoursesShareList {

    public static Map<int[], List<String>> getPossiblePairs(String[][] input){
        Map<int[], List<String>> result = new HashMap();
        Map<Integer, Set<String>> adjList = new HashMap<>();
        List<Integer> studentIds = new ArrayList<>();
        for(String[] course : input){
            int studentId = Integer.parseInt(course[0]);
            if(!adjList.containsKey(studentId))
                studentIds.add(studentId);

            adjList.putIfAbsent(studentId, new HashSet<>());
            adjList.get(studentId).add(course[1]);
        }
        for(int i = 0; i < studentIds.size(); i++){
            int curr = studentIds.get(i);
            for(int j = i + 1; j < studentIds.size(); j++){
                List<String> commonCourses = findCommon(studentIds.get(j), curr, adjList);
                result.put(new int[]{curr, studentIds.get(j)}, commonCourses);
            }
        }
        return result;
    }

    public static List<String> findCommon(int id1, int id2, Map<Integer, Set<String>> adjMap){
        Set<String> student1 = adjMap.get(id1);
        Set<String> student2 = adjMap.get(id2);
        List<String> common = new ArrayList();
        for(String course : student1){
            if(student2.contains(course )){
                common.add(course);
            }
        }
        return common;
    }
}
