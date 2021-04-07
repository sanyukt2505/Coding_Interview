package problem.Design;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Merge k sorted Streams
 */
class Node {
    Integer value;
    IntegerStream stream;
    Node(Integer v, IntegerStream s) {
        value = v;
        stream = s;
    }
}

class IntegerStream {
    Queue<Node> stream = new LinkedList<>();
    Integer size = 0;

    public boolean hasNext() {
        return stream.size() != 0;
    }

    public Node next() {
        Node temp = stream.remove();;
        size--;
        return temp;
    }

    public void add(Integer x) {
        stream.add(new Node(x, this));
        size++;
    }
}
public class MergeKInputStream {
    IntegerStream output = new IntegerStream();

    public void mergeStream(List<IntegerStream> streams) {
        if (streams == null || streams.size() == 0)
            return;

        PriorityQueue<Node> minHeap = new PriorityQueue<>( (a, b) -> a.value - b.value);
        // fill the heap with the first elements from the streams
        for (IntegerStream stream: streams) {
            if (stream != null && stream.hasNext())
                minHeap.add(stream.next());
        }

        while (!minHeap.isEmpty()) {
            // take the min element of the min elements of each stream
            Node min = minHeap.poll();
            output.add(min.value);

            // we know the min came from a stream
            // we need to push the next element from that stream into heap
            if (min.stream.hasNext())
                minHeap.add(min.stream.next());
        }
    }
}
