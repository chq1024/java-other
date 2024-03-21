package module.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author bk
 */
public class demo10 {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " come in ");
                while (true) {
                    if (lock.tryLock()) {
                        System.out.println(Thread.currentThread().getName() + " get lock");
                        try {
                            Thread.sleep(2000);
                            break;
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        } finally {
                            lock.unlock();
                            System.out.println(Thread.currentThread().getName() + " lock unlock");
                        }
                    }
                }
            }
        };
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " come in ");
                while (true) {
                    if (lock.tryLock()) {
                        System.out.println(Thread.currentThread().getName() + " get lock");
                        try {
                            Thread.sleep(2000);
                            break;
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        } finally {
                            lock.unlock();
                            System.out.println(Thread.currentThread().getName() + " lock unlock");
                        }
                    }
                }
            }
        };

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);
        thread1.start();
        thread2.start();
    }
}
