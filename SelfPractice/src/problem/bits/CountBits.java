package problem.bits;

/**
 * https://leetcode.com/problems/counting-bits/
 * Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num
 * calculate the number of 1's in their binary representation and return them as an array.
 * Input: 5
 * Output: [0,1,1,2,1,2]
 */
public class CountBits {

    public int[] countBits1(int num) {
        int[] ans = new int[num + 1];
        for (int i = 0; i <= num; ++i)
            ans[i] = popcount1(i);
        return ans;
    }

    /**
     * The key idea here is to realize that for any number nn,
     * doing a bit-wise AND of n and n - 1 flips the least-significant 11-bit in n to 0.
     * 110100  = n
     * 110011  = n-1
     * 110000  = n & n-1
     */
    private int popcount1(int x) {
        int count;
        for (count = 0; x != 0; ++count)
            x &= x - 1; //zeroing out the least significant nonzero bit
        return count;
    }
    //---------------------------------------------------------------------------------------------------

    /**
     * DP + Last Set Bit
     * countBits (x) = countBits(x & (x-1)) + 1      -- explanation as above
     */
    public int[] countBits(int num) {
        int[] ans = new int[num + 1];
        for (int i = 1; i <= num; ++i)
            ans[i] = ans[i & (i - 1)] + 1;
        return ans;
    }

}
