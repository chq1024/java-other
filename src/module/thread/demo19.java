package module.thread;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.locks.LockSupport;

/**
 * @author bk
 */
public class demo19 {


    public static void main(String[] args) {
        demo19 demo19 = new demo19();
        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            demo19.doIt();
        },"A");
        thread1.start();

        Thread thread2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            demo19.doIt();
        },"B");
        thread2.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        demo19 demo2x = new demo19();
        demo19.doNotify();
    }


    public synchronized void doIt() {
        System.out.println(currTime()  + Thread.currentThread().getName() + " 被wait了");
        try {
            this.wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(currTime() + Thread.currentThread().getName() + " 被notify了");
    }

    public synchronized void doNotify() {
//        LockSupport.unpark(thread);
        this.notifyAll();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " notify");
    }

    public String currTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
