package com.leetcode.binary.search;

/**
 *  You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.

    Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.

    You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.
 */
public class FirstBadVersion {
    public static void main(String[] args) {
        int firstBadVersion = firstBadVersion(10);
        System.out.println("The first bad version is: " + firstBadVersion);
    }

    /**
     * The hint was: You should minimize the number of calls to the API. It implies avoiding Linear Search and going for Binary Search
     * @param n Total versions of the product
     * @return first version that went bad
     */
    private static int firstBadVersion(int n) {
        // Notice: the first version is 1, so the value of lo is initialized to 1
        int lo = 1, hi = n;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (!isBadVersion(mid)) lo = mid + 1;
            else hi = mid;
        }

        return lo;
    }

    /**
     * Mock API which returns if the version is Bad
     * @param version
     * @return if input version is bad
     */
    private static boolean isBadVersion(int version) {
        if (version >= 3) {
            return true;
        }
        return false;
    }
}
