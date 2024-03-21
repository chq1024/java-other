package module.thread;

import java.util.concurrent.*;

/**
 * @author bk
 */
public class demo16 {

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7,8,9,10};
        MyTask myTask = new MyTask(arr,0,10);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> submit = forkJoinPool.submit(myTask);
        try {
            System.out.println(submit.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            forkJoinPool.close();
        }
    }
}


class MyTask extends RecursiveTask<Integer> {

    private int[] arr;
    private int start, end;
    private int THRESHOLD =  4;

    public MyTask(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end - start <= THRESHOLD) {
            int sum = 0;
            for (int i = start; i < end; i++) {
                sum += this.arr[i];
                // 故意放慢计算速度:
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                }
            }
            return sum;
        }
        int mid = (end - start) / 2;
        MyTask myTask1 = new MyTask(this.arr, start, mid); // 0-5  // 0-2
        MyTask myTask2 = new MyTask(this.arr, mid+1, end);//6,10
        invokeAll(myTask1,myTask2);
        return myTask1.join() + myTask2.join();
    }
}
