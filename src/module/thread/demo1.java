package module.thread;

import java.util.concurrent.TimeUnit;

/**
 * 守护线程要在所有线程处理完任务后
 * @author bk
 */
public class demo1 {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            String threadType = Thread.currentThread().isDaemon() ? "daemon" : "user";
            System.out.println(Thread.currentThread().getName() + ":" + threadType);
            while (true) {

            }
        });
        thread.setName("myThread");
        thread.setDaemon(true);
        thread.start();

        Thread thread1 = new Thread(() -> {
            String name = Thread.currentThread().getName();
            System.out.println(name);
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread1.start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(Thread.currentThread().getName() + " -----end");
    }
}
