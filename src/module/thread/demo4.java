package module.thread;

import java.util.concurrent.*;

/**
 * @author bk
 */
public class demo4 {

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
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return Thread.currentThread().getName() + ":1";
        }, threadPoolExecutor).whenComplete((r,e)->{
            if (e != null) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "come in 1");
        });

        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            return Thread.currentThread().getName() + ":2";
        }, threadPoolExecutor).whenComplete((r,e)->{
            if (e != null) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "come in 2");
        });
        CompletableFuture<String> completableFuture1 = completableFuture.applyToEitherAsync(completableFuture2, (o) -> {
            return o;
        });
        String res = completableFuture1.join();
        System.out.println(res);
        threadPoolExecutor.shutdown();
    }
}
