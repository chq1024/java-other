package module.thread;

import java.util.concurrent.*;

/**
 * @author bk
 */
public class demo8 {


    public static void main(String[] args) {
        ThreadFactory threadFactory = new ThreadFactory() {
            private int incr = 0;
            @Override
            public Thread newThread(Runnable r) {
                incr++;
                return new Thread(r, "Thread-" + incr);
            }
        };
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 10, 5000, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy());

        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName());
            System.out.println("A come in");
            return "A";
        },threadPoolExecutor);

        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName());
            System.out.println("B come in");
            return "B";
        },threadPoolExecutor);
        CompletableFuture<String> completableFuture = completableFuture1.applyToEitherAsync(completableFuture2, (r) -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(r + " win");
            return r;
        });
        String join = completableFuture.join();
        threadPoolExecutor.shutdown();
    }
}
