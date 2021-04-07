package problem.leetcode.hard;

import java.util.Comparator;
import java.util.PriorityQueue;

public class FindMedianFromDataStream_295 {
    /** maxheap for storing 1 - n-1 - lower values
     and minHeap for n and 100  - higher values */
    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;

    /** initialize your data structure here. */
    public FindMedianFromDataStream_295() {
        minHeap = new PriorityQueue<Integer>();
        /** By default Java provides min heap. For max heap, we need to pass in a appropriate comparator */
        maxHeap = new PriorityQueue<Integer>(1, new Comparator<Integer>(){
            public int compare(Integer ob1, Integer ob2){
                return ob2.compareTo(ob1);
            }
        });
    }

    /** We use 2 heap - 1 maxHeap and 1 minHeap
     * and every time we add a number we balance the heap, so that they have equal num of elements  */
    public void addNum(int num) {
        /** put the new number in both the heap, so that it gets to it correct position*/
        maxHeap.add(num);
        minHeap.add(maxHeap.poll());
        if(minHeap.size() > maxHeap.size()){
            maxHeap.add(minHeap.poll());
        }
    }

    public double findMedian() {
        /** If both heap are same in size, it means the total number of elements is EVEN */
        if(minHeap.size() == maxHeap.size())
            return (double) (maxHeap.peek()+ minHeap.peek())*0.5 ;
        else
            return (double) maxHeap.peek();
    }
}
