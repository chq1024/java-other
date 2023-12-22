package practice;

import java.util.HashMap;

public class hot01 {

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> numMap = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int cost = target - nums[i];
            if (numMap.containsKey(cost)) {
                return new int[] {i,numMap.get(cost)};
            }
            numMap.put(cost,i);
        }
        return new int[]{};
    }

    public static void main(String[] args) {

    }
}
