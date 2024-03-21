package module.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author bk
 */
public class demo28 {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        AtomicInteger incr = new AtomicInteger(0);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 3, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "Thread-" + incr.getAndIncrement());
            }
        },new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 5; i++) {
            executor.submit(new Task(countDownLatch));
        }
        try {
            countDownLatch.await(1,TimeUnit.SECONDS);
            System.out.println(countDownLatch.getCount());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class Task implements Runnable {

    private final CountDownLatch countDownLatch;

    public Task(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getName() + " do");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
