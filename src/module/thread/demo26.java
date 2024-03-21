package module.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author bk
 */
public class demo26 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            executorService.submit(()->{
                SimpleDateFormat simpleFormatter = SpecialThreadLocal.dateFormatThreadLocal.get();
                System.out.println(simpleFormatter.format(new Date(1000  * finalI)));
            });
        }
        executorService.close();
        SpecialThreadLocal.dateFormatThreadLocal.remove();

        try {
            Thread thread = new Thread(() -> {
                throw new RuntimeException("xxxxxx");
            });
            thread.start();
        }catch (Exception e) {
            System.out.println("出现异常");
            e.printStackTrace();
        }

    }
}


class SpecialThreadLocal {
    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("mm:ss");
        }

    };

}