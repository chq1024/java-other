package module.ktve;

import java.util.HashMap;

/**
 * @author bk
 */
public class TryDemo {

    public static void main(String[] args) {
        doHandler();
    }


    public static void doHandler() {
        HashMap<Integer,Integer> doMap = new HashMap<>();
        doMap.merge(1,1,(o,n)->o+1);

        System.out.println(doMap);
    }
}
