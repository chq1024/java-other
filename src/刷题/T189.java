package 刷题;

import java.util.Arrays;

/**
 * @author bk
 */
public class T189 {
//    给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
//    输入: nums = [1,2,3,4,5,6,7], k = 3
//    输出: [5,6,7,1,2,3,4]
//    解释:
//    向右轮转 1 步: [7,1,2,3,4,5,6]
//    向右轮转 2 步: [6,7,1,2,3,4,5]
//    向右轮转 3 步: [5,6,7,1,2,3,4]

    // O(n)
    // 弊端:产生新数组
    public void rotate1(int[] nums, int k) {
        int len = nums.length;
        int[] temp = new int[len];
        k = k % len;
        if (k != 0) {
            for (int i = 0; i < len; i++) {
                temp[i] = nums[(len - k + i) % len];
            }
            System.out.println("in1:" + nums);
//            nums = temp;
            System.out.println("in2:" + nums);
            System.arraycopy(temp,0,nums,0,temp.length);
        }
    }

    // 翻转数组
    // O(2n)
    public void rotate2(int[] nums, int k) {
        k %= nums.length;
        reverse(nums,0,nums.length-1);
        reverse(nums,0,k -1);
        reverse(nums,k,nums.length - 1);
    }

    private void reverse(int [] nums,int start,int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start ++ ;
            end -- ;
        }
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1,2};
        T189 t189 = new T189();
        System.out.println(nums);
        t189.rotate2(nums,3);
        System.out.println(nums);
        System.out.println(Arrays.toString(nums));
    }
}
