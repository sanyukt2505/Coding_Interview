package algos;

public class Queue{

    public int maxSize;
    public int[] arr;
    public int rear = -1;
    public int front = 0;

    public Queue(int size) {
        maxSize = size+1;
        arr = new int[maxSize];
    }

    public boolean insert(int a) {
        if (isFull())
            return false;

        if (rear == maxSize-1)
            rear = -1;
        arr[++rear] = a;
        return true;
    }

    public int remove() {
        if (isEmpty())
            return -1;

        int temp = arr[front++];
        if (front == maxSize)
            front = 0;
        return temp;
    }

    public boolean isEmpty() {
        return (rear+1 == front || front+maxSize-1 == rear);
    }

    public boolean isFull() {
        return (rear+2 == front || front+maxSize-2 == rear);
    }

    public static void main(String []args){
        Queue q = new  Queue(10);
        System.out.println(q.insert(20));
        System.out.println(q.remove());
        System.out.println(q.remove());
        System.out.println(q.insert(20));

        for (int i=0; i < 10; i++)
            System.out.println(q.insert(i));
    }
}