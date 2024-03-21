package module.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author bk
 */
public class demo29 {


    public static void main(String[] args) {
        AtomicInteger incr = new AtomicInteger(0);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 3, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "Threadx-" + incr.getAndIncrement());
            }
        },new ThreadPoolExecutor.CallerRunsPolicy());


//        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(new Task2());
//        CompletableFuture<Void> completableFuture2 = CompletableFuture.runAsync(new Task2());
//        CompletableFuture<Void> completableFuture3 = CompletableFuture.runAsync(new Task2());
//        CompletableFuture<Void> completableFuture = CompletableFuture.allOf(completableFuture1, completableFuture2, completableFuture3);
//        try {
//            completableFuture.get();
//        } catch (InterruptedException | ExecutionException e) {
//            throw new RuntimeException(e);
//        }

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName());
                return "hello";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, executor);


        try {
//            completableFuture.whenComplete((t,e)->{
//                System.out.println(Thread.currentThread().getName() + " exec");
//            }).get();
            CompletableFuture<String> stringCompletableFuture = completableFuture.thenCombine(CompletableFuture.supplyAsync(()->{
                System.out.println("inner");
                return "inner";
            },executor),(x, r)->{
                System.out.println(x + " " +r );
                return "xr";
            });
            System.out.println(completableFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            executor.close();
        }

    }
}


class Task2 implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " do !");
    }
}