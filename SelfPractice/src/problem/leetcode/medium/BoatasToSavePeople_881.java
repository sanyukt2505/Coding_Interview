package problem.leetcode.medium;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/boats-to-save-people/
 *
 * The i-th person has weight people[i], and each boat can carry a maximum weight of limit.
 * Each boat carries at most 2 people at the same time, provided the sum of the weight of those people is at most limit.
 *
 * Return the minimum number of boats to carry every given person.  (It is guaranteed each person can be carried by a boat.)
 * Input: people = [1,2], limit = 3      Output: 1
 * Explanation: 1 boat (1, 2)
 * Time: O(nlogn)       Space: O(1)
 */
public class BoatasToSavePeople_881 {
    /**
     * As one boat can carry 2 people at a time, try to allocate 2 to the same boat
     * for this pick the heaviest and the lightest guy
     *    - if the total weight > limit, then we are sure that only the heavy(high--) guy can go in that boat
     */
    public static int numRescueBoats(int[] people, int limit) {
        int boats = 0;
        Arrays.sort(people);
        int low = 0;
        int high = people.length - 1;

        while (low < high) {
            if (people[low] + people[high] <= limit) {
                low++;
                high--;
            } else {
                high--;
            }
            boats++;
        }

        return boats;
    }

    public static void main(String[] args) {
        System.out.println(numRescueBoats(new int[]{1,2,3,4}, 3));
    }
}
