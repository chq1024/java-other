package practice;

/**
 * @author bk
 */
public class T605 {

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0 && (i == 0 || flowerbed[i -1] == 0) && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                n--;
            }
            if (n <=0) {
                return true;
            }
        }
        return false;
    }

    public boolean canPlaceFlowers2(int[] flowerbed, int n) {
        int len = flowerbed.length;
        int prev = -1;
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (flowerbed[i] == 1) {
                if (prev < 0) {
                    count += i / 2;
                } else {
                    count +=(i - prev - 2) / 2;
                }
                if (count >= n) {
                    return true;
                }
                prev = i;
            }
        }
        if (prev <  0) {
            count += (len + 1)/ 2;
        } else {
            count += (len - prev - 1) / 2;
        }

        return count >= n;
    }

    public static void main(String[] args) {
        T605 t605 = new T605();
        System.out.println(t605.canPlaceFlowers(new int[]{0,1,0,0,1,0,1,0,0,1}, 1));
    }

}
