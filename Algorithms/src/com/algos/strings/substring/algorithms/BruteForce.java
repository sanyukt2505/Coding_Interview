package com.algos.strings.substring.algorithms;

/**
 * Java's indexOf method uses this brute force algorithm
 * This algorithm is slow if there is lot of repetition in text and in the pattern
 */
public class BruteForce {

    public static int search(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();

        // Reduce the txt exploration to not use last M characters (which is pattern length)
        // If the pattern doesn't start at N-M index then there is no chance of finding it
        for (int i = 0; i <= N - M; i++) {
            int j;
            for (j = 0; j < M; j++) {
                if (txt.charAt(i+j) != pat.charAt(j)) {
                    break;
                }
            }
            // if pattern length has reached, returned the value of index where the pattern was found
            if (j == M) return i;
        }

        // If pattern is not found return the length of the text
        return N;
    }

    /**
     * Issue with the search method above:  You have to maintain buffer of M characters
     *  When the txt is huge string (1 billion characters) and pattern is even 100 characters, every time a character mismatch occurs, you have to take help of backup
     *  This causes inefficient implementation.
     *  In the following implementation:
     *      i points to end of sequence of already-matched chars in text
     *      j stores # of already-matched chars (end of sequence in pattern)
     * @param pat
     * @param txt
     * @return
     */
    public static int searchWithBackUp(String pat, String txt) {
        int i, M = pat.length();
        int j, N = txt.length();

        for (i = 0, j = 0; i < N && j < M; i++) {
            if (txt.charAt(i) == pat.charAt(j)) j++;
            else {
                i -= j;
                j = 0;
            }
        }

        if (j == M) return i - M;
        else return N;
    }

    public static void main(String[] args) {
        int patternFoundAt = search("ABAB", "ABACABABRAC");
        System.out.println("Pattern ABRA found at: " + patternFoundAt);

        // efficient implementation
        patternFoundAt = searchWithBackUp("ABRAC", "ABACABCABRAC");
        System.out.println("Pattern ABRAC found at: " + patternFoundAt);
    }
}
