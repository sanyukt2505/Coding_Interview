package com.ucb.algos.ds.recursion;

import java.io.File;

/**
 * Created by Vijay on 2/15/16.
 */
public class FileSystemDiskUsageRecursive {
    public static void main(String[] args) {
        File algoDsDir = new File("/Users/Vijay/Documents/Coding_Interview/UCB_Algorithms/src/com/ucb/algos/ds");
        long totalSize = diskUsage(algoDsDir);
        System.out.println("Total size of Algos DS Dir: " + totalSize);
    }

    public static long diskUsage(File root) {
        long total = root.length();

        if (root.isDirectory()) {
            for (String childName : root.list()) {
                File child = new File(root, childName);
                total += diskUsage(child);
            }
        }

        System.out.println(total + "\t" + root);
        return total;
    }
}
