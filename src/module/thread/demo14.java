package module.thread;

/**
 * @author bk
 */
public class demo14 {

    private volatile static int a = 0;
    public static void main(String[] args) throws InterruptedException {
        Runnable r = () -> {
            for (int i = 0; i < 10000; i++) {
                a++;
            }
        };

        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(a);
    }
}
