package module.sort;

import java.util.Arrays;

/**
 * 选择排序
 * @author bk
 */
public class SelectionSort {

    public void run(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int mi = i;
            for (int j = i + 1; j < nums.length; j++) {
               if (nums[j] < nums[i]) {
                   mi = j;
               }
            }
            int min = nums[mi];
            nums[mi] = nums[i];
            nums[i] = min;
        }
    }

    public static void main(String[] args) {
        SelectionSort selectionSort = new SelectionSort();
        int[] nums = new int[] {2,3,1,5,4};
        selectionSort.run(nums);
        System.out.println(Arrays.toString(nums));
    }

}
