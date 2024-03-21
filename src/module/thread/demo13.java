package module.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * @author bk
 */
public class demo13 {

    public static void main(String[] args) {
        RunnableImpl2 runnableImpl2 = new RunnableImpl2(Thread.currentThread());
        new Thread(runnableImpl2).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("before park");
        LockSupport.park();
        System.out.println("after park");

    }
}

class RunnableImpl2 implements Runnable {

    private Thread thread;

    public RunnableImpl2(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        System.out.println("before unpark");
        LockSupport.unpark(thread);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("after unpark");
    }
}
