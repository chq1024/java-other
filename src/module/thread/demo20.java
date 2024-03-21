package module.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author bk
 */
public class demo20 {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();

        new Thread(()->{
            try {
                reentrantLock.lock();
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("hello world");
            } finally {
                reentrantLock.unlock();
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new Thread(()->{
            try {
                if (reentrantLock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                    System.out.println(" get lock");
                    System.out.println(1 / 0);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                if (reentrantLock.isHeldByCurrentThread()) {
                    reentrantLock.unlock();
                }
            }
        }).start();

        try {
            Thread.sleep(3000);
            System.out.println("xxx" + reentrantLock.isLocked());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
