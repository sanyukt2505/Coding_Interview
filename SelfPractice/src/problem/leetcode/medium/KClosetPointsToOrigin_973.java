package problem.leetcode.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/k-closest-points-to-origin/
 * We have a list of points on the plane.  Find the K closest points to the origin (0, 0)
 *
 * Input: points = [[3,3],[5,-1],[-2,4]], K = 2
 * Output: [[3,3],[-2,4]]
 * (The answer [[-2,4],[3,3]] would also be accepted.)
 *
 * D = sqrt[(x2 - x1) + (y2 - y1)]
 */
public class KClosetPointsToOrigin_973 {
    public static class MyComparator implements Comparator<int []> {
        @Override
        public int compare(int[] a, int[] b) {
            int dist1 = a[0]*a[0] + a[1]*a[1];
            int dist2 = b[0]*b[0] + b[1]*b[1];
            return dist1 - dist2;
        }
    }
    public static int[][] kClosest(int[][] points, int K) {
        // O(N) to build a heap + O(Klog(N)) to extract.
        PriorityQueue<int[]> minHeap = new PriorityQueue(new MyComparator());
        int[][] res = new int[K][];

        for (int i = 0; i < points.length; i++) {
            minHeap.add(points[i]);
        }
        for (int i = 0; i < K; i++) {
            res[i] = minHeap.poll();
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] res = kClosest(new int[][]{{1,3},{-2,2},{-1,-1}}, 1);
        System.out.println();

        int[] num = new int[] {10, 2, 5, 9, 11, -1};
        Arrays.sort(num);
        for(int n: num) {
            System.out.print(n + " ");
        }
        System.out.println();
        String[] str = new String[] {"test", "hello", "hel", "", "abc"};
        Arrays.sort(str);
        for(String s: str) {
            System.out.print(s + " ");
        }

        System.out.println("a".compareTo("b"));
        System.out.println(new Integer(5).compareTo(new Integer(1)));

        String str1 = "String method tutorial";
        String str2 = "compareTo method examp";
        String str3 = "String method tutorial";

        int var1 = str1.compareTo( str2 );
        System.out.println("str1 & str2 comparison: "+var1);
    }
}
