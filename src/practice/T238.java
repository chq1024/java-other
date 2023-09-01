package practice;

import java.util.Arrays;

/**
 * @author bk
 */
public class T238 {

    // O(n2)
    public int[] productExceptSelf1(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        for (int j = 0; j < res.length; j++) {
            res[j] = 1;
            for (int i = 0; i < nums.length; i++) {
                if (i == j) {
                    continue;
                }
                res[j] = res[j] * nums[i];
            }
        }
        return res;
    }

    // O(n)
    public int[] productExceptSelf2(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        int before = 1;
        int after = 1;
        Arrays.fill(res,1);
        for (int i = 0,j=len-1; i < len; i++,j--) {
           res[i]*=before;
           res[j]*=after;
           before*=nums[i];
           after*=nums[j];
        }
        return res;
    }

    // O(n)
    public int[] productExceptSelf3(int[] nums) {
        int len = nums.length;
        int[] L = new int[len];
        int[] R = new int[len];
        int[] res = new int[len];
        L[0] = 1;
        for (int i = 1; i < len; i++) {
            L[i] = nums[i - 1] * L[i - 1];
        }
        R[len - 1] = 1;
        for (int j = len - 2; j >= 0; j--) {
            R[j] = nums[j+1] * R[j + 1];
        }

        for (int i = 0; i < len; i++) {
            res[i] = L[i] * R[i];
        }
        return res;
    }


    public static void main(String[] args) {
        T238 t238 = new T238();
        int[] nums = new int[] {1,2,3,4};
        System.out.println(Arrays.toString(t238.productExceptSelf3(nums)));
    }
}
