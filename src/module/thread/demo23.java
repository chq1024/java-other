package module.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author bk
 */
public class demo23 implements Runnable{

    private AtomicInteger i = new AtomicInteger(0);

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        for (int j = 0; j < 10000; j++) {
            i.incrementAndGet();
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }

    public int getI() {
        return i.get();
    }

    public static void main(String[] args) throws InterruptedException {
        demo23 demo231 = new demo23();
        Thread thread1 = new Thread(demo231);
        Thread thread2 = new Thread(demo231);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println(demo231.getI());
    }

}
