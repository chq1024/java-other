package module.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author bk
 */
public class BucketSort {

    public void run(int[] nums) {
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            buckets.add(new ArrayList<>());
        }
        for (int num : nums) {
            if (num>=0 && num < 2) {
                buckets.get(0).add(num);
            } else if (num >=2 && num < 4) {
                buckets.get(1).add(num);
            } else {
                buckets.get(2).add(num);
            }
        }
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
        }
        int i = 0;
        for (List<Integer> bucket : buckets) {
            for (Integer item : bucket) {
                nums[i++] = item;
            }
        }
    }

    public static void main(String[] args) {
        BucketSort bucketSort = new BucketSort();
        int[] nums = new int[] {2,3,1,5,4};
        bucketSort.run(nums);
        System.out.println(Arrays.toString(nums));
    }
}
