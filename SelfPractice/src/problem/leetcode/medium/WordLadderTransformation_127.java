package problem.leetcode.medium;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/word-ladder/
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest
 * transformation sequence from beginWord to endWord, such that:
 *      - Only one letter can be changed at a time.
 *      - Each transformed word must exist in the word list.
 * Input:
 * beginWord = "hit",   endWord = "cog",    wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: 5
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog", return its length 5.
 *
 * Solution:
 * https://www.youtube.com/watch?v=M9cVl4d0v04
 *  - Do a BFS on the words - by changing one character at a time
 *  - If you find the new Word in the list, increase the level and do the same for the new Word
 */
public class WordLadderTransformation_127 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> words = new HashSet();
        words.addAll(wordList);

        if (!words.contains(endWord))
            return 0;

        int level = 1;
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        while(!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String currWord = queue.poll();
                char[] charWord = currWord.toCharArray();

                for (int j = 0; j < charWord.length; j++) {
                    char origChar = charWord[j];

                    for (char c = 'a'; c < 'z'; c++) {
                        if (origChar == c)
                            continue;
                        charWord[j] = c;
                        String newWord = String.valueOf(charWord);
                        if (newWord.equals(endWord))
                            return level + 1;

                        if (words.contains(newWord)) {
                            queue.offer(newWord);
                            words.remove(newWord);
                        }
                    }
                    charWord[j] = origChar;
                }
            }
            level++;
        }
        return 0;
    }
}
