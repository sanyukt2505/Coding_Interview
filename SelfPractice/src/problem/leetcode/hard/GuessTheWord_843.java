package problem.leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * https://leetcode.com/problems/guess-the-word/
 * Guess word or guess the secret
 */
public class GuessTheWord_843 {
    public void findSecretWord(String[] wordlist, Master master) {
        Random r = new Random();
        int N = 10;
        int len = 6;

        List<String> list = Arrays.asList(wordlist);
        List<String> tmp;
        while(N > 0) {
            /** get a random string from the word list */
            int i = r.nextInt(list.size());
            String guessWord = list.get(i);
            int numMatch = master.guess(guessWord);
            /** if num of character match == 6, so return */
            if (numMatch == len) return;

            /** modify the list to keep only those words that matches the previous return criteria */
            tmp = new ArrayList<>();
            for (int k = 0; k < list.size(); k++) {
                String s = list.get(k);
                if (k != i && nbMatch(guessWord, s) == numMatch) {
                    tmp.add(s);
                }
            }
            list = tmp;
            N = tmp.size();
        }
    }

    private int nbMatch(String s1, String s2) {
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i)) count++;
        }
        return count;
    }

    interface Master {
        public int guess(String word) ;
    }
}
