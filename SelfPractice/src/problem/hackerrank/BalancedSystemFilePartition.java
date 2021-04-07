package problem.hackerrank;

import java.util.List;

/**
 * https://stackoverflow.com/questions/63999102/balanced-system-files-partition-coding-challenge
 * The directory structure of a system disk partition is represented as a tree. Its n directories are numbered from 0 to n-1,
 * where the root directory has the number 0. The structure of the tree is defined by a parent array,
 * where parent[i] = j means that the directory i is a direct subdirectory of j.
 * Since the root directory does not have a parent, it will be represented as a parent[0] = -1.
 * The value in files_size[i] denotes the sum of the sizes in kilobytes of the files located in directory i, but excluding its subdirectories.
 * The size of the content of a directory is defined as the size of all of its subdirectories.
 * Partition the tree into two smaller ones by cutting one branch so that the sizes of the resulting subtrees are as close as possible.
 *
 * parent = [-1,0,0,1,1,2]
 * files_size = [1,2,2,1,1,1]
 *               0/1
 *             /    \
 *           1/2    2/2
 *          /   \     \
 *        3/1   4/1   5/1
 *
 * Cut the branches between directories 1 and 0.
 * The partition {0,2,5} has size files_size[0] + files_size[2] + files_size[5] = 1 + 2 + 1 = 4.
 * The partition {1,3,4} has size files_size[1] + files_size[3] + files_size[4] = 2 + 1 + 1 = 4.
 * The absolute difference between the sizes of the two new trees is 4 - 4 = 0.
 * Since no other partition can have a smaller absolute difference, the final answer is 0.
 */
public class BalancedSystemFilePartition {
    public static int mostBalancedPartition(List<Integer> parent, List<Integer> filesSize) {
        int totalSum = 0;
        int minDiff = Integer.MAX_VALUE;
        int[] sum = new int[filesSize.size()];

        for (Integer fileSize: filesSize) {
            totalSum += fileSize;
        }

        // every sum[i] will have the sum of that node and its sub tree
        // in above case, sum = [8, 4, 3, 1, 1, 1]
        for (int i = filesSize.size() - 1; i >= 0; i--) {
            sum[i] += filesSize.get(i);
            if (parent.get(i) != -1) {
                sum[parent.get(i)] += sum[i];
            }
        }
        // cant divide the tree at root
        // so starting at index 1
        for (int i = 1; i < sum.length; i++) {
            // calculating the value of the other partition
            // if i chose to partion at this index
            int rem = totalSum - sum[i];
            if (minDiff > Math.abs(sum[i] - rem)) {
                // checking the absolute diff in size of two partitions
                minDiff = Math.abs(sum[i] - rem);
            }
        }
        return minDiff;
    }

}
