package test;

import java.io.File;

import java.io.FileNotFoundException;

import java.util.LinkedList;

import java.util.List;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exercise {

    /**
     * Calculates the average of all provided scores by trying to speed up the addition step.
     *
     * Focus on correctness rather than how fast this will actually perform.
     */

    public int parallelSumAverage(List<File> scoreSources) throws InterruptedException {

        ExecutorService producerExecutor = Executors.newFixedThreadPool(2);
        // use Pair  <Sum, Count>
        Queue<Integer> subSums = new LinkedList<>();

        for (File source : scoreSources) {

            producerExecutor.submit(() -> {
                // local sum might get out range. Long should be used
                int sum = 0;

                // should we read scanner
                Scanner scanner = null;
                try {
                    scanner = new Scanner(source);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                scanner.useDelimiter("\n");

                while (scanner.hasNext()) {
                    // use count++
                    sum += Integer.parseInt(scanner.next());
                }
                // subSums.add(new Pair (sum, count);
                subSums.add(sum);
            });
        }

        int totalSum = 0;
        // initialize to zero and never updated, totalScoreCount= scoreSources.size()
        int totalScoreCount = 0;

        while (!subSums.isEmpty()) {
            int subSum = subSums.poll();
            totalSum += subSum;
        }
        return totalSum / totalScoreCount;
    }
}
