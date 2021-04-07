package array.strings;

import java.util.Arrays;

/**
 * What if duplicates are allowed at most twice?
 * Given sorted array A = [1,1,1,2,2,3], your function should return length = 5, and A is now [1,1,2,2,3].
 */
public class RemoveThirdDuplicate {
  public static void main(String[] args) {
    int[] arr = {1, 1, 1, 2, 2, 3};
    arr = removeDuplicates(arr);
    System.out.println("Unique Array Length: " + arr.length);
    System.out.println("Unique Array: " + Arrays.toString(arr));
  }

  private static int[] removeDuplicates(int[] A) {
    if (A.length < 2) {
      return A;
    }

    int prev = 1;
    int curr = 2;

    while (curr < A.length) {
      if (A[curr] == A[prev] && A[curr] == A[prev - 1]) {
        curr++;
      } else {
        prev++;
        A[prev] = A[curr];
        curr++;
      }
    }

    return Arrays.copyOf(A, prev + 1);
  }
}
