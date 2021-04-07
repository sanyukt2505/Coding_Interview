package com.ucb.algos.ds.maps.hastables.skiplists;

import java.util.Scanner;

/**
 * Created by Vijay on 3/6/16.
 */
public class WordCount {
    public static void main(String[] args) {
        /*Map<String, Integer> frequencyMap = new ChainHashMap<>();
        // scan input for words, using all non-letters as delimiters
        Scanner doc = new Scanner(System.in).useDelimiter("[^a-zA-Z]");
        while (doc.hasNext()) {
            String word = doc.next().toLowerCase();
            Integer count = frequencyMap.get(word);
            if (count == null)
                count = 0;
            frequencyMap.put(word, 1 + count);
        }
        int maxCount = 0;
        String maxWord = "no word";
        for(Entry<String, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxWord = entry.getKey();
                maxCount = entry.getValue();
            }
        }
        System.out.println("The most frequent word is: '" + maxWord);
        System.out.println("' with " + maxCount + " occurrences.");*/

    }
}
