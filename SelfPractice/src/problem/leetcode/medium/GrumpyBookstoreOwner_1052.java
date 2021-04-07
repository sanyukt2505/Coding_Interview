package problem.leetcode.medium;

/**
 * Today, the bookstore owner has a store open for customers.length minutes.  Every minute, some number of customers
 * (customers[i]) enter the store, and all those customers leave after the end of that minute.
 * On some minutes, the bookstore owner is grumpy.  If the bookstore owner is grumpy on the i-th minute, grumpy[i] = 1,
 * otherwise grumpy[i] = 0.  When the bookstore owner is grumpy, the customers of that minute are not satisfied, otherwise they are satisfied.
 *
 * The bookstore owner knows a secret technique to keep themselves not grumpy for X minutes straight, but can only use it once.
 * Return the maximum number of customers that can be satisfied throughout the day.
 * Input: customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
 * Output: 16
 * Explanation: The bookstore owner keeps themselves not grumpy for the last 3 minutes.
 * The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.
 */
public class GrumpyBookstoreOwner_1052 {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int satisfiedCustomers = 0;

        // find the num of satisfies customers without using the technique, satisfiedCustomers = 10
        for (int i = 0; i < customers.length; i++) {
            if (grumpy[i] == 0) {
                satisfiedCustomers += customers[i];
            }
        }

        int currSum = 0;
        int max = 0;
        int start = 0;

        // find sliding window where num of unsatisfied customers is highest
        for (int end = 0; end < customers.length; end++) {
            if (grumpy[end] == 1) {
                currSum += customers[end];
            }

            if (end - start + 1 == X){
                max = Math.max(currSum, max);
                // X length window is moving, remove cust[start] from currSum
                if (grumpy[start] == 1) {
                    currSum -= customers[start];
                }
                start++;
            }
        }
        return satisfiedCustomers + max;
    }
}
