package module.sort;

import java.util.Arrays;

/**
 * @author bk
 */
public class InsertionSort {

    public void run(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int base = nums[i],j = i - 1;
            while (j >= 0 && nums[j] > base) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = base;
        }
    }

    public static void main(String[] args) {
        InsertionSort insertionSort = new InsertionSort();
        int[] nums = new int[] {2,3,1,5,4};
        insertionSort.run(nums);
        System.out.println(Arrays.toString(nums));
    }
}
