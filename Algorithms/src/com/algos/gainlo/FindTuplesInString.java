package com.algos.gainlo;

/**
 * Created by Vijay on 2/2/16.
 */
public class FindTuplesInString {
    public static int count(String str) {
        int tupleCount = 0, countOfAs = 0;
        char[] charArray = str.toCharArray();

        // count As
        for (int i = 0; i < charArray.length; i++) {
            if(str.charAt(i) == 'a') countOfAs++;
        }

        for (int j = charArray.length - 1; j >= 0; j--) {
            switch (charArray[j]) {
                case 'a': countOfAs--; break;
                case 'b': tupleCount = tupleCount + countOfAs; break;
                case 'c': tupleCount = tupleCount - countOfAs; break;
                default : break;
            }
        }

        return tupleCount;
    }

    public static void main(String[] args) {
        System.out.println("Tuples in abab: "+count("abab"));
    }
}
