package problem.leetcode.medium;

/**
 * https://leetcode.com/problems/longest-increasing-subsequence/
 * Find a subsequence in given array in which the subsequence's elements are in sorted order, lowest to highest, and
 * in which the subsequence is as long as possible
 * Input: [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 *
 *  Solution :
 *  https://www.youtube.com/watch?v=CE2b_-XfVDk
 *  Dynamic Programming is used to solve this question. DP equation is
 *  if(arr[i] > arr[j]) { T[i] = max(T[i], T[j] + 1 }
 *
 *  Time complexity is O(n^2).
 *  Space complexity is O(n)
 *  Reference
 *  * http://en.wikipedia.org/wiki/Longest_increasing_subsequence
 *  * http://www.geeksforgeeks.org/dynamic-programming-set-3-longest-increasing-subsequence/
 */
public class LongestIncreasingSubsequence_300 {

    /**
     * DP way of solving LIS
     */
    public int longestSubsequenceWithActualSolution(int arr[]) {
        // temp array to keep track of number of smaller numbers encountered so far
        int T[] = new int[arr.length];
        int actualSolution[] = new int[arr.length];
        for(int i=0; i < arr.length; i++){
            T[i] = 1;
            actualSolution[i] = i;
        }

        for(int i=1; i < arr.length; i++){
            for(int j=0; j < i; j++){
                // if the number on Right > num on Left, then increment 1 to the count
                // but also check temp[Right], there can be a greater number already there, due to some other sequence
                if(arr[i] > arr[j]){
                    if(T[j] + 1 > T[i]){
                        T[i] = T[j] + 1;
                        //set the actualSolution to point to guy before me
                        actualSolution[i] = j;
                    }
                }
            }
        }

        //find the index of max number in T
        int maxIndex = 0;
        for(int i=0; i < T.length; i++){
            if(T[i] > T[maxIndex]){
                maxIndex = i;
            }
        }

        //lets print the actual solution
        int t = maxIndex;
        int newT = maxIndex;
        do{
            t = newT;
            System.out.print(arr[t] + " ");
            newT = actualSolution[t];
        }while(t != newT);
        System.out.println();

        return T[maxIndex];
    }



    public static void main(String args[]){
        LongestIncreasingSubsequence_300 lis = new LongestIncreasingSubsequence_300();
        int arr[] = {23,10,22,5,33,8,9,21,50,41,60,80,99, 22,23,24,25,26,27};
        int result = lis.longestSubsequenceWithActualSolution(arr);
        System.out.println(result);
    }
}