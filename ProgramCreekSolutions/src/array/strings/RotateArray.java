package array.strings;

/**
 * Created by Vijay on 3/10/16.
 */
public class RotateArray {
    public static void main(String[] args) {
        int[] arrOne = {0, 1, 2, 3, 4, 5, 6},
                arrTwo = {0, 1, 2, 3, 4, 5, 6};

        System.out.println("Original Array: ");
        for (Integer number : arrOne) {
            System.out.print(number + "\t");
        }

        System.out.println("");

        System.out.println("Rotating arrOne by +3: ");
        rotate(arrOne, 3);
        for (Integer number : arrOne) {
            System.out.print(number + "\t");
        }

        System.out.println("");

        System.out.println("Rotating arrTwo by -3: ");
        rotate(arrTwo, -3);
        for (Integer number : arrTwo) {
            System.out.print(number + "\t");
        }
    }

    /**
     * Assuming that we are given array {1, 2, 3, 4, 5, 6} and order is 2
     *   Rotate the first part: {4, 3, 2, 1, 5, 6}
     *   Rotate the second part: {4, 3, 2, 1, 6, 5}
     *   Rotate whole array: {5, 6, 1, 2, 3, 4}
     * @param arr
     * @param order
     */
    private static void rotate(int[] arr, int order) {
        if(order < 0){
            if (Math.abs(order) > arr.length)
                order = -(Math.abs(order) % arr.length);
            order = (arr.length + order ) % arr.length; //make it same as right shift
        } else {
            // This will take care of situation in which order is greater than the length of array
            order = order % arr.length;
        }

        if (arr == null || order < 0) {
            throw new IllegalArgumentException("Illegal values for arr or order");
        }

        // Calculate the first part of the array
        int a = arr.length - order;

        // reverse the first part
        reverse(arr, 0, a - 1);
        reverse(arr, a, arr.length - 1);
        reverse(arr, 0, arr.length - 1);

    }

    public static void reverse(int[] arr, int left, int right) {
        if (arr == null || arr.length == 1)
            return;

        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}
