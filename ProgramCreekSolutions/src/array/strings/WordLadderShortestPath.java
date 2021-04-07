package array.strings;

import java.util.*;

/**
 * Created by Vijay on 3/13/16.
 */
public class WordLadderShortestPath {
    public static void main(String[] args) {
        Set<String> dictionary = new HashSet<>();
        dictionary.add("CAT");
        dictionary.add("BAT");
        dictionary.add("COT");
        dictionary.add("COG");
        dictionary.add("COW");
        dictionary.add("RAT");
        dictionary.add("BUT");
        dictionary.add("CUT");
        dictionary.add("DOG");
        dictionary.add("WEB");
        dictionary.add("DON");

        String startWord = "CAT";
        String endWord = "DON";

        Ladder result = getShortestTransformationIterative(startWord, endWord, dictionary);

        if (result != null) {
            System.out.println("Shortest path length is: " + result.getLength() + " and path is: " + result.getPath());
        } else {
            System.out.println("No Path Found");
        }

    }

    private static Ladder getShortestTransformationIterative(String startWord, String endWord, Set<String> dictionary) {
        // Verify that the startWord and endWord are valid words in dictionary
        if (dictionary.contains(startWord) && dictionary.contains(endWord)) {
            List<String> path = new LinkedList<>();
            path.add(startWord);

            // Define the queue to store all the intermediate paths
            Queue<Ladder> queue = new LinkedList<>();
            queue.add(new Ladder(path, 1 , startWord));

            // We have considered the startWord in the intermediate path, so remove it from dictionary
            dictionary.remove(startWord);

            // Iterate till queue is not empty or endWord is found in Path
            while (!queue.isEmpty() && !queue.peek().equals(endWord)) {
                Ladder ladder = queue.remove();

                if (endWord.equals(ladder.getLastWord())) {
                    return ladder;
                }

                Iterator<String> iterator = dictionary.iterator();
                while (iterator.hasNext()) {
                    String currentWord = iterator.next();

                    if (differByOne(currentWord, ladder.getLastWord())) {
                        List<String> list = new LinkedList<>(ladder.getPath());
                        list.add(currentWord);

                        // If the words differ by one then dump it in Queue for later processing
                        queue.add(new Ladder(list, ladder.getLength() + 1, currentWord));

                        // Since currentWord is picked up in the path, we don't need it again
                        iterator.remove();
                    }
                }
            }

            // Check is done to see, on what condition does above loop break
            // if it breaks because Queue is empty, then we didn't get any path till endWord
            // if it breaks because of endWord matched, then we got the Path and return the path from head of the queue
            if (!queue.isEmpty()) {
                return queue.peek();
            }
        }
        return null;
    }

    private static boolean differByOne(String word1, String word2){
        if (word1.length() != word2.length()) {
            return false;
        }

        int diffCount = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                diffCount++;
            }
        }
        return (diffCount == 1);
    }

    static class Ladder {
        private List<String> path;
        private String lastWord;
        private int length;

        public Ladder(List<String> path, int length, String lastWord) {
            this.path = path;
            this.lastWord = lastWord;
            this.length = length;
        }

        public Ladder(List<String> path) {
            this.path = path;
        }

        public List<String> getPath() {
            return path;
        }

        public void setPath(List<String> path) {
            this.path = path;
        }

        public String getLastWord() {
            return lastWord;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }
    }
}
