package module.thread;

import java.util.concurrent.Callable;

/**
 * @author bk
 */
public class demo11{

    public static void main(String[] args) {
        Runnable runnable = () -> {
            int num = 0;
            int decr = 15;
            while (true) {
                if (num > 10) {
                    try {
                        Thread.currentThread().wait();
                        if (num >= 15) {
                            System.out.println("finish");
                            break;
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    num++;
                    decr--;
                    if (decr ==0) {
                        Thread.currentThread().notify();
                    }
                }
            }
            System.out.println("hello");
        };
        Thread thread = new Thread(runnable);
        thread.start();
        System.out.println(thread.isAlive());
        System.out.println(thread.isInterrupted());
        try {
            Thread.sleep(5000);
            thread.interrupt();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
