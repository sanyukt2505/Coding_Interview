package com.leetcode.arrays.strings;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a sorted integer array where the range of elements are [0,99] inclusive, return its missing ranges
 * e.g. given [0, 1, 3, 50, 75], return ["2", "4->49", "51->74", "76->99"]
 * Questions that candidates may ask:
 * Q1: What if given array is empty? A: Then you should return ["0->99"] as those ranges are missing
 * Q2: What if the given array contains all elements from the ranges? A: Returns an empty list, which means no range is missing
 */
public class MissingRanges {
  public static void main(String[] args) {
    int[] vals = {0, 1, 3, 50, 75};
    List<String> missingRanges = findMissingRanges(vals, 0, 99);
    missingRanges.stream().forEach(missingRange -> System.out.print(missingRange + "\t"));
  }

  /**
   * Compare the gap between two neighbor elements and output its range.
   * Edge cases: the first and last elements which does not have previous and next element respectively
   * Solution: Add two artificial elements, -1 before the first element and 100 after the last element
   * @param vals array containing values
   * @param start
   * @param end
   * @return List of Strings that represent missing ranges between the array element values
   */
  private static List<String> findMissingRanges(int[] vals, int start, int end) {
    List<String> ranges = new ArrayList<>();

    // prev is an artificial element added as an element before first element
    int prev = start - 1;

    for (int i = 0; i < vals.length; i++) {
      int curr = (i == vals.length) ? end + 1 : vals[i];

      // If difference between neighboring elements is equal to or more than 2, we have found a missing range
      if (curr - prev >= 2) {
        ranges.add(getRange(prev + 1, curr - 1));
      }

      prev = curr;
    }

    return ranges;
  }

  private static String getRange(int from, int to) {
    return (from == to) ? String.valueOf(from) : from + "->" + to;
  }
}
