package module.thread;

import io.netty.util.concurrent.DefaultThreadFactory;
import io.netty.util.concurrent.ThreadPerTaskExecutor;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author bk
 */
public class demo18 {

    public static void main(String[] args) {
//        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
//        queue.add("A");
//        queue.add("B");
//
//        LinkedBlockingDeque<String> linkQueue = new LinkedBlockingDeque<>();
        DefaultThreadFactory defaultThreadFactory = new DefaultThreadFactory("pool");
        AtomicInteger incr = new AtomicInteger(0);
        AtomicInteger incrSucc = new AtomicInteger(0);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 5, 2, TimeUnit.SECONDS, new ArrayBlockingQueue<>(50), defaultThreadFactory, new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            threadPoolExecutor.execute(()->{
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    incr.incrementAndGet();
                    throw new RuntimeException(e);
                }
                System.out.println("当前线程：" + finalI + "-" + Thread.currentThread().getName());
                incrSucc.incrementAndGet();
            });
        }
        List<Runnable> runnables = threadPoolExecutor.shutdownNow();
        System.out.println(runnables.size());
        System.out.println("正在执行并失败的数量：" + incr.get());
        System.out.println("执行成功的数量：" + incrSucc.get());
        System.out.println("继续执行~");
    }
}
