package module.thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Stream;

/**
 * @author bk
 */
public class demo40 {

    public static void main(String[] args) throws Exception {
        demo40 d40 = new demo40();
        demo40 d41 = new demo40();
//        String[] str = new String[] {"A","B"};
        Callable<String> c = ()->{
            System.out.println("doooo");
            return "xxxxx";
        };
//        Callable<String> reduce = Arrays.stream(str).reduce(c, (r1, tx) -> {
//            switch (tx) {
//                case "B":
//                    return () -> "BB";
//                case "A":
//                    return () -> "AA";
//                default:
//                    return null;
//            }
//        }, (r1, r2) -> {
//            return null;
//        });
//
//        System.out.println(reduce.call());

        List<String> x = new ArrayList<>();

        Callable<String> reduce1 = Stream.of("A","B").map(String::valueOf).reduce(c, (r1, tx) -> {
            switch (tx) {
                case "A":
                    return () -> d40.doHandler(r1);
                case "B":
                    return () -> d41.doHandler(r1);
                default:
                    return null;
            }
        }, (r1, r2) -> {
            return r2;
        });
        System.out.println("res:" + reduce1.call());
    }

    public String doHandler(Callable<String> call) {
        try {
            String callRes = call.call();
            System.out.println(callRes);
            return callRes;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
