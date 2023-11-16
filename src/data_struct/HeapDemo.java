package data_struct;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 堆本身是一个完全二叉树 分为大顶堆和小顶堆
 * 以下用大顶堆为例，即堆顶为最大元素
 * 而完全二叉树又最适用于数组来表示，紧凑无null
 *
 * @author bk
 */
public class HeapDemo {
    // 大顶堆
//    Queue<Integer> queue = new PriorityQueue<>((a,b)->b-a);

    List<Integer> maxHeap = new ArrayList<>();

    public int left(int index) {
        return 2 * index + 1;
    }

    public int right(int index) {
        return 2 * index + 2;
    }

    public int parent(int index) {
        return (index - 1) / 2;
    }

    public int peek() {
        return maxHeap.get(0);
    }

    public void push(int value) {
        maxHeap.add(value);
        siftUp(maxHeap.size() - 1);
    }

    public int pop() {
        if (maxHeap.isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        swap(0, maxHeap.size() - 1);
        Integer remove = maxHeap.remove(maxHeap.size() - 1);
        siftDown(0);
        return remove;
    }

    private void swap(int a, int b) {
        int av = maxHeap.get(a);
        int bv = maxHeap.get(b);
        maxHeap.set(a, bv);
        maxHeap.set(b, av);
    }

    // 堆化(自底向顶)
    private void siftUp(int index) {
        while (true) {
            int parent = parent(index);
            if (parent < 0 || maxHeap.get(index) <= maxHeap.get(parent)) {
                break;
            }
            swap(index, parent);
            index = parent;
        }
    }

    // 堆化，自顶向底
    private void siftDown(int index) {
        while (true) {
            int l = left(index);
            int r = right(index);
            int m = index;
            if (l < maxHeap.size() && maxHeap.get(l) > maxHeap.get(m)) {
                m = l;
            }
            if (r < maxHeap.size() && maxHeap.get(r) > maxHeap.get(m)) {
                m = r;
            }
            if (m == index) {
                break;
            }
            swap(m, index);
            index = m;
        }
    }

    public void maxHeapCreate() {
        List<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(4);
        arr.add(6);
        arr.add(2);
        arr.add(3);
        arr.add(9);
        arr.add(5);
        arr.add(7);
        maxHeap.addAll(arr);
        for (int i = parent(arr.size() - 1); i >= 0; i--) {
            siftDown(i);
        }
//        for (Integer value : arr) {
//            push(value);
//        }
    }


    public static void main(String[] args) {
        HeapDemo demo = new HeapDemo();
        demo.maxHeapCreate();
        System.out.println(demo.maxHeap);
    }

}
