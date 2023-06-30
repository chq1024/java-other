package 刷题;

import java.util.Arrays;

/**
 * @author bk
 */
public class T66 {

    public int[] plusOne1(int[] digits) {
        int len = digits.length;
        int in = 0;
        for (int i = len - 1; i >= 0; i--) {
            int num = digits[i] + in;
            if (i == len - 1) {
                num = num + 1;
            }
            if (num >= 10) {
                if (i - 1 < 0) {
                     int[] res = new int[len + 1];
                     res[0] = 1;
                     return res;
                }
                digits[i] = 0;
                in = 1;
            } else {
                digits[i]  = num;
                break;
            }
        }
        return digits;
    }

    public int[] plusOne2(int[] digits) {
        int len = digits.length;
        for (int i = len - 1; i >= 0; i--) {
            digits[i] = (digits[i] + 1) % 10;
            if (digits[i] != 0) {
                return digits;
            }
        }
        int[] res = new int[len + 1];
        res[0] = 1;
        return res;
    }


    public static void main(String[] args) {
        int[] nums = new int[] {1,9,9};
        T66 t66 = new T66();
        int[] res = t66.plusOne2(nums);
        System.out.println(Arrays.toString(res));
    }
}
