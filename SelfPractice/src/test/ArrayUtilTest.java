package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.stream.Collectors;

public class ArrayUtilTest {

    public static void main(String[] args)
    {
        Random rand = new Random();
        /** if u need to generate number in a range --- num = minimum + rn.nextInt(maxValue - minvalue + 1) */
        rand.nextInt(100);   // random generate from 0-99
        int numBetween5To8 = rand.nextInt(8 - 5 + 1) + 5;

        int [] myArrayInt = {1,3,5,7,9};
        Integer [] myArray = {11,13,15,17,19};

        System.out.println("Original Array:" + Arrays.toString(myArrayInt));
        // Below create a List<int[] > and thats why you cant do reverse or any other List operation on it
        System.out.println("Original Array:" + Arrays.asList(myArrayInt));

        System.out.println("Original Array:" + Arrays.toString(myArray));
        System.out.println("Original Array:" + Arrays.asList(myArray));

        Collections.reverse(Arrays.asList(myArray));
        System.out.println("Reversed Array:" + Arrays.asList(myArray));

        Integer.parseInt("12");
        Integer.valueOf("12");

        char[] charArr = "Hello".toCharArray();
        String newWord = String.valueOf(charArr);

        String main = "aababcaab";
        String sub = "aab";
        System.out.println(Arrays.asList(main.split(sub)).size());

        List intList = Arrays.asList(1,2,3);

        HashMap<Integer, Integer> unsortedMap = new HashMap();
        unsortedMap.put(1,2);
        unsortedMap.put(2,3);
        unsortedMap.put(3,4);
        unsortedMap.put(5,1);
        PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>((a, b)-> a.getValue() - b.getValue());
        heap.addAll(unsortedMap.entrySet());

    }

    public void sortMapByValues() {
        HashMap<Integer, Integer> unsortedMap = new HashMap();
        unsortedMap.put(1,2);
        unsortedMap.put(2,3);
        unsortedMap.put(3,4);
        Map<Integer, Integer> sortedMap =
                unsortedMap.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new));


        List<Map.Entry<Integer, Integer>> toSort = new ArrayList<>(unsortedMap.entrySet());

        toSort.sort(Map.Entry.comparingByValue());
        Map<Integer, Integer> sortedMap2 = new LinkedHashMap();
        for (Map.Entry<Integer, Integer> entry : toSort) {
            sortedMap.putIfAbsent(entry.getKey(), entry.getValue());
        }


        PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>((a, b)-> a.getValue() - b.getValue());
        heap.addAll(unsortedMap.entrySet());
    }
}
