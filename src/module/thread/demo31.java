package module.thread;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author bk
 */
public class demo31 {

    public static void main(String[] args) {
        ThreadFactory factory = Thread.ofVirtual().name("virtual-thread",0).factory();
        try (ExecutorService executorService = Executors.newThreadPerTaskExecutor(factory)){
            executorService.submit(()->{
                try {
                    Thread.sleep(10000);
                    throw new RuntimeException("xxxxxxxxxxx");
                } catch (RuntimeException e) {
                    throw new RuntimeException(e);
                }
            });
        }catch (Exception e) {

        }



    }
}
