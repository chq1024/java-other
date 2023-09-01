package practice;

import java.util.Arrays;

/**
 * @author bk
 */
public class T724 {

//    给你一个整数数组nums ，请计算数组的 中心下标 。
//    数组 中心下标 是数组的一个下标，其左侧所有元素相加的和等于右侧所有元素相加的和。
//    如果中心下标位于数组最左端，那么左侧数之和视为 0 ，因为在下标的左侧不存在元素。这一点对于中心下标位于数组最右端同样适用。
//    如果数组有多个中心下标，应该返回 最靠近左边 的那一个。如果数组不存在中心下标，返回 -1 。

    //设中心下标为i,数组总和为total,左侧之和为sumL,右侧之和为sumR = total - num[i] - sumL,即 sumL + sumL + num[i] = total时满足条件
    public int pivotIndex(int[] nums) {
        int total = Arrays.stream(nums).sum();
        int sumL = 0;
        for (int i = 0; i < nums.length; i++) {
            if (2 * sumL + nums[i] == total) {
                return i;
            }
            sumL += nums[i];
        }
        return -1;
    }

    public static void main(String[] args) {
        T724 t724 = new T724();
        int[] nums = new int[] {-1,-1,2};
        System.out.println(t724.pivotIndex(nums));
    }
}
