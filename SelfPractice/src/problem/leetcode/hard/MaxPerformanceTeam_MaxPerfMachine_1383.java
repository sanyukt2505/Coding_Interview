package problem.leetcode.hard;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/maximum-performance-of-a-team/
 * Same problem can be used for machine performance in a cluster
 * There are n engineers numbered from 1 to n and two arrays: speed and efficiency, where speed[i]
 * nd efficiency[i] represent the speed and efficiency for the i-th engineer respectively.
 * Return the maximum performance of a team composed of at most k engineers, since the answer can be a huge number, return this modulo 10^9 + 7.
 *
 * The performance of a team is the sum of their engineers' speeds multiplied by the minimum efficiency among their engineers.
 * Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 2
 * Output: 60
 */
public class MaxPerformanceTeam_MaxPerfMachine_1383 {

    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        if(speed.length == 0 || efficiency.length == 0)
            return 0;
        int[][] ess = new int[n][2];
        for(int i = 0; i < n; i++)
        {
            ess[i] = new int[] {efficiency[i],speed[i]};
        }
        // sort the elements based on efficiency
        // e = [9,7,5,4,3,2] after the sort
        // s = [1,5,2,10,3,8]
        Arrays.sort(ess, (a,b) -> (b[0] - a[0]));

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        long res = 0, sum = 0;
        // add the speed to a minheap of size k
        // calculate the sum after adding the speed
        // for calculating the current performance, one has to use the current efficiency, curr Eff will be the smallest
        for(int[] es : ess)
        {
            pq.add(es[1]);
            sum += es[1];
            if(pq.size() > k)
            {
                sum -= pq.peek();
                pq.poll();
            }
            res = Math.max( res, (sum*es[0]));
        }
        return (int) (res%(long)(1e9+7) );
    }
}
