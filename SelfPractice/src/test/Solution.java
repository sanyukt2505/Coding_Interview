package test;

import java.util.Arrays;

public class Solution {

    public static int findMaxLength(int[] nums) {
        int maxlen = 0;
        for (int start = 0; start < nums.length; start++) {
            int zeroes = 0, ones = 0;
            for (int end = start; end < nums.length; end++) {
                if (nums[end] == 0) {
                    zeroes++;
                } else {
                    ones++;
                }
                if (zeroes == ones) {
                    maxlen = Math.max(maxlen, end - start + 1);
                }
            }
        }
        return maxlen;
    }

    public static void main(String[] args) {
        System.out.println(findMaxLength(new int[]{0,1,1,1,1,0,0,1,1,1,1}));

        Integer[] arr = {5, 10, 2, 9 ,1};
        Arrays.sort(arr, (a,b) -> a - b);
        System.out.println();

        int test = 3;
        System.out.println(test & 1);

    }
}
