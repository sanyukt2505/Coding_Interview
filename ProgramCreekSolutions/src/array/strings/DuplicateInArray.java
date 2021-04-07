package array.strings;

import java.util.HashSet;

/**
 * Given an array of integers, find if the array contains any duplicates.
 * Your function should return true if any value appears at least twice in the array
 */
public class DuplicateInArray {
  public static void main(String[] args) {
    int[] nums = {11, 22, 33, 44, 11};
    System.out.println("Array contains duplicates?: " + (containsDuplicate(nums) ? "Yes" : "No"));
  }

  public static boolean containsDuplicate(int[] nums) {
    if (nums == null || nums.length < 2)
      return false;
    HashSet<Integer> set = new HashSet<>();
    for (int num : nums) {
      if (!set.add(num)) {
        return true;
      }
    }
    return false;
  }

}
