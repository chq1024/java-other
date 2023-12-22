package module.sort;

import java.util.Arrays;

/**
 * @author bk
 */
public class QuickSort {

    public void run(int[] nums,int left,int right) {
//        if (left >= right) {
//            return;
//        }
//        int pos = partition(nums,left,right);
//        run(nums,left,pos -1);
//        run(nums,pos + 1,right);
        while (left < right) {
            int partition = partition(nums, left, right);
            if (partition - left < right - partition) {
                run(nums,left,partition - 1);
                left = partition +  1;
            } else {
                run(nums,partition + 1,right);
                right = partition - 1;
            }
        }
    }

    public void swap(int[] nums,int i,int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 获取基准数下标
     * @param nums
     * @param left
     * @param right
     * @return
     */
    public int partition(int[] nums,int left,int right) {
        int medianThree = medianThree(nums, left, (left + right) / 2, right);
        swap(nums,left,medianThree);
        int i = left,j = right;
        while (i < j) {
            while (i <j && nums[j] >= nums[left]) {
                j--;
            }
            while (i <j && nums[i] <= nums[left]) {
                i++;
            }
            swap(nums,i,left);
        }
        return i;
    }

    public int medianThree (int[] nums,int left,int median,int right) {
        if ((nums[left] < nums[median]) ^ (nums[left] < nums[right])) {
            return left;
        } else if ((nums[median] < nums[left]) ^ (nums[median] < nums[right])) {
            return median;
        } else {
            return right;
        }
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] nums = new int[] {2,3,1,5,4};
        quickSort.run(nums,0,nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }
}
