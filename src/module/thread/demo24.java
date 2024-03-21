package module.thread;

import java.util.concurrent.atomic.LongAdder;

/**
 * @author bk
 */
public class demo24 implements Runnable{

    private LongAdder adder = new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        demo24 demo24 = new demo24();
        Thread thread1 = new Thread(demo24);
        Thread thread2 = new Thread(demo24);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();


        System.out.println(demo24.adder.sum());;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            adder.increment();
        }

    }
}
