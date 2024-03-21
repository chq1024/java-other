package module.lock;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author bk
 */
public class Util {

    public static ThreadPoolExecutor getThreadPool() {
        ThreadFactory threadFactory = new ThreadFactory() {
            private int incr = 0;
            @Override
            public Thread newThread(Runnable r) {
                incr++;
                return new Thread(r, "Thread-" + incr);
            }
        };
        return new ThreadPoolExecutor(3, 10, 5000, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                threadFactory,
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
