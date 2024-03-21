package module.thread;

import java.util.concurrent.*;

/**
 * @author bk
 */
public class demo3 {

    public static void main(String[] args) {
        ThreadFactory threadFactory = new ThreadFactory() {
            private int incr = 0;
            @Override
            public Thread newThread(Runnable r) {
                incr++;
               return new Thread(r,"Thread-" + incr);
            }
        };
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 10, 5000, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy());
        Future<String> future = threadPoolExecutor.submit(() -> {
            return Thread.currentThread().getName() + ":hello world";
        });
        try {
            String res = future.get();
            System.out.println(res);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            threadPoolExecutor.shutdown();
        }
    }
}
