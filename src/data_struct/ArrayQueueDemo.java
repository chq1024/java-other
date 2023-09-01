package data_struct;

/**
 * @author bk
 */
public class ArrayQueueDemo {


    class ArrayQueue {

        private final int maxSize;
        private final int[] arr;
        private int font;
        private int tail;

        public ArrayQueue(int maxSize) {
            this.maxSize = maxSize;
            this.arr = new int[maxSize];
            this.font = this.tail = -1;
        }

        public void add(int num) {
            if (tail == maxSize) {
                throw new RuntimeException("队列已满");
            }
            this.arr[++tail] =  num;
        }

        public int get() {
            if (tail == font) {
                throw new RuntimeException("队列为空");
            }
            return this.arr[++font];
        }

        public void show() {
            if (tail == font) {
                throw new RuntimeException("队列为空");
            }
            for (int i = 0; i < arr.length; i++) {
                System.out.printf("arr[%d] = %d \n", i, arr[i]);
            }
        }

    }

}
