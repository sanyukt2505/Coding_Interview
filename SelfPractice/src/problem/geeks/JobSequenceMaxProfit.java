package problem.geeks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * https://www.geeksforgeeks.org/job-sequencing-problem/
 * Given an array of jobs where every job has a deadline and associated profit if the job is finished before the deadline.
 * It is also given that every job takes single unit of time, so the minimum possible deadline for any job is 1.
 * How to maximize total profit if only one job can be scheduled at a time.
 * Input:  Five Jobs with following deadlines and profits
 * JobID   Deadline  Profit
 *   a       2        100
 *   b       1        19
 *   c       2        27
 *   d       1        25
 *   e       3        15
 * Output: c, a, e
 *
 * Sol:
 * 1) Sort all jobs in decreasing order of profit.
 * 2) Iterate on jobs in decreasing order of profit.For each job , do the following :
 *      a)Find a time slot i, such that slot is empty and i < deadline and i is greatest.Put the job in this slot and
 *          mark this slot filled.
 *      b)If no such i exists, then ignore the job.
 */

public class JobSequenceMaxProfit {
    private static class Job {
        // Each job has a unique-id, profit and deadline
        char id;
        int deadline, profit;

        public Job(char id, int deadline, int profit)
        {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }

    char[] printJobScheduling(ArrayList<Job> arr, int t)
    {
        int n = arr.size();
        // Sort all jobs according to decreasing order of profit
        Collections.sort(arr, (a, b) -> b.profit - a.profit);

        // To keep track of free time slots
        boolean result[] = new boolean[t];
        // To store result (Sequence of jobs)
        char job[] = new char[t];

        // Iterate through all given jobs
        for(int i = 0; i < n; i++)
        {
            // Find a free slot for this job (Note that we start from the last possible slot)
            for(int j = Math.min(t - 1, arr.get(i).deadline - 1); j >= 0; j--)
            {
                // Free slot found
                if (result[j] == false)
                {
                    result[j] = true;
                    job[j] = arr.get(i).id;
                    break;
                }
            }
        }
        return job;
    }

    public static void main(String args[])
    {
        ArrayList<Job> arr = new ArrayList<>();
        arr.add(new Job('a', 2, 100));
        arr.add(new Job('b', 1, 19));
        arr.add(new Job('c', 2, 27));
        arr.add(new Job('d', 1, 25));
        arr.add(new Job('e', 3, 15));

        JobSequenceMaxProfit maxProfit = new JobSequenceMaxProfit();
        char[] jobs = maxProfit.printJobScheduling(arr, 3);

        for(char jb: jobs)
            System.out.print(jb + " ");

        System.out.println();


        Integer [] myArray = {1,3,5,6,7,9,10};
        System.out.println("Original Array:" + Arrays.asList(myArray));

        Collections.reverse(Arrays.asList(myArray));
        System.out.println("Reversed Array:" + Arrays.asList(myArray));
    }
}


