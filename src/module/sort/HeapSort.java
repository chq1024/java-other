package module.sort;

import java.util.Arrays;
import java.util.BitSet;

/**
 * @author bk
 */
public class HeapSort {

    public void siftDown(int[] nums,int n,int i) {
        while (true) {
            int l = 2 * i + 1;
            int r = 2 * i + 2;
            int ma = i;
            if (l < n && nums[l] > nums[ma]) {
                ma = l;
            }
            if (r < n && nums[r] > nums[ma]) {
                ma = r;
            }
            if ( ma == i) {
                break;
            }
            int temp = nums[i];
            nums[i] = nums[ma];
            nums[ma] = temp;
            i = ma;
        }
    }

    public void run(int[] nums) {
        for (int i = nums.length / 2 - 1; i > 0; i--) {
            siftDown(nums,nums.length,i);
        }
        for (int i = nums.length - 1; i > 0; i--) {
            int temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;
            siftDown(nums,i,0);
        }
    }

    public static void main(String[] args) {
        HeapSort heapSort = new HeapSort();
        int[] nums = new int[] {2,3,1,5,4};
        heapSort.run(nums);
        System.out.println(Arrays.toString(nums));
    }
}
