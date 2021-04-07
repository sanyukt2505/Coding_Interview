package algos;

public class MyStack {

    public int maxSize;
    public int[] arr;
    public int top;

    public MyStack(int size) {
        maxSize = size;
        arr = new int[size];
        top = -1;
    }

    public boolean push(int a) {
        if (isFull())
            return false;

        arr[++top] = a;
        return true;
    }

    public int pop() {
        if (isEmpty())
            return -1;

        return arr[top--];
    }

    public boolean isFull() {
        return top == maxSize-1;
    }

    public boolean isEmpty() {
        return top == -1;
    }


    public static void main(String []args){
        MyStack stack = new MyStack(10);
        System.out.println(stack.push(20));
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.push(2));

        for (int i=0; i < 10; i++)
            System.out.println(stack.push(i));
    }
}
