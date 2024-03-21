package module.thread;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.*;

import static java.lang.Thread.interrupted;

/**
 * @author bk
 */
public class demo17 {

    public static void main(String[] args)  {
        ThreadFactory factory = r -> new Thread(r, String.valueOf(new Random().nextInt()));
//        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor(factory);
//        executorService.scheduleAtFixedRate(()->{
//            System.out.println(Thread.currentThread().getName() + " 当前时间:" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//        },3000,3000, TimeUnit.MILLISECONDS);
        demo17 demo17 = new demo17();
        DelayQueue<TaskDelay> delayeds = new DelayQueue<>();
        delayeds.add(new TaskDelay(toMill(LocalDateTime.now().plusSeconds(3)),"A"));
        delayeds.add(new TaskDelay(toMill(LocalDateTime.now().plusSeconds(7)),"B"));
        delayeds.add(new TaskDelay(toMill(LocalDateTime.now().plusSeconds(10)),"C"));
        Thread thread = new Thread(() -> {
            try {
                demo17.doIt(delayeds);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, "A");
        thread.start();
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        delayeds.add(new TaskDelay(toMill(LocalDateTime.now().plusSeconds(1)),"D"));
        delayeds.add(new TaskDelay(toMill(LocalDateTime.now().plusSeconds(2)),"E"));
        delayeds.add(new TaskDelay(toMill(LocalDateTime.now().plusSeconds(3)),"F"));
        demo17.notifyThread();

        System.out.println(thread.isInterrupted());
        System.out.println(thread.getState());
        while (true) {
            if (delayeds.isEmpty()) {
                thread.interrupt();
                break;
            }
        }
    }

    public static long toMill(LocalDateTime dateTime) {
        return dateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    public synchronized void doIt(DelayQueue<TaskDelay> delayQueue) {
        while (true) {
            System.out.println("Thread:" + Thread.currentThread().getName());
            if (delayQueue.isEmpty()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.out.println("线程停止中~");
                    break;
                }
            }
            TaskDelay take = null;
            try {
                take = delayQueue.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            take.sysUnique();
        }
    }

    public synchronized void notifyThread() {
        notifyAll();
    }
}

class TaskDelay implements Delayed {

    public long ttl;
    private String unique;

    public TaskDelay(long ttl,String unique) {
        this.ttl = ttl;
        this.unique = unique;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long second = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        return unit.convert(ttl-second,TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }

    public void sysUnique() {
        System.out.println(unique);
    }
}
