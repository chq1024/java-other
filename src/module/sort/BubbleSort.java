package module.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * @author bk
 */
public class BubbleSort {

    public void run(int[] nums) {
        for (int i = nums.length - 1; i > 0 ; i--) {
            boolean flag = false;
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        int[] nums = new int[] {2,3,1,5,4};
        bubbleSort.run(nums);
        System.out.println(Arrays.toString(nums));
    }
}
