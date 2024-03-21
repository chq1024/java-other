package module.thread;

/**
 * @author bk
 */
public class demo38 {

    public int max(int[] arr) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                max = Math.max(arr[j] - arr[i], max);
            }
        }
        return max;
    }

    public int maxProfit(int[] prices) {
        int idx = 0;
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            int dis = prices[i] - prices[idx];
            if (dis < 0) {
                idx = i;
            } else {
                max = Math.max(dis, max);
            }
        }
        return max;
    }

    public int max2(int[] arr) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            } else if (arr[i] - min > max) {
                max = arr[i] - min;
            }
        }
        return max;
    }



    public static void main(String[] args) {
        demo38 demo38 = new demo38();
        int max = demo38.maxProfit(new int[]{2,1,2,1,0,1,2});
        System.out.println(max);
    }
}
