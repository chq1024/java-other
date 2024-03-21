package module.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author bk
 */
public class demo30 {

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();


    public static void main(String[] args) {
        demo30 demo30 = new demo30();
        new Thread(()->{
            lock.lock();
            try {
                System.out.println("当前线程：" + Thread.currentThread().getName() + "开始等待");
                condition.await();
                System.out.println("当前线程：" + Thread.currentThread().getName() + "结束等待");
            }catch (Exception e) {

            }finally {
                lock.unlock();
            }
        }).start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        demo30.method1();
    }


    public void method1() {
        lock.lock();
        try {
            System.out.println("当前线程：" + Thread.currentThread().getName() + "开始等待");
            Thread.sleep(10000);
            condition.signal();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "结束等待");
            Thread.sleep(5000);
            System.out.println("~~~~~~~~~~~~");
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
