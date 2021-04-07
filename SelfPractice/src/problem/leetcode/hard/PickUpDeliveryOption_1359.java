package problem.leetcode.hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * https://leetcode.com/problems/count-all-valid-pickup-and-delivery-options/
 * 1 -> P1,D1
 * 2 -> [P1, P2, D1, D2],  [P1, P2, D2, D1],  [P1, D1, P2, D2], [P2, P1, D1, D2], [P2, P1, D2, D1], [P2, D2, P1, D1]
 * // given n, print all the variation of valid sequence
 */
public class PickUpDeliveryOption_1359 {

    /** 1 -> P1,D1
    // 2 -> P1,D1,P2,D2  P1,P2,D1,D2  ...
    // given n, print all the variation of valid sequence  */
    static int length = 0;

    static Integer generateSequence(int num) {
        length = num;

        List<List<String>> resSeq = new ArrayList<>();
        List<String> validPickUps = new ArrayList<>();
        List<String> validDelivery = new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            validPickUps.add("P" + i);
            validDelivery.add("D" + i);
        }
        generateSequenceUtil(resSeq, validPickUps, validDelivery, new ArrayList<>(), new boolean[num],
                new boolean[num]);
        System.out.println(resSeq.size());
        return resSeq.size();
    }

    static void generateSequenceUtil(List<List<String>> seq, List<String> pickUpSet, List<String> deliverSet,
            List<String> currSet,
            boolean[] picked, boolean[] delivered) {
        if (currSet.size() == length * 2) {
            seq.add(new ArrayList<>(currSet));
            System.out.println(currSet);
        }

        for (int i = 0; i < pickUpSet.size(); i++) {
            if (!picked[i]) {
                currSet.add(pickUpSet.get(i));
                picked[i] = true;
                generateSequenceUtil(seq, pickUpSet, deliverSet, currSet, picked, delivered);
                currSet.remove(currSet.size() - 1);
                picked[i] = false;
            }
        }

        for (int i = 0; i < deliverSet.size(); i++) {
            if (picked[i] && !delivered[i]) {
                currSet.add(deliverSet.get(i));
                delivered[i] = true;
                generateSequenceUtil(seq, pickUpSet, deliverSet, currSet, picked, delivered);
                currSet.remove(currSet.size() - 1);
                delivered[i] = false;
            }
        }
    }

    /** validate Pick up delivery seq
     *  P1 P2 D2 D1 = true
     *  D1 P1 P2 D2 = false
     * */
    static boolean validSequence(List<String> sequences) {
        HashSet<Integer> pickSet = new HashSet<>();
        HashSet<Integer> deliveredSet = new HashSet<>();


        for (String seq: sequences) {
            char c = seq.charAt(0);
            Integer id = Integer.parseInt(seq.substring(1));

            if (c == 'P') {
                if(deliveredSet.contains(id) || !pickSet.add(id)) {
                    return false;
                }
            } else {
                if(!pickSet.remove(id)) {
                    return false;
                }
                deliveredSet.add(id);
            }
        }
        return pickSet.size() == 0;
    }

    public static void main(String[] args) {
        // System.out.println(validSequence(Arrays.asList("P1", "D1", "P32", "P33", "D32", "P2", "D2", "D33")));
        // System.out.println(validSequence(Arrays.asList("P1", "D1", "P33", "D32", "P2", "D2")));
        // System.out.println(validSequence(Arrays.asList("D2", "D1")));
        // System.out.println(validSequence(Arrays.asList("P1", "D1", "P1", "D1")));
        // System.out.println(validSequence(Arrays.asList("P1", "D1", "P33", "D32", "P2", "D2")));
        System.out.println(generateSequence(3));
    }
}
