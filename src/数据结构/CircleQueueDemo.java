package 数据结构;

/**
 * @author bk
 */
public class CircleQueueDemo {


    class CircleQueue {
        private int maxSize;
        private int font;
        private int rear;
        private int[] arr;

        public CircleQueue(int arrMaxSize) {
            this.maxSize = arrMaxSize + 1;
            this.font = 0;
            this.rear = 0;
            this.arr = new int[maxSize];
        }

        public void add(int num) {
            if (isFull()) {
                throw new RuntimeException("队列已满");
            }
            this.arr[rear] = num;
            rear = (rear + 1) % maxSize;
        }

        public int get() {
            if (isEmpty()) {
                throw new RuntimeException("队列为空");
            }
            int val = arr[font];
            font = (font + 1) % maxSize;
            return val;
        }

        private boolean isEmpty() {
            return font == rear;
        }

        private boolean isFull() {
            return (rear + 1) % maxSize == font;
        }
    }
}
