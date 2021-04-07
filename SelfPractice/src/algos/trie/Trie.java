package algos.trie;

/**
 * Trie is an efficient information reTrieval data structure. Using Trie, search complexities can be brought to optimal limit (key length)
 * If we store keys in binary search tree, a well balanced BST will need time proportional to M * log N,
 *      where M is maximum string length and N is number of keys in tree. Using Trie, we can search the key in O(M) time.
 *
 *  This is an example of R-way Trie where R denotes length of character set we are considering (in this case, we are considering alphabet set whose length is 26)
 */
public class Trie {                     // or Dictionary

    public static final int ALPHA_SIZE = 26;

    public static class TrieNode {
        // In case of Dictionary we replace boolean endOfWord with String finalWord;
        public boolean endOfWord;
        public TrieNode[] children = new TrieNode[ALPHA_SIZE];

        public TrieNode() {
            endOfWord = false;
            for (int i =0; i < ALPHA_SIZE; i++)
                children[i] = null;
        }
    }

    public TrieNode root;

    public void insert(String word) {
        TrieNode curr = root;
        int index;

        for (int i = 0; i < word.length(); i++) {
            index = word.charAt(i) - 'a';

            if(curr.children[index] == null)
                curr.children[index] = new TrieNode();

            curr = curr.children[index];
        }
        curr.endOfWord = true;          // mark last node as leaf
    }

    /**
     * https://www.geeksforgeeks.org/insertion-in-a-trie-recursively/?ref=rp
     */
    public void insertRecursive(TrieNode curr, String word, int depth) {
        // until the end of word is reached, keep inserting nodes recursively
        if(depth < word.length()) {
            int index = word.charAt(depth) - 'a';

            if(curr.children[index] == null) {
                curr.children[index] = new TrieNode();
            }
            insertRecursive(curr.children[index], word, depth + 1);
        } else {
            // Make the endOfWord true which represents the end of string
            curr.endOfWord = true;
        }
    }

    public boolean search(String word) {
        TrieNode curr = root;
        int index;

        for (int i = 0; i < word.length(); i++) {
            index = word.charAt(i) - 'a';
            if(curr.children[index] == null)
                return false;

            curr = curr.children[index];
        }
        return (curr != null && curr.endOfWord);
    }

    /**
     * https://www.geeksforgeeks.org/search-in-a-trie-recursively/?ref=rp
     */
    public boolean searchRecursive(TrieNode curr, String word, int depth) {
        // When a string or any character of a string is not found
        if(curr == null)
            return false;

        // Condition of finding string successfully
        // Return true when endOfWord of last node contains true
        if(curr.endOfWord == true && depth == word.length()) {
            return true;
        }

        int index = word.charAt(depth) - 'a';
        // Recursive call and return value of function call stack
        return searchRecursive(curr.children[index], word, depth + 1);
    }

    /**
     * startsWith is same as search, Just that when word ends, you dont check for the endOfWord
     */
    public boolean startsWith(String prefix) {
        TrieNode curr = root;
        int index;

        for (int i = 0; i < prefix.length(); i++) {
            index = prefix.charAt(i) - 'a';
            if(curr.children[index] == null)
                return false;

            curr = curr.children[index];
        }
        return true;
    }

    public void deleteRecursive(String word) {
        deleteRecursive(root, word, 0);
    }

    /**
     * During deleteRecursive operation we deleteRecursive the key in bottom up manner using recursion. The following are possible conditions when deleting key from trie,
     *
     * Key may not be there in trie. Delete operation should not modify trie.
     * Key present as unique key (no part of key contains another key (prefix), nor the key itself is prefix of another key in trie). Delete all the nodes.
     * Key is prefix key of another long key in trie. Unmark the leaf node.
     * Key present in trie, having atleast one other key as prefix key. Delete nodes from end of key until first leaf node of longest prefix key
     */
    public TrieNode deleteRecursive(TrieNode curr, String word, int depth) {

        // If last character of key is being processed
        if(depth == word.length()) {
            // This node is no more end of word after removal of given key
            if(curr.endOfWord) {
                curr.endOfWord = false;
            }

            // If given node is not prefix of any other word
            if(isEmpty(curr)) {
                curr = null;
            }
            return curr;
        }

        // If not last character, recur for the child obtained using ASCII value
        int index = word.charAt(depth) - 'a';
        curr.children[index] = deleteRecursive(curr.children[index], word, depth + 1);

        // If curr does not have any child (its only child got deleted), and it is not end of another word.
        if(isEmpty(curr) && !curr.endOfWord) {
            curr = null;
        }
        return curr;
    }

