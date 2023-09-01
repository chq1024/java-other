package module.flow;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @author bk
 */
public class FlowMain {

    private final static Pattern PATTERN = Pattern.compile("[0-9]*");

    public static void main(String[] args) {
        List<String> list = List.of("1", "2a", "3", "4b", "5c", "6", "7d", "8", "9");
        FlowHelper<String> flowHelper = FlowHelper.init(list.spliterator());
        flowHelper.filter(r-> PATTERN.matcher(r).matches()).mapping(Integer::valueOf).accept(System.out::println);
    }
}
