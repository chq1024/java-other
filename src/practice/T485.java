package practice;

/**
 * @author bk
 */
public class T485 {

    // 给定一个二进制数组 nums ， 计算其中最大连续 1 的个数。
    public int findMaxConsecutiveOnes1(int[] nums) {
        int sum = 0;
        int count = 0;
        for (int num : nums) {
            if (num == 1) {
                count +=1;
                sum =  Math.max(count,sum);
            } else {
                count = 0;
            }
        }
        return sum;
    }

    public int findMaxConsecutiveOnes2(int[] nums) {
        int sum = 0;
        int last = 0;
        for (int i = 1; i <= nums.length; i++) {
            if (nums[i-1] == 1) {
                sum = Math.max(i-last,sum);
            } else {
                last = i;
            }
        }
        return sum;
    }


    public static void main(String[] args) {
        T485 t485 = new T485();
        int[] nums = new int[] {1,0,0,1,1,1,0,1};
        System.out.println(t485.findMaxConsecutiveOnes2(nums));
    }
}
