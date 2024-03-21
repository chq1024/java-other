package module.thread;

import java.util.concurrent.*;

/**
 * @author bk
 */
public class demo7 {

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


        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + ":start");
            return 1;
        }, threadPoolExecutor).thenApply((t) -> {
            System.out.println(Thread.currentThread().getName() + ":" + t);
            int i = 1 / 0;
            return t + 1;
        }).thenApply((t) -> {
            System.out.println(Thread.currentThread().getName() + ":" + t);
            return t + 2;
        }).whenComplete((v, e) -> {
            if (e!= null) {
                System.out.println("产生异常:" + e.getMessage());
            } else {
                System.out.println(Thread.currentThread().getName() + ":结果:" + v);
            }
        }).exceptionally((e)->{
            String message = e.getMessage();
            System.out.println("错误消息:" + message);
            throw new RuntimeException(e);
        });
        System.out.println(future.join());

        threadPoolExecutor.shutdown();
    }
}
