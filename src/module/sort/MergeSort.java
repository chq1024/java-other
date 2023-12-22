package module.sort;

import java.util.Arrays;

/**
 * @author bk
 */
public class MergeSort {

    /* 归并排序 */
    public void run(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) /2;
        run(nums,left,mid);
        run(nums,mid + 1,right);
        merge(nums,left,mid,right);
    }

    /* 合并左子数组和右子数组 */
    public void merge(int[] nums, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left,j = mid + 1,k = 0;
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++]  = nums[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = nums[i++];
        }
        while (j <= right) {
            temp[k++] = nums[j++];
        }
        for (int q = 0; q < temp.length; q++) {
            nums[left + q] = temp[q];
        }
    }


    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] nums = new int[] {2,3,1,5,4};
        mergeSort.run(nums,0,nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

}
