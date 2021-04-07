package Threading;

class Message {
    int count;
    // True if receiver should wait
    // False if sender should wait
    private boolean transfer = true;

    public void send() {
        synchronized (this) {
//            while (count > 10) {
            while (!transfer) {
                try {
                    wait();
                } catch (InterruptedException e)  {
                    Thread.currentThread().interrupt();
                    System.out.println("Thread interrupted" + e);
                }
            }
            transfer = false;

            count++;
            System.out.println("Produced: " + count);
            notifyAll();
        }
    }

    public  int receive() {
        synchronized (this) {
//            while (count < 5) {
            while (transfer) {
                try {
                    wait();
                } catch (InterruptedException e)  {
                    Thread.currentThread().interrupt();
                    System.out.println("Thread interrupted" + e);
                }
            }
            transfer = true;

            notifyAll();
            count--;
            System.out.println("Consumed: " + count);
            return count;
        }
    }
}

class Consumer extends Thread {
    private Message consumerMsg;

    public Consumer(Message msg) {
        this.consumerMsg = msg;
    }

    @Override
    public void run() {
        while (true) {
            consumerMsg.send();
        }
    }
}

class Producer extends Thread {
    private Message producerMsg;

    public Producer(Message msg) {
        this.producerMsg = msg;
    }

    @Override
    public void run() {
        while (true) {
            producerMsg.receive();
        }
    }
}

public class ProducerConsumer {
    public static void main(String[] args) {
        Message msg = new Message();
        Producer producer = new Producer(msg);
        Consumer consumer = new Consumer(msg);

        producer.start();
        consumer.start();
    }
}
