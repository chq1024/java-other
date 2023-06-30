package 刷题;

/**
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * @author bk
 */
public class Num04 {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int size = nums1.length + nums2.length;
        int n1 , n2 = 0;
        int num = size / 2 + 1;

        return 1;
    }

    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int num1,num2=0;
        if (len1 % 2 > 0) {
            num1 = nums1[len1 / 2];
        } else {
            num1 = nums1[len1 / 2 -1] + nums1[len1 /2];
        }
        if (len2 % 2 > 0) {
            num2 = nums2[len2 / 2];
        } else {
            num2 = nums2[len2 / 2 -1] + nums2[len2 /2];
        }

        return 1;
    }

}
