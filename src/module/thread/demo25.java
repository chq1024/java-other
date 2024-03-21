package module.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.IntStream;

/**
 * @author bk
 */
public class demo25 {

    private final LongAccumulator accumulator = new LongAccumulator((l, r)->l+r,0);

    public static void main(String[] args) throws InterruptedException {
        demo25 demo25 = new demo25();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        IntStream.range(1,10).forEach(i->{
            executorService.submit(()->{
                demo25.accumulator.accumulate(i);
            });
        });

        executorService.shutdown();

        while (!executorService.isTerminated()) {
            Thread.sleep(1000);
        }

        System.out.println(demo25.accumulator.getThenReset());
    }
}
