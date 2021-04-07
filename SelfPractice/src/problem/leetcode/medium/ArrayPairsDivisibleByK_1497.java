package problem.leetcode.medium;

/**
 * https://leetcode.com/problems/check-if-array-pairs-are-divisible-by-k/
 * Given an array of integers arr of even length n and an integer k.
 * We want to divide the array into exactly n / 2 pairs such that the sum of each pair is divisible by k.
 * Return True If you can find a way to do that or False otherwise.
 * Input: arr = [1,2,3,4,5,10,6,7,8,9], k = 5
 * Output: true
 * Explanation: Pairs are (1,9),(2,8),(3,7),(4,6) and (5,10)
 */
public class ArrayPairsDivisibleByK_1497 {
    public boolean canArrange(int[] arr, int k) {

        // create array of count of modulus
        int[] mod = new int[k];
        for(int i = 0; i < arr.length; i++){
            int rem = arr[i] % k;
            // -1 and 6 can be a pair
            // if you get a -ve mod, use the ABS of modulus and decrement it
            if(arr[i] < 0){
                mod[Math.abs(rem)]--;
            }else
                mod[rem]++;
        }
        // count of numbers that are divisible by k
        if(mod[0]%2 != 0)
            return false;
        // here we are match mod[1] to mod[4], as 1+4 = 5
        for(int i = 1; i< mod.length-i; i++){
            if(mod[i] != mod[mod.length-i])
                return false;
        }
        return true;
    }
}
