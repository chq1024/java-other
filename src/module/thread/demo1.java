package module.thread;

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
    }
}
