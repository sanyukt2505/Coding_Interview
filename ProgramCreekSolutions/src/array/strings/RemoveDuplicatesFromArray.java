package array.strings;

import java.util.Arrays;

public class RemoveDuplicatesFromArray {
  public static void main(String[] args) {
    int[] arr = {1, 2, 2, 3, 3};
    arr = removeDuplicates(arr);
    System.out.println("Unique Array Length: " + arr.length);
    System.out.println("Unique Array: " + Arrays.toString(arr));
  }

  private static int[] removeDuplicates(int[] A) {
    if (A.length < 2) {
      return A;
    }

    int j = 0;
    int i = 1;

    while (i < A.length) {
      if (A[i] == A[j]) {
        i++;
      } else {
        j++;
        A[j] = A[i];
        i++;
      }
    }

    int[] B = Arrays.copyOf(A, j + 1);

    return B;
  }


}
