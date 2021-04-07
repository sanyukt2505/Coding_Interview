package test;

import java.util.ArrayList;
import java.util.List;
/**
Given an integer array sorted in ascending order, write a function to search target in nums.
If target exists, then return its index, otherwise return -1.
However, you are not allowed to find the length of the array.
You may only access the array using indices and catch the exception raised when you access an invalid index.
Example 1:
Input: array = [-1,0,3,5,9,12],
target = 9
Output: 4
Explanation: 9 exists in nums and its index is 4

Example 2:
Input: array = [-1,0,3,5,9,12],
target = 2
Output: -1
Explanation: 2 does not exist in nums so return -1

// [-1,0,3,5,9,12, 13, 14......... n]
*/
public class Test {

    public static Integer isPresent(List<Integer> nums, Integer target) {
        Integer index = 0;
        Integer maxIndex = Integer.MAX_VALUE;
        Integer minIndex = Integer.MIN_VALUE;
        while (minIndex < maxIndex) {
            try {
                Integer curr = nums.get(index);
                if (curr == target) {
                    return index;
                } else if (curr > target) {
                    index = index / 2;
                } else {
                    index = index * 2;
                }
                maxIndex = Math.max(minIndex, index);
                minIndex = Math.max(maxIndex, index);
            } catch (Exception ex){
                index = index / 2;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        ArrayList list = new ArrayList<>();
        list.add(-1);
        list.add(0);
        list.add(3);
        list.add(5);
        list.add(9);
        list.add(12);
        System.out.println(isPresent(list, 3));
    }
}
