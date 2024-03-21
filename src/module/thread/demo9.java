package module.thread;

import java.util.concurrent.*;

/**
 * @author bk
 */
public class demo9 {

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


        CompletableFuture<Integer> future1 =CompletableFuture.supplyAsync(()->{
            return 1;
        },threadPoolExecutor);

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            return 2;
        }, threadPoolExecutor);

        CompletableFuture<Integer> combine = future1.thenCombine(future2, (t1, t2) -> {
            return t1 + t2;
        });
        System.out.println(combine.join());
    }
}
