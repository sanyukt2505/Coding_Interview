package algos;

/**
 * Heap is a Tree-based data structure in which the tree is a
 * Complete Binary tree: All levels are completely filled except possibly the last level and the last level has all keys as left as possible.
 *   This property of Binary Heap makes them suitable to be stored in an array.
 *     - The root element will be at Arr[0].
 *     - for the ith node, i.e., Arr[i]
 *          - Arr[(i-1)/2]	    Returns the parent node
 *          - Arr[(2*i)+1]	    Returns the left child node
 *          - Arr[(2*i)+2]	    Returns the right child node
 * Max-Heap: In a Max-Heap the key at root node must be greatest among the keys present at all of it’s children.
 *          The same property must be recursively true for all sub-trees in that Binary Tree.
 * Min-Heap: In a Min-Heap the key at root node must be minimum among the keys present at all of it’s children.
 *          The same property must be recursively true for all sub-trees in that Binary Tree.
 *
 * For Priority Queues use Heap
 *
 *   A Maxheap can be build in O(n)
 */
public class MinHeap {
    public static class Node {
        private int data;

        public Node(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }
    }

    private int maxSize;
    private int currSize;
    private Node[] heapArr;

    public MinHeap(int size) {
        this.maxSize = size;
        this.heapArr = new Node[size];
        this.currSize = 0;
    }

    int parent(int i) { return (i-1)/2; }

    // to get index of left child of node at index i
    int left(int i) { return (2*i + 1); }

    // to get index of right child of node at index i
    int right(int i) { return (2*i + 2); }

    /**
     * Inserting a new key takes O(Logn) time. We add a new key at the end of the tree.
     * If new key is greater than its parent, then we don’t need to do anything.
     * Otherwise, we need to traverse up to fix the violated heap property.
     */
    public boolean insert(int key) {
        if(currSize == maxSize)
            return false;

        Node newNode = new Node(key);
        heapArr[currSize] = newNode;
        trickleUp(currSize++);
        return true;
    }

    public void trickleUp(int index) {
        Node bottom = heapArr[index];
        int parentIndex = parent(index);

        while(index > 0 && heapArr[parentIndex].getData() > bottom.getData()) {
            heapArr[index] = heapArr[parentIndex];
            index = parentIndex;
            parentIndex = parent(index);
        }
        heapArr[index] = bottom;
    }

    /**
     * deleteRecursive a new key takes O(Logn) time. We deleteRecursive element at the index and replaces with the  last element.
     * If replaced element is smaller than its children, then we don’t need to do anything.
     * Otherwise, we need to traverse down to heapify the tree below that element.
     */
    public Node delete(int index) {
        Node root = heapArr[index];
        heapArr[index] = heapArr[--currSize];
        minHeapify(index);
        return root;
    }

    /**
     * * Careful analysis turns out it O(n) https://www.youtube.com/watch?v=B7hVxCmfPtM 37:00
     *      - Nodes above leaf are large but has to do a constant work O(1)
     *      - Nodes that are l level above leaf has to do O(l) work
     *      - Root node has to do maximum work, but it just one
     *      - num of node at L level = n / (2 ^ (l + 1))
     *      - n/4 at level 1, n/8 at level 2 ...... 1 node at log(n) level
     *      - Total work = n/4 * (1*c)  + n/8 * (2*c) + ...... + 1 *(lg n * c)    where c is a const
     *                   = c 2^k ( 1/2^0  + 2/2^1 + 3/2^2 +  ..... + (k+1)/2^k )        -- setting n/4 = 2^k
     *                           This is a convergence series bounded by constant
     *
     * A recursive method to heapify a subtree with the root at given index
     * This method assumes that the subtrees are already heapified
     */
    public void minHeapify(int index) {         // also known as trickleDown ()

        int left = left(index);
        int right = right(index);
        int smallestChild = index;

        if(left < currSize && heapArr[left].getData() < heapArr[index].getData())
            smallestChild = left;
        if(right < currSize && heapArr[right].getData() < heapArr[smallestChild].getData())
            smallestChild = right;

        if(smallestChild != index) {
            //swap the two
            Node temp = heapArr[smallestChild];
            heapArr[smallestChild] = heapArr[index];
            heapArr[index] = temp;

            minHeapify(smallestChild);
        }
    }

    public void printHeap() {
        for (int i=0; i < currSize; i++) {
            System.out.print(heapArr[i].getData() + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MinHeap heap = new MinHeap(20);
        heap.insert(16);
        heap.insert(5);
        heap.insert(11);
        heap.insert(12);
        heap.insert(7);
        heap.insert(2);
        heap.insert(26);
        heap.insert(25);

        heap.printHeap();
        heap.delete(3);
        heap.printHeap();

    }
}
