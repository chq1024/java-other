package Demo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author bk
 */
public class demo1 {

    public static void main(String[] args) {
//        Semaphore semaphore = new Semaphore(3);
//        ExecutorService executorService = Executors.newFixedThreadPool(3);
//
//        for (int i = 0; i < 20; i++) {
//            try {
//                semaphore.acquire();
//                executorService.submit(()->{
//                    String threadName = Thread.currentThread().getName();
//                    try {
//                        Thread.sleep(1000);
//                        System.out.println("currenThread:" + threadName + " less:" + semaphore.availablePermits());
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    } finally {
//                        semaphore.release();
//                    }
//                });
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//
//        }
//        executorService.shutdown();
        String jdbcUrl = "jdbc:mysql://localhost:3306/vesta_log_10001?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
        String[] split = jdbcUrl.split("/");
        String s = split[split.length - 1];
        String s1 = s.split("\\?")[0];
        System.out.println(s1);
    }
}
