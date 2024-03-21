package module.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author bk
 */
public class demo21 {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();

        Thread thread = new Thread(() -> {
            try {
                if (reentrantLock.tryLock(2000, TimeUnit.MILLISECONDS)) {
                    System.out.println("hello");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {
                if (reentrantLock.isHeldByCurrentThread()) {
                    reentrantLock.unlock();
                }
            }
        });
        thread.start();

        try {
            TimeUnit.MILLISECONDS.sleep(1000);
            thread.interrupt();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
