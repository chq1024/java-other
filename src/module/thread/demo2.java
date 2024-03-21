package module.thread;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author bk
 */
public class demo2 {

    public static void main(String[] args) {
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        Callable<String> callTask = () -> {
            Thread.sleep(3000);
            return "hello world";
        };
        FutureTask<String> futureTask = new FutureTask<>(callTask);
        new Thread(futureTask, "thread1").start();

        String res = "";
        while (true) {
            if (futureTask.isDone()) {
                try {
                    res = futureTask.get();
                    System.out.println(res);
                    break;
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}