    private boolean isEmpty(TrieNode node) {
        for(int i = 0; i < ALPHA_SIZE; i++) {
            if(node.children[i] != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Auto complete feature using Trie
     * https://www.geeksforgeeks.org/auto-complete-feature-using-trie/
     *
     * For example if the Trie store {“abc”, “abcd”, “aa”, “abbbaba”} and the User types in “ab” then he must be shown {“abc”, “abcd”, “abbbaba”}
     *      1. Search for given query using standard Trie search algorithm.
     *      2. If query prefix itself is not present, return -1 to indicate the same.
     *      3. If query is present and is end of word in Trie, print query. This can quickly checked by seeing if last
     *          matching node has isEndWord flag set. We use this flag in Trie to mark end of word nodes for purpose of searching.
     *      4. If last matching node of query has no children, return.
     *      5. Else recursively print all nodes under subtree of last matching node.
     */
    public void suggestAutoComplete(TrieNode root, String query) {
        System.out.println("Auto Complete Suggestions for " + query);
        TrieNode curr = root;
        // Check if prefix is present and find the node (of last level) with last character of given string.
        for(int depth = 0; depth < query.length(); depth++) {
            int index = query.charAt(depth) - 'a';

            if(curr.children[index] == null) {
                return;
            }
            curr = curr.children[index];
        }

        // If prefix is present as a word
        if(curr.endOfWord && isEmpty(curr)) {
            System.out.println("No strings with this prefix");
            return;
        } else {
            suggestRecursive(curr, query);
        }
    }

    public void suggestRecursive(TrieNode root, String prefix) {

        // found a string in Trie with the given prefix
        if(root.endOfWord) {
            System.out.println(prefix);
        }
        if(isEmpty(root)) {
            return;
        }

        for(int i = 0; i < ALPHA_SIZE; i++) {
            if(root.children[i] != null) {

                suggestRecursive(root.children[i], prefix + (char) (i + 97));
            }
        }
    }

    public static void main(String args[])
    {
        // Input keys (use only 'a' through 'z' and lower case)
        String keys[] = {"hello", "hey", "heyt", "there", "me", "heat"};

        Trie trie = new Trie();
        trie.root = new TrieNode();

        // Construct trie
        int i;
        for (i = 0; i < keys.length ; i++)
            trie.insert(keys[i]);

        trie.insertRecursive(trie.root, "test", 0);
        System.out.println("is present ? " + "test: " + trie.search("test"));
        System.out.println("is present ? " + "ttest: " + trie.search("ttest"));

        trie.suggestAutoComplete(trie.root, "he");
        trie.suggestAutoComplete(trie.root, "heo");

        System.out.println("is recursive present ? " + "hello: " + trie.searchRecursive(trie.root, "hello", 0));

        // Search for different keys
        System.out.println("is present ? " + "hello: " + trie.search("hello"));
        System.out.println("is present ? " + "these: " + trie.search("these"));
        System.out.println("is present ? " + "their: " + trie.search("their"));
        System.out.println("is present ? " + "there: " + trie.search("there"));

        System.out.println("is prefix ? " + "hel: " + trie.startsWith("hel"));
        System.out.println("is prefix ? " + "helo: " + trie.startsWith("helo"));

        trie.deleteRecursive("hello");
        System.out.println("is present ? " + "hello: " + trie.search("hello"));
        trie.deleteRecursive("hey");
        System.out.println("is present ? " + "hey: " + trie.search("hey"));
        System.out.println("is present ? " + "heyt: " + trie.search("heyt"));

    }

}
