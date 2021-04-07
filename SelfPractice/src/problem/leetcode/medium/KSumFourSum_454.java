package problem.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

public class KSumFourSum_454 {

    /**
     * https://leetcode.com/problems/4sum-ii/solution/
     * a+b+c+d = 0, we can observe that a + b == -(c + d)
     *
     * First, we will count sums of elements a + b from the first two arrays using a hashmap.
     * Then, we will enumerate elements from the third and fourth arrays,
     * and search for a complementary sum a + b == -(c + d) in the hashmap.
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int cnt = 0;
        Map<Integer, Integer> m = new HashMap<>();
        for (int a : A)
            for (int b : B)
                m.put(a + b, m.getOrDefault(a + b, 0) + 1);
        for (int c : C)
            for (int d : D)
                cnt += m.getOrDefault(-(c + d), 0);
        return cnt;
    }

//    ---------------------------------------------------------

    /**
     * Same way, we will divide k arrays into two groups. For the first group, we will have k/2 nested loops to count
     * sums. Another k/2 nested loops will enumerate arrays in the second group and search for complements.
     * Complexity: O(n^(k/2))
     *
     * using this approach a 6-Sum can be done in in O(n^3)
     */
    public int fourSumCountUsingKSum(int[] A, int[] B, int[] C, int[] D) {
        return kSumCount(new int[][]{A, B, C, D});
    }

    public int kSumCount(int[][] lists) {
        Map<Integer, Integer> m = new HashMap<>();
        addToHash(lists, m, 0, 0);
        return countComplements(lists, m, lists.length / 2, 0);
    }

    void addToHash(int[][] lists, Map<Integer, Integer> m, int iter, int iterSum) {
        if (iter == lists.length / 2)
            m.put(iterSum, m.getOrDefault(iterSum, 0) + 1);
        else
            for (int a : lists[iter])
                addToHash(lists, m, iter + 1, iterSum + a);
    }

    int countComplements(int[][] lists, Map<Integer, Integer> m, int iter, int complement) {
        if (iter == lists.length)
            return m.getOrDefault(complement, 0);
        int cnt = 0;
        for (int a : lists[iter])
            cnt += countComplements(lists, m, iter + 1, complement - a);
        return cnt;
    }
}
