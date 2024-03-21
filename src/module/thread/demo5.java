package module.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author bk
 */
public class demo5 {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        MyForkJoinTask myForkJoinTask = new MyForkJoinTask(1,100,10);
        ForkJoinTask<Integer> submit = forkJoinPool.submit(myForkJoinTask);
        System.out.println(submit.join());
    }


    public static class MyForkJoinTask extends RecursiveTask<Integer>{

        private int start;
        private int end;
        private int pre;
        public MyForkJoinTask(int start,int end,int pre) {
            this.start = start;
            this.end = end;
            this.pre = pre;
        }

        @Override
        protected Integer compute() {
            if (end - start < pre) {
                int num = 0;
                int incr = end - start + 1;
                for (int i = 0; i < incr; i++) {
                    num += start;
                    start++;
                }
                return num;
            } else {
                int times = (end - start) / pre;
                if (times > 0) {
                    times = times + 1;
                }
                List<MyForkJoinTask> tasks  = new ArrayList<>();
                for (int i = 0; i < times; i++) {
                    int a = start + i * pre;
                    int b = start + (i+1) * pre - 1;
                    MyForkJoinTask myForkJoinTask = new MyForkJoinTask(a, b, pre);
                    myForkJoinTask.fork();
                    tasks.add(myForkJoinTask);
                }
                int num = 0;
                for (MyForkJoinTask task : tasks) {
                    num += task.join();
                }
                return num;
            }
        }
    }
}
