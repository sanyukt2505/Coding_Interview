package algos;

/**
 * to resolve collision, we use (https://www.geeksforgeeks.org/hashing-set-3-open-addressing/)
 * 1 - Separate Chaining - has a next pointer for each element, so that we can have a Linklist
 * 2 - Open Addressing
 *      i  - Linear probing - when collision, you look into the next index (x+1, x+2, x+3)
 *      ii - Quadratic probing (x+1, x+4, x+9)
 *      ii - Double hashing - stepSize = const - (key % const)
 */
public class HashTable {
    public static class HashItem {
        public int key;
        public int value;
        // we add a pointer to next element to support Separate Chaining
        public HashItem next;

        public HashItem(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private HashItem[] bucketArray;
    private int numBuckets;
    private int size;

    public HashTable(int bucketSize) {
        bucketArray = new HashItem[bucketSize];
        numBuckets = bucketSize;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * This implements hash function to find index for a key
     * A good hash function should have following properties
     * 1) Efficiently computable.
     * 2) Should uniformly distribute the keys (Each table position equally likely for each key)
     */
    private int hashFunc(int key) {
        int index = key % numBuckets;
        return index;
    }

    public int get(int key) {
        int hash = hashFunc(key);
        HashItem hashItem = this.bucketArray[hash];

        if (hashItem == null) {
            return -1;
        }

        while (hashItem != null && hashItem.key != key) {
            hashItem = hashItem.next;
        }

        if (hashItem == null){
            return  -1;
        } else {
            return hashItem.value;
        }
    }

    public void put(int key, int value) {
        int hash = hashFunc(key);
        HashItem hashItem = this.bucketArray[hash];

        if (hashItem == null) {
            // bucket is empty
            this.bucketArray[hash] = new HashItem(key, value);
            size++;
        } else {
            // Collision situation: There is already a HashItem at this index
            while (hashItem.next != null && hashItem.key != key) {
                hashItem = hashItem.next;
            }

            if (hashItem.key == key) {
                hashItem.value = value;
            } else {
                hashItem.next = new HashItem(key, value);
                size++;
            }
        }

        // If load factor goes beyond threshold, then double hash table size
        if ((1.0 * size)/numBuckets >= 0.7) {
            HashItem[] temp = bucketArray;

            int newBucketsSize = numBuckets * 2;
            bucketArray = new HashItem[newBucketsSize];
            size = 0;
            numBuckets = newBucketsSize;

            for (HashItem item : temp) {
                while (item != null) {
                    put(item.key, item.value);
                    item = item.next;
                }
            }

        }
    }

    public int remove(int key) {
        int hash = hashFunc(key);
        HashItem hashItem = this.bucketArray[hash];
        HashItem prev = null;

        if (hashItem == null) {
            return -1;
        } else {
            while (hashItem != null && hashItem.key != key) {
                prev = hashItem;
                hashItem = hashItem.next;
            }

            if (hashItem == null) {
                return -1;
            }

            if (prev != null) {
                prev.next = hashItem.next;
            } else {
                bucketArray[hash] = hashItem.next;
            }
            size--;
            return hashItem.value;
        }
    }

    public static void main(String[] args)
    {
        HashTable map = new HashTable(10);
        map.put(1, 101 );
        map.put(2, 201 );
        map.put(3, 401 );
        map.put(4, 401 );
        System.out.println(map.size());
        System.out.println(map.get(1));
        System.out.println(map.remove(1));
        System.out.println(map.get(1));
        System.out.println(map.size());
        System.out.println(map.isEmpty());
    }
}
