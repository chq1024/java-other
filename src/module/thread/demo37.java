package module.thread;

/**
 * 插入
 * @author bk
 */
public class demo37 {


    public int[] run(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int pre = arr[i - 1];
            int curr = arr[i];
            while (pre >= 0 && curr < arr[pre]) {
                arr[pre + 1] = arr[pre];
                pre--;
            }
            arr[pre + 1] = curr;
        }
        return arr;
    }
}
