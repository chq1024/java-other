package module.thread;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author bk
 */
public class demo12 {

    public static void main(String[] args) {
        demo12 mainClazz = new demo12();
        Thread thread1 = new Thread(mainClazz::doHandle,"A");
        Thread thread2 = new Thread(new Runnable2(thread1),"B");

        thread1.start();
        try {
            System.out.println(Thread.currentThread().getName() + ":" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        thread2.start();
    }
    public void doHandle() {
        Thread waitThread = Thread.currentThread();
        synchronized (waitThread) {
            try {
                System.out.println(Thread.currentThread().getName() + ":" + waitThread.getName() + " is waiting");
                // 释放锁
                waitThread.wait();
//                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + ":" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                System.out.println(waitThread.getName() + " is running");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Runnable2 implements Runnable {

    private final Thread otherThread;

    public Runnable2(Thread otherThread) {
        this.otherThread = otherThread;
    }

    @Override
    public void run() {
        synchronized (otherThread) {
            try {
                // 唤醒otherThread，获得锁，但没有释放锁，等待下面数据执行完成后 释放锁，释放后otherThread端就会获得锁继续执行
                otherThread.notify();

                System.out.println(Thread.currentThread().getName() + ":" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                Thread.sleep(2000);
                System.out.println("唤醒线程...." + otherThread.getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}