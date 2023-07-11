package 排序;

import java.util.Arrays;
import java.util.zip.ZipOutputStream;

/**
 * @author bk
 */
public class BubbleSort {


    public int[] exec(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - i- 1; j++) {
                if (nums[j] > nums[j + 1]) {
//                    int temp = nums[j + 1];
//                    nums[j+1] = nums[j];
//                    nums[j] = temp;
                    nums[j] = nums[j] ^ nums[j + 1];
                    nums[j + 1] = nums[j] ^ nums[j + 1];
                    nums[j] = nums[j] ^ nums[j + 1];
                }
            }
        }
        return nums;
    }


    public void exec2 () {
        ZipOutputStream outputStream  = null;
        try {
//            outputStream = new ZipOutputStream();
        }catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        //select
        BubbleSort bubbleSort = new BubbleSort();
        int[] ints = new int[] {6, 2, 3, 5, 1, 4};
        System.out.println(Arrays.toString(bubbleSort.exec(ints)));
    }
}
