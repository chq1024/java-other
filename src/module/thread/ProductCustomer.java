package module.thread;

import java.util.LinkedList;

/**
 * @author bk
 */
public class ProductCustomer {

    public static void main(String[] args) {

        MyQueue queue = new MyQueue();
        Product product = new Product(queue);
        Customer customer = new Customer(queue);

        new Thread(product).start();
        new Thread(customer).start();

    }
}
class Product implements Runnable{

    private MyQueue queue;

    public Product(MyQueue queue) {
        this.queue = queue;
    }

    public void put(String message) {
        queue.put(message);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            put(String.valueOf(i));
        }
    }
}

class Customer implements Runnable{

    private MyQueue queue;

    public Customer(MyQueue queue) {
        this.queue = queue;
    }

    public String take() {
        return queue.take();
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(take());
        }
    }
}

class MyQueue {

    private int max = 10;

    private LinkedList<String> queue = new LinkedList<>();

    public synchronized void put(String message) {
        if (size() >= max) {
            try {
                System.out.println("put被阻塞了~");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        queue.add(message);
    }

    public synchronized String take() {
        while (size() == 0) {
            try {
                System.out.println("take被阻塞了~");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("take被唤醒了~");
        notifyAll();
        String take = queue.getFirst();
        queue.remove();
        return take;
    }

    public int size() {
        return queue.size();
    }
}

