package module.thread;

import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.concurrent.*;

/**
 * @author bk
 */
public class demo15 {

    public static void main(String[] args) throws InterruptedException {
//        final Integer[] a = {0};
//
//        ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
//
//        ThreadFactory factory = Thread.ofVirtual().name("xxx",10).factory();
//        ThreadFactory factory1 = new ThreadFactory() {
//            @Override
//            public Thread newThread(Runnable r) {
//                return new Thread(r,"x");
//            }
//        };
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        for (int i = 0; i < 9; i++) {
            scheduledExecutorService.schedule(()->{
                try {
                    Thread.sleep(5000);
                    System.out.println(Thread.currentThread().getName() + " running");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            },1000,TimeUnit.MILLISECONDS);
        }
        scheduledExecutorService.close();

//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2, 10000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10), factory);
//        for (int i = 0; i < 100; i++) {
//            threadPoolExecutor.execute(()->{
//
//            });
//        }
        //        ExecutorService executorService = Executors.newThreadPerTaskExecutor(factory);
//        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
//        try {
//            executorService.submit(() -> {
//                try {
//                    Thread.sleep(5000);
//                    System.out.println("是否是虚拟线程:" + Thread.currentThread().isVirtual() );
//                    System.out.println("虚拟线程运行完毕~");
//                    System.out.println("虚拟线程:"+ Thread.currentThread().getName());
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            });
////            try {
////                Integer o = submit.get();
////                System.out.println(o);
////            } catch (InterruptedException | ExecutionException e) {
////                throw new RuntimeException(e);
////            }
//        }finally {
//            executorService.close();
//        }

//        executorService.submit(() -> {
//            for (int i = 0; i < 10000; i++) {
//                System.out.println("1." + i + " " + Thread.currentThread().threadId());
//                a[0]++;
//            }
//            return true;
//        });
//        executorService.submit(() -> {
//            for (int i = 0; i < 10000; i++) {
//                System.out.println("2." + i + " " + Thread.currentThread().threadId());
//                a[0]++;
//            }
//            return true;
//        });
//        executorService.close();
//
//        System.out.println(a[0]);



//        for (int i = 0; i < 10000; i++) {
//            int finalI = i;
//            factory.newThread(()->{
//                System.out.println(Thread.currentThread().getName() + ":" + finalI);
//            }).start();
//        }
//        ThreadFactory factory = Thread.ofVirtual().name("xxx",10).factory();
//        Thread thread = factory.newThread(() -> {
//            try {
//                System.out.println(Thread.currentThread().isVirtual());
//                Thread.sleep(5000);
//                System.out.println("虚拟线程运行完毕~");
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        thread.start();
//        Thread thread = new Thread(() -> {
//                System.out.println(Thread.currentThread().isVirtual());
//                System.out.println("启动虚拟线程~");
//                for (int i = 0; i < 100; i++) {
//                    int finalI = i;
//                    Thread.ofVirtual().name("ooo").start(() -> {
//                        try {
//                            System.out.println("进入虚拟线程~" + finalI);
//                            Thread.sleep(3000);
//                            System.out.println("虚拟线程执行");
//                        } catch (InterruptedException e) {
//                            throw new RuntimeException(e);
//                        }
//                    });
//                }
//        });
//        thread.start();

//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println(thread.isVirtual());
//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }



//        String name = Thread.currentThread().getName();
//        System.out.println("主线程:" + name);
//        System.out.println("主线程运行");
//        Thread.sleep(5000);
    }
}
