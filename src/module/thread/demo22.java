package module.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author bk
 */
public class demo22 {
    public static void main(String[] args) {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        Lock readLock =  readWriteLock.readLock();
        Lock writeLock =  readWriteLock.writeLock();
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("开始~");
                readLock.lock();
                System.out.println("xxxxxxxxx");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                readLock.unlock();
            }
        });
        thread.start();

        Thread thread2 = new Thread(() -> {
            try {
                if (readLock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                    System.out.println("222222222");
                    readLock.unlock();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                readLock.unlock();
            }
        });
        thread2.start();


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Thread thread1 = new Thread(() -> {
            try {
                writeLock.lock();
                System.out.println("get write lock");
            } finally {
                writeLock.unlock();
            }
        });
        thread1.start();
    }
}
