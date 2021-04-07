package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Testing {

    static class Interval {
        int start;
        int end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public static int widestGap(int n, List<Integer> start, List<Integer> finish) {
        if (start == null || start.size() == 0) {
            return n;
        }

        List<Interval> list = new ArrayList<>();
        int localGap = 0;
        int maxGap = 0;

        for (int i = 0; i < start.size(); i++) {
            list.add(new Interval(start.get(i), finish.get(i)));
        }

        Collections.sort(list, (a,b) -> a.start - b.start);

        LinkedList<Interval> merge = new LinkedList<>();
        merge.add(list.get(0));

        for (int i = 1; i < list.size(); i++) {
            if(list.get(i).start < merge.getLast().end) {
                merge.getLast().end = Math.max(list.get(i).end, merge.getLast().end);
            } else {
                merge.add(list.get(i));
            }
        }

        for (int i = 1; i < merge.size(); i++) {
            if(merge.get(i).start > merge.get(i-1).end ) {
                localGap = merge.get(i).start - merge.get(i-1).end;
            }
            maxGap = Math.max(maxGap, localGap);
        }
        return maxGap;
    }

    public static void main(String[] args) {
        Integer[] t1= new Integer[]{22 ,75,26 ,45 ,72 ,81 ,47 ,29 ,97 ,2 ,75 ,25 ,82 ,84 ,17 ,56 ,32 ,2 ,28 ,37 ,57 ,
                39 ,18 ,11 ,79 ,6 ,40 ,68 ,68 ,16 ,40 ,63 ,93 ,49 ,91,10,55,68,31, 80};

        Integer[] t2= new Integer[]{ 51 ,92 ,59 ,60 ,77 ,95 ,61 ,68 ,98 ,90 ,87 ,39 ,94 ,85 ,67 ,74 ,41 ,65 ,78 ,80 ,
                85 ,93 ,87 ,82 ,83 ,16 ,89 ,81 ,69 ,72, 80, 77 ,99 ,90, 92, 95, 68 ,70 ,75 ,97};
        List<Integer> start = Arrays.asList(t1);

        List<Integer> finish = Arrays.asList(t2);
        System.out.println(widestGap(100, start, finish));
    }
}
