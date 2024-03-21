package module.lock;

import java.util.concurrent.*;

/**
 * @author bk
 */
public class demo1 {

    private int num = 0;

    public synchronized void lockMethod() {
        num++;
    }

    public static void main(String[] args) {
        demo1 obj1 = new demo1();

        ThreadPoolExecutor threadPool = Util.getThreadPool();
        for (int i = 0; i < 1000; i++) {
            threadPool.execute(()->{
                obj1.lockMethod();
            });
        }
        threadPool.shutdown();
        System.out.println(obj1.num);
    }
}
