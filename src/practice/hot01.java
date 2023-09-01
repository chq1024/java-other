package practice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class hot01 {

    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            for (int j = i + 1; j < nums.length ; j++) {
                if (temp == nums[j]) {
                    return new int[] {i,j};
                }
            }
        }
        return new int[0];
    }

    public int[] twoSum2(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return new int[] {map.get(nums[i]),i};
            }
            map.put(target - nums[i],i);
        }
        return new int[0];
    }

    public static void main(String[] args) {
        int[] nums = new int[] {3,2,4};
        hot01 hot01 = new hot01();
        int[] ints = hot01.twoSum2(nums, 6);
        System.out.println(Arrays.toString(ints));
    }
}
