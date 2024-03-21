package module.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author bk
 */
public class demo27 implements Callable<String> {

    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        return Thread.currentThread().getName() + " " + UUID.randomUUID().toString();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        AtomicInteger inc = new AtomicInteger(0);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 10, 3, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                r -> new Thread(r,"Thread-" + inc.getAndIncrement()),
        new ThreadPoolExecutor.CallerRunsPolicy());

        List<Future<String>> arr = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            Future<String> submit = threadPoolExecutor.submit(new demo27());
            arr.add(submit);
        }
        threadPoolExecutor.close();
        long beginTime = System.currentTimeMillis();
        for (Future<String> future : arr) {
            String res = future.get();
            System.out.println(res);
        }
        long endTime = System.currentTimeMillis();

        System.out.println(endTime - beginTime);
    }
}
