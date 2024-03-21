package module.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bk
 */
public class demo6 {

    public static void main(String[] args) {
        List<String> arr = new ArrayList<>();
        arr.add("1");
        arr.add("2");
        arr.add("3");
        arr.add("10");
        arr.add("5");
        arr.add("3");
        System.out.println(arr.parallelStream().map(Integer::parseInt).collect(Collectors.toList()));
    }
}
