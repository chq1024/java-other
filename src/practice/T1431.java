package practice;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bk
 */
public class T1431 {

    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> res = new ArrayList<>(candies.length);
        int max = 0;
        for (int i = 0; i < candies.length; i++) {
            max = Math.max(candies[i],max);
        }
        for (int i = 0; i < candies.length; i++) {
            res.add(i,candies[i] + extraCandies >= max);
        }
        return res;
    }

    public static void main(String[] args) {
        T1431 t1431 = new T1431();
        List<Boolean> booleans = t1431.kidsWithCandies(new int[]{2, 3, 1, 5, 3}, 2);
        System.out.println(booleans.toString());
    }
}
