package problem.other;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class TransactionalMap {

    private Map<String, Stack<Integer>> itemsMap = new HashMap<>();
    private Map<Integer, Stack<String>> transactionMap = new HashMap<>();
    private Map<String, Stack<Integer>> deletdItemsMap = new HashMap<>();
    private Map<Integer, Stack<String>> deletdTransactionMap = new HashMap<>();
    private Set<Integer> abortedIndexes = new HashSet<>();

    private Integer currentPt = 0;
    private Integer latestPt = 0;

    public Integer get(String str) {
        if(!itemsMap.containsKey(str))
            return Integer.MIN_VALUE; // here min value denotes we could'nt find value- throw an error instead!
        Stack<Integer> st = itemsMap.get(str);
        if (st.isEmpty())
            return Integer.MIN_VALUE; // throw an error instead!
        return st.peek();
    }

    public void set(String str, Integer value) {
        if (currentPt > 0) {
            Stack<String> transactionStack = transactionMap.getOrDefault(currentPt, new Stack<String>());
            transactionStack.push(str);
            transactionMap.put(currentPt, transactionStack);
        }
        Stack<Integer> st = itemsMap.getOrDefault(str, new Stack<Integer>());
        st.push(value);
        itemsMap.put(str, st);
    }

    public void unset(String str) {
        if (currentPt > 0) {
            Stack<Integer> itemStack = itemsMap.getOrDefault(str, new Stack<Integer>());
            Stack<String> deletedStack = deletdTransactionMap.getOrDefault(currentPt, new Stack<String>());
            deletedStack.push(str);
            deletdTransactionMap.put(currentPt, deletedStack);
            deletdItemsMap.put(str, itemStack);
        }
        itemsMap.remove(str);
    }

    /**

     */
    public void begin() {
        while(abortedIndexes.contains(currentPt++));
        if (currentPt > latestPt) latestPt = currentPt;
    }

    public void abort() {
        if (currentPt < 0) throw new RuntimeException("All transactions are already performed!");
        while (latestPt >= currentPt) {
            if(!abortedIndexes.contains(currentPt)) {
                abortedIndexes.add(latestPt);
                // setting the unset-ed values
                if (deletdTransactionMap.containsKey(latestPt) && !deletdTransactionMap.get(latestPt).isEmpty()) {
                    Stack<String> dSt = deletdTransactionMap.get(latestPt);
                    while(!dSt.isEmpty()) {
                        String item = dSt.pop();
                        Stack<Integer> newitemStack = deletdItemsMap.getOrDefault(item, new Stack<Integer>());
                        Stack<Integer> exisitingItemStack = itemsMap.getOrDefault(item, new Stack<Integer>());
                        Stack<Integer> temp = new Stack<>();
                        while(!exisitingItemStack.isEmpty()) {
                            temp.push(exisitingItemStack.pop());
                        }
                        while(!temp.isEmpty()) {
                            newitemStack.push(temp.pop());
                        }
                        itemsMap.put(item, newitemStack);
                    }
                }
                // setting the set values
                Stack<String> tSt = transactionMap.getOrDefault(latestPt, new Stack<String>());
                while(!tSt.isEmpty()) {
                    String item = tSt.pop();
                    Stack<Integer> st = itemsMap.getOrDefault(item, new Stack<Integer>());
                    if(!st.isEmpty()) st.pop();
                    itemsMap.put(item, st);
                }
            }
            latestPt--;
        }
        currentPt--;

    }

    public void commit() {
        if (currentPt < 0)
            throw new RuntimeException("All transactions are already performed!");
        while(abortedIndexes.contains(currentPt--));
    }

    public static void main(String[] args) {
        TransactionalMap s = new TransactionalMap();
        s.begin();
        s.set("e",50);
        System.out.println(s.get("e")); // 50

        s.begin();
        s.set("f",60);
        s.set("e",55);
        System.out.println(s.get("f")); // 60
        System.out.println(s.get("e")); // 55

        s.begin();
        s.set("g",70);
        s.unset("e");
        System.out.println(s.get("g")); // 70
        System.out.println(s.get("e")); // -2147483648
        s.abort();

        System.out.println(s.get("g")); // -2147483648
        s.set("e",58);
        s.unset("e");
        System.out.println(s.get("e")); // -2147483648
        s.abort();

        System.out.println(s.get("g")); // -2147483648
        System.out.println(s.get("f")); // -2147483648
        System.out.println(s.get("e")); // 50
        s.commit();
    }
}
