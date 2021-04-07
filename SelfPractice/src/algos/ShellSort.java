package algos;

import java.util.Arrays;

// W = nlog(n)^2 , Avg = nlog(n)^2, Best = nlog(n)
// Space = O(1)
// ShellSort is mainly a variation of Insertion Sort. In insertion sort, we move elements
// only one position ahead. When an element has to be moved far ahead, many movements are
// involved. The idea of shellSort is to allow exchange of far items.
//
// Step 1 − Initialize the value of gap
// Step 2 − Divide the list into smaller sub-list of equal interval gap
// Step 3 − Sort these sub-lists using insertion sort
// Step 3 − Repeat until complete list is sorted

public class ShellSort{

    public static void main(String []args){
        int[] arr = new int[]{11,13,75,7,9,22,44,6,17,10};
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void shellSort(int[] arr) {
        int n = arr.length;
        for (int gap = n/2; gap > 0; gap = gap/2) {
            // Do a gapped insertion sort for this gap size.
            // [0 & gap-1] [1 & gap] [2 & gap+1]
            for (int i = gap; i < n; i++) {
                int temp = arr[i];

                // shift earlier gap-sorted elements up until
                // the correct location for a[i] is found
                int j;
                for (j = i; j >= gap && arr[j-gap] > temp; j = j-gap) {
                    arr[j] = arr[j-gap];
                }
                // put temp (the original a[i]) in its correct location
                arr[j] = temp;
            }
        }
    }
}
